package de.uniluebeck.itm.tr.iwsn.portal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.AbstractService;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.protobuf.MessageLite;
import de.uniluebeck.itm.tr.common.config.CommonConfig;
import de.uniluebeck.itm.tr.iwsn.common.ResponseTracker;
import de.uniluebeck.itm.tr.iwsn.common.ResponseTrackerCache;
import de.uniluebeck.itm.tr.iwsn.common.ResponseTrackerFactory;
import de.uniluebeck.itm.tr.iwsn.messages.*;
import de.uniluebeck.itm.tr.iwsn.portal.eventstore.ReservationEventStore;
import de.uniluebeck.itm.tr.iwsn.portal.eventstore.ReservationEventStoreFactory;
import de.uniluebeck.itm.tr.rs.persistence.RSPersistence;
import de.uniluebeck.itm.tr.rs.persistence.RSPersistenceListener;
import de.uniluebeck.itm.util.scheduler.SchedulerService;
import eu.wisebed.api.v3.common.NodeUrn;
import eu.wisebed.api.v3.common.NodeUrnPrefix;
import eu.wisebed.api.v3.common.SecretReservationKey;
import eu.wisebed.api.v3.rs.ConfidentialReservationData;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.ReadableInstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newHashSet;
import static de.uniluebeck.itm.tr.common.ReservationHelper.deserialize;
import static de.uniluebeck.itm.tr.common.ReservationHelper.serialize;
import static org.joda.time.DateTime.now;

public class ReservationImpl extends AbstractService implements Reservation {

    private static final Duration KEEP_ALIVE_TIME = Duration.standardMinutes(5);

    private static final Logger log = LoggerFactory.getLogger(Reservation.class);

    private final Set<NodeUrn> nodeUrns;

    private final ReservationEventBus reservationEventBus;

    private final Interval interval;

    private final String username;

    private final String key;

    private final ResponseTrackerCache responseTrackerCache;

    private final ResponseTrackerFactory responseTrackerFactory;

    private final CommonConfig commonConfig;

    private final PortalServerConfig portalServerConfig;

    private final ReservationEventStore reservationEventStore;

    private final RSPersistence rsPersistence;

    private final PortalEventBus portalEventBus;

    private final SchedulerService schedulerService;

    @VisibleForTesting
    private Callable<Void> nextScheduledEvent;

    private Set<ConfidentialReservationData> confidentialReservationData;

    @VisibleForTesting
    private ScheduledFuture<Void> nextScheduledEventFuture;

    @Nullable
    private DateTime cancelled;

    @Nullable
    private DateTime finalized;

    private final RSPersistenceListener rsPersistenceListener = new RSPersistenceListener() {
        @Override
        public void onReservationMade(final List<ConfidentialReservationData> crd) {
            // nothing to do
        }

        @Override
        public void onReservationCancelled(final List<ConfidentialReservationData> crd) {
            if (crd.get(0).getSecretReservationKey().equals(ReservationImpl.this.getSecretReservationKey())) {
                ReservationImpl.this.confidentialReservationData = newHashSet(crd);
                ReservationImpl.this.cancelled = crd.get(0).getCancelled();
                if (nextScheduledEvent instanceof ReservationEndCallable || (cancelled != null && cancelled.isBefore(getInterval().getStart()) && nextScheduledEvent instanceof ReservationStartCallable)) {
                    // now is between start and end -> cancel the end callable and schedule the cancellation
                    // or cancel is before start -> schedule cancellation instead of starting
                    nextScheduledEventFuture.cancel(true);
                    scheduleEvent(new ReservationCancelCallable(), cancelled);
                }
            }
        }

        @Override
        public void onReservationFinalized(
                final List<ConfidentialReservationData> crd) {
            if (crd.get(0).getSecretReservationKey().equals(ReservationImpl.this.getSecretReservationKey())) {
                ReservationImpl.this.finalized = crd.get(0).getFinalized();
            }
        }
    };

    @VisibleForTesting
    Runnable startRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                portalEventBus.post(ReservationOpenedEvent.newBuilder().setSerializedKey(getSerializedKey()).build());
                scheduleFirstLifecycleCallable();
            } catch (Exception e) {
                log.error("Exception while posting ReservationOpenedEvent before scheduling lifecycle events: ", e);
            }
        }
    };

    @Inject
    public ReservationImpl(final CommonConfig commonConfig,
                           final PortalServerConfig portalServerConfig,
                           final RSPersistence rsPersistence,
                           final ReservationEventBusFactory reservationEventBusFactory,
                           final ResponseTrackerCache responseTrackerCache,
                           final ResponseTrackerFactory responseTrackerFactory,
                           final ReservationEventStoreFactory reservationEventStoreFactory,
                           final PortalEventBus portalEventBus,
                           @Assisted final SchedulerService schedulerService,
                           @Assisted final List<ConfidentialReservationData> confidentialReservationDataList,
                           @Assisted("secretReservationKey") final String key,
                           @Assisted("username") final String username,
                           @Nullable @Assisted("cancelled") final DateTime cancelled,
                           @Nullable @Assisted("finalized") final DateTime finalized,
                           @Assisted final Set<NodeUrn> nodeUrns,
                           @Assisted final Interval interval) {
        this.portalEventBus = checkNotNull(portalEventBus);
        this.rsPersistence = checkNotNull(rsPersistence);
        this.commonConfig = checkNotNull(commonConfig);
        this.portalServerConfig = checkNotNull(portalServerConfig);
        this.responseTrackerCache = checkNotNull(responseTrackerCache);
        this.responseTrackerFactory = checkNotNull(responseTrackerFactory);
        this.confidentialReservationData = newHashSet(checkNotNull(confidentialReservationDataList));
        this.key = checkNotNull(key);
        this.username = checkNotNull(username);
        this.cancelled = cancelled;
        this.finalized = finalized;
        this.nodeUrns = checkNotNull(nodeUrns);
        this.interval = checkNotNull(interval);
        this.reservationEventBus = checkNotNull(reservationEventBusFactory.create(this));
        this.reservationEventStore = reservationEventStoreFactory.createOrLoad(this);
        this.schedulerService = checkNotNull(schedulerService);
    }

    private void scheduleFirstLifecycleCallable() {
        if (!isFinalized()) {
            // the reservation isn't finalized yet. Replay all events that should have occurred in the past and schedule future events.
            scheduleEvent(new ReservationMadeCallable(), now());
        }
    }

    private static Duration calculateDurationFromNow(ReadableInstant to) {
        ReadableInstant now = now();
        return (now.isAfter(to) || now.isEqual(to)) ? Duration.ZERO : new Duration(now, to);
    }

    @Override
    protected void doStart() {
        log.trace("ReservationImpl.doStart()");
        try {
            if (portalServerConfig.isReservationEventStoreEnabled()) {
                reservationEventStore.startAsync().awaitRunning();
            }
            reservationEventBus.startAsync().awaitRunning();
            rsPersistence.addListener(rsPersistenceListener);
            schedulerService.execute(startRunnable);
            notifyStarted();
        } catch (Exception e) {
            notifyFailed(e);
        }
    }

    @Override
    protected void doStop() {
        log.trace("ReservationImpl.doStop()");
        try {
            portalEventBus.post(ReservationClosedEvent.newBuilder().setSerializedKey(getSerializedKey()).build());
        } catch (Exception e) {
            log.error("Exception while posting ReservationClosedEvent: ", e);
        }
        try {
            rsPersistence.removeListener(rsPersistenceListener);
            reservationEventBus.stopAsync().awaitTerminated();
            reservationEventStore.stopAsync().awaitTerminated();
            notifyStopped();
        } catch (Exception e) {
            notifyFailed(e);
        }
    }

    @Override
    public Set<Entry> getEntries() {
        return newHashSet(new Entry(
                        commonConfig.getUrnPrefix(),
                        username,
                        key,
                        nodeUrns,
                        interval,
                        reservationEventBus
                )
        );
    }

    @Override
    public Set<NodeUrnPrefix> getNodeUrnPrefixes() {
        return newHashSet(commonConfig.getUrnPrefix());
    }

    @Override
    public Set<NodeUrn> getNodeUrns() {
        return nodeUrns;
    }

    @Override
    public ReservationEventBus getEventBus() {
        return reservationEventBus;
    }

    @Override
    public Interval getInterval() {
        return interval;
    }

    @Override
    @Nullable
    public DateTime getCancelled() {
        return cancelled;
    }

    @Nullable
    @Override
    public DateTime getFinalized() {
        return finalized;
    }

    @Override
    public boolean isFinalized() {
        return getFinalized() != null && (getFinalized().isBeforeNow() || getFinalized().isEqualNow());
    }

    @Override
    public boolean isCancelled() {
        return getCancelled() != null && (getCancelled().isBeforeNow() || getCancelled().isEqualNow());
    }

    @Override
    public String getSerializedKey() {
        try {
            return serialize(getSecretReservationKey());
        } catch (Exception e) {
            throw propagate(e);
        }
    }

    @Override
    public List<MessageLite> getPastLifecycleEvents() {

        final List<MessageLite> events = newLinkedList();

        events.add(ReservationMadeEvent.newBuilder().setSerializedKey(getSerializedKey()).build());

        final boolean started = getInterval().getStart().isBeforeNow() &&
                (getCancelled() == null || getCancelled().isAfter(getInterval().getStart()));

        if (started) {

            final ReservationStartedEvent event = ReservationStartedEvent.newBuilder()
                    .setSerializedKey(getSerializedKey())
                    .setTimestamp(getInterval().getStartMillis())
                    .build();
            events.add(event);
        }

        final boolean ended = getInterval().isBeforeNow();

        if (isCancelled()) {

            final ReservationCancelledEvent event = ReservationCancelledEvent.newBuilder()
                    .setSerializedKey(getSerializedKey())
                    .setTimestamp(getCancelled().getMillis())
                    .build();
            events.add(event);

        } else if (ended) {

            final ReservationEndedEvent event = ReservationEndedEvent.newBuilder()
                    .setSerializedKey(getSerializedKey())
                    .setTimestamp(getInterval().getEndMillis())
                    .build();
            events.add(event);
        }

        if (isFinalized()) {

            assert getFinalized() != null;
            final ReservationFinalizedEvent event = ReservationFinalizedEvent.newBuilder()
                    .setSerializedKey(getSerializedKey())
                    .setTimestamp(getFinalized().getMillis())
                    .build();
            events.add(event);
        }

        return events;
    }

    private SecretReservationKey getSecretReservationKey() {
        return new SecretReservationKey().withKey(key).withUrnPrefix(commonConfig.getUrnPrefix());
    }

    @Override
    public Set<SecretReservationKey> getSecretReservationKeys() {
        return newHashSet(getSecretReservationKey());
    }

    @Override
    public Set<ConfidentialReservationData> getConfidentialReservationData() {
        return confidentialReservationData;
    }

    @Override
    public ResponseTracker createResponseTracker(final Request request) {
        if (responseTrackerCache.getIfPresent(request.getRequestId()) != null) {
            throw new IllegalArgumentException(
                    "ResponseTracker for requestId \"" + request.getRequestId() + "\" already exists!"
            );
        }
        final ResponseTracker responseTracker = responseTrackerFactory.create(request, reservationEventBus);
        responseTrackerCache.put(request.getRequestId(), responseTracker);
        return responseTracker;
    }

    @Override
    public ResponseTracker getResponseTracker(final long requestId) {
        return responseTrackerCache.getIfPresent(requestId);
    }

    @Override
    public void enableVirtualization() {
        reservationEventBus.enableVirtualization();
    }

    @Override
    public void disableVirtualization() {
        reservationEventBus.disableVirtualization();
    }

    @Override
    public boolean isVirtualizationEnabled() {
        return reservationEventBus.isVirtualizationEnabled();
    }

    @Override
    public ReservationEventStore getEventStore() {
        return reservationEventStore;
    }

    @Override
    public boolean touch() {
        if (isFinalized() && !isRunning()) {
            startAsync().awaitRunning();
            rescheduleFinalization();
            return true;
        }
        if (nextScheduledEvent instanceof ReservationFinalizeCallable) {
            rescheduleFinalization();
            boolean touched = !nextScheduledEventFuture.isDone() && !nextScheduledEventFuture.isCancelled();
            if (touched) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ReservationImpl that = (ReservationImpl) o;
        return interval.equals(that.interval) && nodeUrns.equals(that.nodeUrns);
    }

    @Override
    public int hashCode() {
        int result = nodeUrns.hashCode();
        result = 31 * result + interval.hashCode();
        return result;
    }

    private void scheduleEvent(Callable<Void> callable, DateTime time) {
        nextScheduledEvent = callable;
        Duration duration = calculateDurationFromNow(time);
        log.trace("Reservation[{}]: scheduled {} in {}", key, callable.getClass().getSimpleName(), duration);
        nextScheduledEventFuture =
                schedulerService.schedule(nextScheduledEvent, duration.getMillis(), TimeUnit.MILLISECONDS);
    }

    private void rescheduleFinalization() {
        if (nextScheduledEvent instanceof ReservationFinalizeCallable) {
            nextScheduledEventFuture.cancel(true);
        }
        scheduleEvent(new ReservationFinalizeCallable(), now().plus(KEEP_ALIVE_TIME));
    }


    protected class ReservationMadeCallable implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            log.trace("ReservationMadeCallable.call({})", key);
            final Reservation reservation = ReservationImpl.this;
            if (reservation.isFinalized()) {
                return null;
            }
            final ReservationMadeEvent event = ReservationMadeEvent
                    .newBuilder()
                    .setSerializedKey(reservation.getSerializedKey())
                    .build();
            portalEventBus.post(event);
            if (reservation.getCancelled() != null && reservation.getCancelled()
                    .isBefore(reservation.getInterval().getStart())) {
                // Reservation is cancelled before start
                scheduleEvent(new ReservationCancelCallable(), now());
            } else {
                scheduleEvent(new ReservationStartCallable(), reservation.getInterval().getStart());
            }
            return null;
        }
    }

    protected class ReservationStartCallable implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            log.trace("ReservationStartCallable.call({})", key);
            final Reservation reservation = ReservationImpl.this;
            reservation.startAsync().awaitRunning();
            if (reservation.isFinalized()) {
                rescheduleFinalization();
                return null;
            }
            final ReservationStartedEvent event = ReservationStartedEvent
                    .newBuilder()
                    .setSerializedKey(reservation.getSerializedKey())
                    .setTimestamp(reservation.getInterval().getStartMillis())
                    .build();
            portalEventBus.post(event);
            if (reservation.getCancelled() != null && reservation.getCancelled()
                    .isBefore(reservation.getInterval().getEnd())) {
                scheduleEvent(new ReservationCancelCallable(), reservation.getCancelled());
            } else {
                scheduleEvent(new ReservationEndCallable(), reservation.getInterval().getEnd());
            }
            return null;
        }
    }

    protected class ReservationCancelCallable implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            log.trace("ReservationCancelCallable.call({})", key);
            final Reservation reservation = ReservationImpl.this;
            if (reservation.isFinalized()) {
                return null;
            }
            assert reservation.getCancelled() != null;
            final ReservationCancelledEvent event = ReservationCancelledEvent
                    .newBuilder()
                    .setSerializedKey(reservation.getSerializedKey())
                    .setTimestamp(reservation.getCancelled().getMillis())
                    .build();
            portalEventBus.post(event);
            if (!reservation.isRunning()) {
                scheduleEvent(new ReservationFinalizeCallable(), now());
            } else {
                rescheduleFinalization();
            }
            return null;
        }
    }

    protected class ReservationEndCallable implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            log.trace("ReservationEndCallable.call({})", key);
            final Reservation reservation = ReservationImpl.this;
            if (reservation.isFinalized()) {
                return null;
            }
            final ReservationEndedEvent event = ReservationEndedEvent
                    .newBuilder()
                    .setSerializedKey(reservation.getSerializedKey())
                    .setTimestamp(reservation.getInterval().getEndMillis())
                    .build();
            portalEventBus.post(event);
            rescheduleFinalization();
            return null;
        }
    }

    protected class ReservationFinalizeCallable implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            log.trace("ReservationFinalizeCallable.call({})", key);
            final Reservation reservation = ReservationImpl.this;
            if (!reservation.isFinalized()) {
                Set<SecretReservationKey> srks;
                srks = deserialize(reservation.getSerializedKey());
                // Finalize reservations of this testbed, filter federated reservations
                ReservationImpl.this.finalized = DateTime.now();
                for (SecretReservationKey current : srks) {
                    if (commonConfig.getUrnPrefix().equals(current.getUrnPrefix())) {
                        rsPersistence.finalizeReservation(current);
                    }
                }

                assert reservation.getFinalized() != null;
                final ReservationFinalizedEvent event = ReservationFinalizedEvent
                        .newBuilder()
                        .setSerializedKey(reservation.getSerializedKey())
                        .setTimestamp(reservation.getFinalized().getMillis())
                        .build();
                portalEventBus.post(event);
            }
            if (reservation.isRunning()) {
                reservation.stopAsync().awaitTerminated();
            }


            return null;
        }
    }
}