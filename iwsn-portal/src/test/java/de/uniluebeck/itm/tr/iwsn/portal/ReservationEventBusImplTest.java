package de.uniluebeck.itm.tr.iwsn.portal;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.EventBus;
import com.google.protobuf.ByteString;
import de.uniluebeck.itm.tr.iwsn.messages.*;
import eu.wisebed.api.v3.common.NodeUrn;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.*;
import static de.uniluebeck.itm.tr.iwsn.messages.MessagesHelper.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationEventBusImplTest {

	private static final NodeUrn GW1_N1 = new NodeUrn("urn:unit-test:gw1:0x0001");

	private static final NodeUrn GW1_N2 = new NodeUrn("urn:unit-test:gw1:0x0002");

	private static final NodeUrn GW1_N3 = new NodeUrn("urn:unit-test:gw1:0x0003");

	private static final NodeUrn GW2_N1 = new NodeUrn("urn:unit-test:gw2:0x0001");

	private static final NodeUrn GW2_N2 = new NodeUrn("urn:unit-test:gw2:0x0002");

	private static final NodeUrn GW2_N3 = new NodeUrn("urn:unit-test:gw2:0x0003");

	private static final Set<NodeUrn> GW1_NODES = newHashSet(GW1_N1, GW1_N2, GW1_N3);

	private static final Set<NodeUrn> GW2_NODES = newHashSet(GW2_N1, GW2_N2, GW2_N3);

	private static final Set<NodeUrn> ALL_NODES = union(GW1_NODES, GW2_NODES);

	private static final Set<NodeUrn> RESERVED_NODES = newHashSet(GW1_N1, GW1_N2, GW2_N1, GW2_N3);

	private static final Set<NodeUrn> UNRESERVED_NODES = difference(ALL_NODES, RESERVED_NODES);

	private static final Multimap<NodeUrn, NodeUrn> LINKS_RESERVED = HashMultimap.create();

	private static final Multimap<NodeUrn, NodeUrn> LINKS_UNRESERVED = HashMultimap.create();

	static {
		LINKS_RESERVED.put(GW1_N1, GW1_N2);
		LINKS_RESERVED.put(GW2_N1, GW2_N3);

		LINKS_UNRESERVED.put(GW1_N1, GW2_N2);
		LINKS_UNRESERVED.put(GW2_N2, GW2_N3);
	}

	private static final Random RANDOM = new Random();

	private static final Iterable<? extends SetChannelPipelinesRequest.ChannelHandlerConfiguration>
			CHANNEL_HANDLER_CONFIGURATIONS = newArrayList();

	private static final String RESERVATION_ID = "" + RANDOM.nextLong();

	@Mock
	private PortalEventBus portalEventBus;

	@Mock
	private EventBus eventBus;

	private ReservationEventBusImpl reservationEventBus;

	@Before
	public void setUp() throws Exception {
		reservationEventBus = new ReservationEventBusImpl(portalEventBus, eventBus, RESERVED_NODES);
		reservationEventBus.startAndWait();
	}

	@After
	public void tearDown() throws Exception {
		reservationEventBus.stopAndWait();
		reservationEventBus = null;
	}

	@Test
	public void testAreNodesAliveRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newAreNodesAliveRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES));
	}

	@Test
	public void testAreNodesAliveRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newAreNodesAliveRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES));
		postRequestAndVerifyException(newAreNodesAliveRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES));
	}

	@Test
	public void testAreNodesConnectedRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newAreNodesConnectedRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES));
	}

	@Test
	public void testAreNodesConnectedRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newAreNodesConnectedRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES));
		postRequestAndVerifyException(newAreNodesConnectedRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES));
	}

	@Test
	public void testDisableNodesRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newDisableNodesRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES));
	}

	@Test
	public void testDisableNodesRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newDisableNodesRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES));
		postRequestAndVerifyException(newDisableNodesRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES));
	}

	@Test
	public void testEnableNodesRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newEnableNodesRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES));
	}

	@Test
	public void testEnableNodesRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newEnableNodesRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES));
		postRequestAndVerifyException(newEnableNodesRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES));
	}

	@Test
	public void testResetNodesRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newResetNodesRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES));
	}

	@Test
	public void testResetNodesRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newResetNodesRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES));
		postRequestAndVerifyException(newResetNodesRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES));
	}

	@Test
	public void testDisableVirtualLinksRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newDisableVirtualLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_RESERVED));
	}

	@Test
	public void testDisableVirtualLinksRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(
				newDisableVirtualLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_UNRESERVED)
		);
	}

	@Test
	public void testEnableVirtualLinksRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newEnableVirtualLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_RESERVED));
	}

	@Test
	public void testEnableVirtualLinksRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newEnableVirtualLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_UNRESERVED)
		);
	}

	@Test
	public void testDisablePhysicalLinksRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newDisablePhysicalLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_RESERVED)
		);
	}

	@Test
	public void testDisablePhysicalLinksRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(
				newDisablePhysicalLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_UNRESERVED)
		);
	}

	@Test
	public void testEnablePhysicalLinksRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newEnablePhysicalLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_RESERVED));
	}

	@Test
	public void testEnablePhysicalLinksRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(
				newEnablePhysicalLinksRequest(RESERVATION_ID, RANDOM.nextLong(), LINKS_UNRESERVED)
		);
	}

	@Test
	public void testSendDownstreamMessageRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		final byte[] bytes = "hello".getBytes(Charset.defaultCharset());
		postRequestAndVerifyForwarded(
				newSendDownstreamMessageRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES, bytes)
		);
	}

	@Test
	public void testSendDownstreamMessageRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		final byte[] bytes = "hello".getBytes(Charset.defaultCharset());
		postRequestAndVerifyException(
				newSendDownstreamMessageRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES, bytes)
		);
		postRequestAndVerifyException(
				newSendDownstreamMessageRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES, bytes)
		);
	}

	@Test
	public void testFlashImagesRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		final byte[] imageBytes = "hello".getBytes(Charset.defaultCharset());
		postRequestAndVerifyForwarded(
				newFlashImagesRequest(RESERVATION_ID, RANDOM.nextLong(), RESERVED_NODES, imageBytes)
		);
	}

	@Test
	public void testFlashImagesRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		final byte[] imageBytes = "hello".getBytes(Charset.defaultCharset());
		postRequestAndVerifyException(
				newFlashImagesRequest(RESERVATION_ID, RANDOM.nextLong(), UNRESERVED_NODES, imageBytes)
		);
		postRequestAndVerifyException(newFlashImagesRequest(RESERVATION_ID, RANDOM.nextLong(), ALL_NODES, imageBytes));
	}

	@Test
	public void testSetChannelPipelinesRequestCanBePostedIfNodeUrnsArePartOfReservation() throws Exception {
		postRequestAndVerifyForwarded(newSetChannelPipelinesRequest(
				RESERVATION_ID,
				RANDOM.nextLong(),
				RESERVED_NODES,
				CHANNEL_HANDLER_CONFIGURATIONS
		)
		);
	}

	@Test
	public void testSetChannelPipelinesRequestCanNotBePostedIfNodeUrnsAreNotPartOfReservation() throws Exception {
		postRequestAndVerifyException(newSetChannelPipelinesRequest(
				RESERVATION_ID,
				RANDOM.nextLong(),
				UNRESERVED_NODES,
				CHANNEL_HANDLER_CONFIGURATIONS
		)
		);
		postRequestAndVerifyException(newSetChannelPipelinesRequest(
				RESERVATION_ID,
				RANDOM.nextLong(),
				ALL_NODES,
				CHANNEL_HANDLER_CONFIGURATIONS
		)
		);
	}

	@Test
	public void testUpstreamMessageEventShouldBeForwardedIfNodePartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : RESERVED_NODES) {
			final UpstreamMessageEvent event = newUpstreamMessageEvent(nodeUrn, nodeUrn.toString());
			reservationEventBus.onUpstreamMessageEventFromPortalEventBus(event);
			verify(eventBus).post(eq(event));
		}
	}

	@Test
	public void testUpstreamMessageEventShouldNotBeForwardedIfNodeNotPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : UNRESERVED_NODES) {
			final UpstreamMessageEvent event = newUpstreamMessageEvent(nodeUrn, nodeUrn.toString());
			reservationEventBus.onUpstreamMessageEventFromPortalEventBus(event);
			verify(eventBus, never()).post(eq(event));
		}
	}

	@Test
	public void testDevicesAttachedEventShouldBeForwardedIfNodePartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : RESERVED_NODES) {
			final DevicesAttachedEvent event = newDevicesAttachedEvent(new DateTime().getMillis(), nodeUrn);
			reservationEventBus.onDevicesAttachedEventFromPortalEventBus(event);
			verify(eventBus).post(eq(event));
		}
	}

	@Test
	public void testDevicesAttachedEventShouldNotBeForwardedIfNodeNotPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : UNRESERVED_NODES) {
			final DevicesAttachedEvent event = newDevicesAttachedEvent(new DateTime().getMillis(), nodeUrn);
			reservationEventBus.onDevicesAttachedEventFromPortalEventBus(event);
			verify(eventBus, never()).post(eq(event));
		}
	}

	@Test
	public void testDevicesAttachedEventShouldBeFilteredIfSomeNodesArePartOfReservation() throws Exception {

		final long timestamp = new DateTime().getMillis();
		final DevicesAttachedEvent event = newDevicesAttachedEvent(timestamp, ALL_NODES);
		reservationEventBus.onDevicesAttachedEventFromPortalEventBus(event);

		final ArgumentCaptor<DevicesAttachedEvent> captor = ArgumentCaptor.forClass(DevicesAttachedEvent.class);
		verify(eventBus, times(1)).post(captor.capture());
		assertTrue(MessagesHelper.equals(captor.getValue(), newDevicesAttachedEvent(timestamp, RESERVED_NODES)));
	}

	@Test
	public void testSingleNodeProgressShouldBeForwardedIfNodePartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : RESERVED_NODES) {
			final SingleNodeProgress progress =
					MessagesHelper.newSingleNodeProgress(RESERVATION_ID, RANDOM.nextLong(), nodeUrn, 37);
			reservationEventBus.onSingleNodeProgressFromPortalEventBus(progress);
			verify(eventBus).post(eq(progress));
		}
	}

	@Test
	public void testSingleNodeProgressDetachedEventShouldNotBeForwardedIfNodeNotPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : UNRESERVED_NODES) {
			final SingleNodeProgress progress = newSingleNodeProgress(RESERVATION_ID, RANDOM.nextLong(), nodeUrn, 37);
			reservationEventBus.onSingleNodeProgressFromPortalEventBus(progress);
			verify(eventBus, never()).post(eq(progress));
		}
	}

	@Test
	public void testSingleNodeResponseShouldBeForwardedIfNodePartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : RESERVED_NODES) {
			final SingleNodeResponse response =
					newSingleNodeResponse(RESERVATION_ID, RANDOM.nextLong(), nodeUrn, 37, null);
			reservationEventBus.onSingleNodeResponseFromPortalEventBus(response);
			verify(eventBus).post(eq(response));
		}
	}

	@Test
	public void testSingleNodeResponseDetachedEventShouldNotBeForwardedIfNodeNotPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : UNRESERVED_NODES) {
			final SingleNodeResponse response =
					newSingleNodeResponse(RESERVATION_ID, RANDOM.nextLong(), nodeUrn, 37, null);
			reservationEventBus.onSingleNodeResponseFromPortalEventBus(response);
			verify(eventBus, never()).post(eq(response));
		}
	}

	@Test
	public void testNotificationShouldBeForwardedIfNoNodeIsSet() throws Exception {
		final NotificationEvent event = newNotificationEvent("hello");
		reservationEventBus.onNotificationEventFromPortalEventBus(event);
		verify(eventBus).post(eq(event));
	}

	@Test
	public void testNotificationShouldBeForwardedIfNodeIsPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : RESERVED_NODES) {
			final NotificationEvent event = newNotificationEvent(nodeUrn, nodeUrn.toString());
			reservationEventBus.onNotificationEventFromPortalEventBus(event);
			verify(eventBus).post(eq(event));
		}
	}

	@Test
	public void testNotificationShouldNotBeForwardedIfNodeIsNotPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : UNRESERVED_NODES) {
			final NotificationEvent event = newNotificationEvent(nodeUrn, nodeUrn.toString());
			reservationEventBus.onNotificationEventFromPortalEventBus(event);
			verify(eventBus, never()).post(eq(event));
		}
	}

	@Test
	public void testDevicesDetachedEventShouldBeForwardedIfNodePartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : RESERVED_NODES) {
			final DevicesDetachedEvent event = newDevicesDetachedEvent(nodeUrn);
			reservationEventBus.onDevicesDetachedEventFromPortalEventBus(event);
			verify(eventBus).post(eq(event));
		}
	}

	@Test
	public void testDevicesDetachedEventShouldNotBeForwardedIfNodeNotPartOfReservation() throws Exception {
		for (NodeUrn nodeUrn : UNRESERVED_NODES) {
			final DevicesDetachedEvent event = newDevicesDetachedEvent(nodeUrn);
			reservationEventBus.onDevicesDetachedEventFromPortalEventBus(event);
			verify(eventBus, never()).post(eq(event));
		}
	}

	@Test
	public void testDevicesDetachedEventShouldBeFilteredIfSomeNodesArePartOfReservation() throws Exception {

		final long timestamp = new DateTime().getMillis();
		final DevicesDetachedEvent event = newDevicesDetachedEvent(timestamp, ALL_NODES);
		reservationEventBus.onDevicesDetachedEventFromPortalEventBus(event);

		final ArgumentCaptor<DevicesDetachedEvent> captor = ArgumentCaptor.forClass(DevicesDetachedEvent.class);
		verify(eventBus, times(1)).post(captor.capture());
		assertTrue(MessagesHelper.equals(captor.getValue(), newDevicesDetachedEvent(timestamp, RESERVED_NODES)));
	}

	private UpstreamMessageEvent newUpstreamMessageEvent(final NodeUrn sourceNodeUrn, final String message) {
		return UpstreamMessageEvent.newBuilder()
				.setSourceNodeUrn(sourceNodeUrn.toString())
				.setTimestamp(new DateTime().getMillis())
				.setMessageBytes(ByteString.copyFrom(message.getBytes()))
				.build();
	}

	private void postRequestAndVerifyForwarded(final Request request) {
		reservationEventBus.post(request);
		verify(portalEventBus).post(eq(request));
	}

	private void postRequestAndVerifyException(final Request request) {
		try {
			reservationEventBus.post(request);
			fail("An IllegalArgumentException should have been thrown!");
		} catch (IllegalArgumentException expected) {
		}
	}
}
