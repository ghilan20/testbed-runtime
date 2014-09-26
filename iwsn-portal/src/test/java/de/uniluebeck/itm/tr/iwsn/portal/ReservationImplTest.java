package de.uniluebeck.itm.tr.iwsn.portal;

import de.uniluebeck.itm.tr.common.config.CommonConfig;
import de.uniluebeck.itm.tr.iwsn.common.ResponseTrackerCache;
import de.uniluebeck.itm.tr.iwsn.common.ResponseTrackerFactory;
import de.uniluebeck.itm.tr.iwsn.portal.eventstore.ReservationEventStore;
import de.uniluebeck.itm.tr.iwsn.portal.eventstore.ReservationEventStoreFactory;
import de.uniluebeck.itm.tr.rs.persistence.RSPersistence;
import eu.wisebed.api.v3.common.NodeUrn;
import eu.wisebed.api.v3.rs.ConfidentialReservationData;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReservationImplTest {

	private static final Set<NodeUrn> NODE_URNS = newHashSet(new NodeUrn("urn:unit-test:0x0001"));

	private static final Interval INTERVAL = new Interval(DateTime.now(), DateTime.now().plusHours(1));

	private static final String username = "Horst Ackerpella";

	@Mock
	private ReservationEventBusFactory reservationEventBusFactory;

	@Mock
	private ReservationEventBus reservationEventBus;

	@Mock
	private ResponseTrackerCache responseTrackerTimedCache;

	@Mock
	private ResponseTrackerFactory responseTrackerFactory;

	@Mock
	private CommonConfig commonConfig;

	@Mock
	private ConfidentialReservationData confidentialReservationData;

	@Mock
	private ReservationEventStoreFactory reservationEventStoreFactory;

	@Mock
	private ReservationEventStore reservationEventStore;

	@Mock
	private PortalEventBus portalEventBus;

	@Mock
	private RSPersistence rsPersistence;

	private ReservationImpl reservation;

	@Before
	public void setUp() throws Exception {
		when(reservationEventBusFactory.create(Matchers.<Reservation>any())).thenReturn(reservationEventBus);
		when(reservationEventStoreFactory.createOrLoad(Matchers.<Reservation>any())).thenReturn(reservationEventStore);
		reservation = new ReservationImpl(
				commonConfig,
				rsPersistence,
				reservationEventBusFactory,
				responseTrackerTimedCache,
				responseTrackerFactory,
				reservationEventStoreFactory,
				newArrayList(confidentialReservationData),
				"someRandomReservationIdHere",
				username,
                null,
                null,
				NODE_URNS,
				INTERVAL
		);
	}

	@Test
	public void testThatReservationEventBusIsCreatedAndStartedWhenStartingReservation() throws Exception {
		reservation.startAndWait();
		verify(reservationEventBus).startAndWait();
	}

	@Test
	public void testThatReservationEventBusIsStoppedWhenStoppingReservation() throws Exception {
		reservation.startAndWait();
		reservation.stopAndWait();
		verify(reservationEventBus).stopAndWait();
	}

	@Test
	public void testThatEventStoreIsStartedWhenStartingReservation() throws Exception {
		reservation.startAndWait();
		verify(reservationEventStore).startAndWait();
	}

	@Test
	public void testThatEventStoreIsStoppedWhenStoppingReservation() throws Exception {
		reservation.startAndWait();
		reservation.stopAndWait();
		verify(reservationEventStore).stopAndWait();
	}
}
