package de.uniluebeck.itm.tr.iwsn.messages;

import com.google.common.collect.Multimap;
import com.google.protobuf.ByteString;
import eu.wisebed.api.v3.common.NodeUrn;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Functions.toStringFunction;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static de.uniluebeck.itm.tr.iwsn.common.NodeUrnHelper.NODE_URN_TO_STRING;

public abstract class MessagesHelper {

	public static Message newAreNodesAliveRequestMessage(@Nullable final String reservationId,
														 final long requestId,
														 final Iterable<NodeUrn> nodeUrns) {
		return newMessage(newAreNodesAliveRequest(reservationId, requestId, nodeUrns));
	}

	public static Request newAreNodesAliveRequest(@Nullable final String reservationId,
												  final long requestId,
												  final Iterable<NodeUrn> nodeUrns) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.ARE_NODES_ALIVE)
				.setAreNodesAliveRequest(
						AreNodesAliveRequest.newBuilder().addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Request newAreNodesConnectedRequest(@Nullable final String reservationId,
													  final long requestId,
													  final Iterable<NodeUrn> nodeUrns) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.ARE_NODES_CONNECTED)
				.setAreNodesConnectedRequest(
						AreNodesConnectedRequest.newBuilder().addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newAreNodesConnectedRequestMessage(@Nullable final String reservationId,
															 final long requestId,
															 final Iterable<NodeUrn> nodeUrns) {
		return newMessage(newAreNodesConnectedRequest(reservationId, requestId, nodeUrns));
	}

	public static Message newDisableNodesRequestMessage(@Nullable final String reservationId,
														final long requestId,
														final Iterable<NodeUrn> nodeUrns) {
		return newMessage(newDisableNodesRequest(reservationId, requestId, nodeUrns));
	}

	public static Request newDisableNodesRequest(@Nullable final String reservationId,
												 final long requestId,
												 final Iterable<NodeUrn> nodeUrns) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.DISABLE_NODES)
				.setDisableNodesRequest(
						DisableNodesRequest.newBuilder().addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Request newEnableNodesRequest(@Nullable final String reservationId,
												final long requestId,
												final Iterable<NodeUrn> nodeUrns) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.ENABLE_NODES)
				.setEnableNodesRequest(
						EnableNodesRequest.newBuilder().addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newEnableNodesRequestMessage(@Nullable final String reservationId,
													   final long requestId,
													   final Iterable<NodeUrn> nodeUrns) {
		return newMessage(newEnableNodesRequest(reservationId, requestId, nodeUrns));
	}

	public static Request newResetNodesRequest(@Nullable final String reservationId,
											   final long requestId,
											   final Iterable<NodeUrn> nodeUrns) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.RESET_NODES)
				.setResetNodesRequest(
						ResetNodesRequest.newBuilder().addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newResetNodesRequestMessage(@Nullable final String reservationId,
													  final long requestId,
													  final Iterable<NodeUrn> nodeUrns) {
		return newMessage(newResetNodesRequest(reservationId, requestId, nodeUrns));
	}

	public static Request newSendDownstreamMessageRequest(@Nullable final String reservationId,
														  final long requestId,
														  final Iterable<NodeUrn> nodeUrns,
														  final byte[] bytes) {
		return newSendDownstreamMessageRequest(reservationId, requestId, nodeUrns, ByteString.copyFrom(bytes));
	}

	public static Request newSendDownstreamMessageRequest(@Nullable final String reservationId,
														  final long requestId,
														  final Iterable<NodeUrn> nodeUrns,
														  final ByteString bytes) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.SEND_DOWNSTREAM_MESSAGES)
				.setSendDownstreamMessagesRequest(SendDownstreamMessagesRequest.newBuilder()
						.addAllTargetNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
						.setMessageBytes(bytes)
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newSendDownstreamMessageRequestMessage(@Nullable final String reservationId,
																 final long requestId,
																 final Iterable<NodeUrn> nodeUrns,
																 final byte[] bytes) {
		return newMessage(
				newSendDownstreamMessageRequest(reservationId, requestId, nodeUrns, ByteString.copyFrom(bytes))
		);
	}

	public static Request newFlashImagesRequest(@Nullable final String reservationId,
												final long requestId,
												final Iterable<NodeUrn> nodeUrns,
												final byte[] imageBytes) {
		return newFlashImagesRequest(reservationId, requestId, nodeUrns, ByteString.copyFrom(imageBytes));
	}

	public static Request newFlashImagesRequest(@Nullable final String reservationId,
												final long requestId,
												final Iterable<NodeUrn> nodeUrns,
												final ByteString image) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.FLASH_IMAGES)
				.setFlashImagesRequest(FlashImagesRequest.newBuilder()
						.addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
						.setImage(image)
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newFlashImagesRequestMessage(@Nullable final String reservationId,
													   final long requestId,
													   final Iterable<NodeUrn> nodeUrns,
													   final byte[] imageBytes) {
		return newMessage(newFlashImagesRequest(reservationId, requestId, nodeUrns, ByteString.copyFrom(imageBytes)));
	}

	public static Request newDisableVirtualLinksRequest(@Nullable final String reservationId,
														final long requestId,
														final Multimap<NodeUrn, NodeUrn> links) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.DISABLE_VIRTUAL_LINKS)
				.setDisableVirtualLinksRequest(
						DisableVirtualLinksRequest.newBuilder().addAllLinks(toLinks(links)).build()
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newDisableVirtualLinksRequestMessage(@Nullable final String reservationId,
															   final long requestId,
															   final Multimap<NodeUrn, NodeUrn> links) {
		return newMessage(newDisableVirtualLinksRequest(reservationId, requestId, links));
	}

	public static Request newEnableVirtualLinksRequest(@Nullable final String reservationId,
													   final long requestId,
													   final Multimap<NodeUrn, NodeUrn> links) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.ENABLE_VIRTUAL_LINKS)
				.setEnableVirtualLinksRequest(
						EnableVirtualLinksRequest.newBuilder().addAllLinks(toLinks(links)).build()
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newEnableVirtualLinksRequestMessage(@Nullable final String reservationId,
															  final long requestId,
															  final Multimap<NodeUrn, NodeUrn> links) {
		return newMessage(newEnableVirtualLinksRequest(reservationId, requestId, links));
	}

	public static Request newDisablePhysicalLinksRequest(@Nullable final String reservationId,
														 final long requestId,
														 final Multimap<NodeUrn, NodeUrn> links) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.DISABLE_PHYSICAL_LINKS)
				.setDisablePhysicalLinksRequest(
						DisablePhysicalLinksRequest.newBuilder().addAllLinks(toLinks(links)).build()
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newDisablePhysicalLinksRequestMessage(@Nullable final String reservationId,
																final long requestId,
																final Multimap<NodeUrn, NodeUrn> links) {
		return newMessage(newDisablePhysicalLinksRequest(reservationId, requestId, links));
	}

	public static Request newEnablePhysicalLinksRequest(@Nullable final String reservationId,
														final long requestId,
														final Multimap<NodeUrn, NodeUrn> links) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.ENABLE_PHYSICAL_LINKS)
				.setEnablePhysicalLinksRequest(
						EnablePhysicalLinksRequest.newBuilder().addAllLinks(toLinks(links)).build()
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newEnablePhysicalLinksRequestMessage(@Nullable final String reservationId,
															   final long requestId,
															   final Multimap<NodeUrn, NodeUrn> links) {
		return newMessage(newEnablePhysicalLinksRequest(reservationId, requestId, links));
	}

	public static Request newSetChannelPipelinesRequest(@Nullable final String reservationId,
														final long requestId,
														final Iterable<NodeUrn> nodeUrns,
														final Iterable<? extends SetChannelPipelinesRequest.ChannelHandlerConfiguration> channelHandlerConfigurations) {
		final Request.Builder builder = Request.newBuilder()
				.setRequestId(requestId)
				.setType(Request.Type.SET_CHANNEL_PIPELINES)
				.setSetChannelPipelinesRequest(SetChannelPipelinesRequest.newBuilder()
						.addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
						.addAllChannelHandlerConfigurations(channelHandlerConfigurations)
				);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static Message newSetChannelPipelinesRequestMessage(@Nullable final String reservationId,
															   final long requestId,
															   final Iterable<NodeUrn> nodeUrns,
															   final Iterable<? extends SetChannelPipelinesRequest.ChannelHandlerConfiguration> channelHandlerConfigurations) {
		return newMessage(
				newSetChannelPipelinesRequest(reservationId, requestId, nodeUrns, channelHandlerConfigurations)
		);
	}

	public static Message newMessage(Request request) {
		return Message.newBuilder()
				.setType(Message.Type.REQUEST)
				.setRequest(request)
				.build();
	}

	public static Message newMessage(final SingleNodeProgress progress) {
		return Message.newBuilder()
				.setType(Message.Type.PROGRESS)
				.setProgress(progress)
				.build();
	}

	public static Message newMessage(final SingleNodeResponse response) {
		return Message.newBuilder()
				.setType(Message.Type.RESPONSE)
				.setResponse(response)
				.build();
	}

	public static Message newMessage(final Event event) {
		return Message.newBuilder()
				.setType(Message.Type.EVENT)
				.setEvent(event)
				.build();
	}

	public static Event newEvent(final long eventId, final DevicesAttachedEvent devicesAttachedEvent) {
		return Event.newBuilder()
				.setEventId(eventId)
				.setType(Event.Type.DEVICES_ATTACHED)
				.setDevicesAttachedEvent(devicesAttachedEvent)
				.build();
	}

	public static Event newEvent(final long eventId, final DevicesDetachedEvent devicesDetachedEvent) {
		return Event.newBuilder()
				.setEventId(eventId)
				.setType(Event.Type.DEVICES_DETACHED)
				.setDevicesDetachedEvent(devicesDetachedEvent)
				.build();
	}

	public static Event newEvent(final long eventId, final UpstreamMessageEvent upstreamMessageEvent) {
		return Event.newBuilder()
				.setEventId(eventId)
				.setType(Event.Type.UPSTREAM_MESSAGE)
				.setUpstreamMessageEvent(upstreamMessageEvent)
				.build();
	}

	public static Event newEvent(final long eventId, final NotificationEvent notificationEvent) {
		return Event.newBuilder()
				.setEventId(eventId)
				.setType(Event.Type.NOTIFICATION)
				.setNotificationEvent(notificationEvent)
				.build();
	}

	private static Iterable<Link> toLinks(final Multimap<NodeUrn, NodeUrn> linkMap) {
		List<Link> links = newArrayList();
		for (NodeUrn sourceNodeUrn : linkMap.keySet()) {
			for (NodeUrn targetNodeUrn : linkMap.get(sourceNodeUrn)) {
				links.add(Link.newBuilder()
						.setSourceNodeUrn(sourceNodeUrn.toString())
						.setTargetNodeUrn(targetNodeUrn.toString())
						.build()
				);
			}
		}
		return links;
	}

	public static DevicesAttachedEvent newDevicesAttachedEvent(final long timestamp, final Iterable<NodeUrn> nodeUrns) {
		checkArgument(!isEmpty(nodeUrns));
		return DevicesAttachedEvent.newBuilder()
				.addAllNodeUrns(transform(nodeUrns, toStringFunction()))
				.setTimestamp(timestamp)
				.build();
	}

	public static DevicesAttachedEvent newDevicesAttachedEvent(final Iterable<NodeUrn> nodeUrns) {
		return newDevicesAttachedEvent(new DateTime().getMillis(), nodeUrns);
	}

	public static DevicesAttachedEvent newDevicesAttachedEvent(final long timestamp, final NodeUrn nodeUrn,
															   final NodeUrn... nodeUrns) {
		checkNotNull(nodeUrn);
		final DevicesAttachedEvent.Builder builder = DevicesAttachedEvent.newBuilder();
		builder.addNodeUrns(nodeUrn.toString());
		for (NodeUrn anotherNodeUrn : nodeUrns) {
			builder.addNodeUrns(anotherNodeUrn.toString());
		}
		return builder.setTimestamp(timestamp).build();
	}

	public static DevicesAttachedEvent newDevicesAttachedEvent(final NodeUrn nodeUrn, final NodeUrn... nodeUrns) {
		return newDevicesAttachedEvent(new DateTime().getMillis(), nodeUrn, nodeUrns);
	}

	public static DevicesDetachedEvent newDevicesDetachedEvent(final long timestamp, final Iterable<NodeUrn> nodeUrns) {
		checkArgument(!isEmpty(nodeUrns));
		return DevicesDetachedEvent.newBuilder()
				.addAllNodeUrns(transform(nodeUrns, NODE_URN_TO_STRING))
				.setTimestamp(timestamp)
				.build();
	}

	public static DevicesDetachedEvent newDevicesDetachedEvent(final Iterable<NodeUrn> nodeUrns) {
		return newDevicesDetachedEvent(new DateTime().getMillis(), nodeUrns);
	}

	public static DevicesDetachedEvent newDevicesDetachedEvent(final long timestamp, final NodeUrn nodeUrn,
															   final NodeUrn... nodeUrns) {
		checkNotNull(nodeUrn);
		final DevicesDetachedEvent.Builder builder = DevicesDetachedEvent.newBuilder();
		builder.addNodeUrns(nodeUrn.toString());
		for (NodeUrn anotherNodeUrn : nodeUrns) {
			builder.addNodeUrns(anotherNodeUrn.toString());
		}
		return builder.setTimestamp(timestamp).build();
	}

	public static DevicesDetachedEvent newDevicesDetachedEvent(final NodeUrn nodeUrn, final NodeUrn... nodeUrns) {
		return newDevicesDetachedEvent(new DateTime().getMillis(), nodeUrn, nodeUrns);
	}

	public static boolean equals(final DevicesAttachedEvent event1, final DevicesAttachedEvent event2) {
		return event1.getTimestamp() == event2.getTimestamp() &&
				newHashSet(event1.getNodeUrnsList()).equals(newHashSet(event2.getNodeUrnsList()));
	}

	public static boolean equals(final DevicesDetachedEvent event1, final DevicesDetachedEvent event2) {
		return event1.getTimestamp() == event2.getTimestamp() &&
				newHashSet(event1.getNodeUrnsList()).equals(newHashSet(event2.getNodeUrnsList()));
	}

	public static NotificationEvent newNotificationEvent(@Nullable final NodeUrn nodeUrn,
														 final String message) {
		return newNotificationEvent(nodeUrn, new DateTime().getMillis(), message);
	}

	public static NotificationEvent newNotificationEvent(@Nullable final NodeUrn nodeUrn,
														 final long timestamp,
														 final String message) {

		final NotificationEvent.Builder builder = NotificationEvent.newBuilder();

		if (nodeUrn != null) {
			builder.setNodeUrn(nodeUrn.toString());
		}

		return builder
				.setTimestamp(timestamp)
				.setMessage(message)
				.build();
	}

	public static NotificationEvent newNotificationEvent(final String message) {
		return newNotificationEvent(null, new DateTime().getMillis(), message);
	}

	public static SingleNodeProgress newSingleNodeProgress(@Nullable final String reservationId,
														   final long requestId,
														   final NodeUrn nodeUrn,
														   final int progressInPercent) {
		checkArgument(
				progressInPercent >= 0 && progressInPercent <= 100,
				"A progress in percent can only be between 0 and 100 (actual value: " + progressInPercent + ")"
		);

		final SingleNodeProgress.Builder builder = SingleNodeProgress.newBuilder()
				.setRequestId(requestId)
				.setNodeUrn(nodeUrn.toString())
				.setProgressInPercent(progressInPercent);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		return builder.build();
	}

	public static SingleNodeResponse newSingleNodeResponse(@Nullable final String reservationId,
														   final long requestId,
														   final NodeUrn nodeUrn,
														   final int statusCode,
														   @Nullable final String errorMessage) {
		final SingleNodeResponse.Builder builder = SingleNodeResponse.newBuilder()
				.setRequestId(requestId)
				.setNodeUrn(nodeUrn.toString())
				.setStatusCode(statusCode);

		if (reservationId != null) {
			builder.setReservationId(reservationId);
		}

		if (errorMessage != null) {
			builder.setErrorMessage(errorMessage);
		}

		return builder.build();
	}
}
