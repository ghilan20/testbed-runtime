// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public interface EventOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // required int64 eventId = 1;
  boolean hasEventId();
  long getEventId();
  
  // required .de.uniluebeck.itm.tr.iwsn.messages.Event.Type type = 2;
  boolean hasType();
  de.uniluebeck.itm.tr.iwsn.messages.Event.Type getType();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.UpstreamMessageEvent upstreamMessageEvent = 101;
  boolean hasUpstreamMessageEvent();
  de.uniluebeck.itm.tr.iwsn.messages.UpstreamMessageEvent getUpstreamMessageEvent();
  de.uniluebeck.itm.tr.iwsn.messages.UpstreamMessageEventOrBuilder getUpstreamMessageEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent devicesAttachedEvent = 110;
  boolean hasDevicesAttachedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent getDevicesAttachedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEventOrBuilder getDevicesAttachedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.DevicesDetachedEvent devicesDetachedEvent = 111;
  boolean hasDevicesDetachedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DevicesDetachedEvent getDevicesDetachedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DevicesDetachedEventOrBuilder getDevicesDetachedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent gatewayConnectedEvent = 120;
  boolean hasGatewayConnectedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent getGatewayConnectedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEventOrBuilder getGatewayConnectedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.GatewayDisconnectedEvent gatewayDisconnectedEvent = 121;
  boolean hasGatewayDisconnectedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.GatewayDisconnectedEvent getGatewayDisconnectedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.GatewayDisconnectedEventOrBuilder getGatewayDisconnectedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.NotificationEvent notificationEvent = 130;
  boolean hasNotificationEvent();
  de.uniluebeck.itm.tr.iwsn.messages.NotificationEvent getNotificationEvent();
  de.uniluebeck.itm.tr.iwsn.messages.NotificationEventOrBuilder getNotificationEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationStartedEvent reservationStartedEvent = 140;
  boolean hasReservationStartedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationStartedEvent getReservationStartedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationStartedEventOrBuilder getReservationStartedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent reservationEndedEvent = 141;
  boolean hasReservationEndedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent getReservationEndedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEventOrBuilder getReservationEndedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationMadeEvent reservationMadeEvent = 142;
  boolean hasReservationMadeEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationMadeEvent getReservationMadeEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationMadeEventOrBuilder getReservationMadeEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationCancelledEvent reservationCancelledEvent = 143;
  boolean hasReservationCancelledEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationCancelledEvent getReservationCancelledEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationCancelledEventOrBuilder getReservationCancelledEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationOpenedEvent reservationOpenedEvent = 144;
  boolean hasReservationOpenedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationOpenedEvent getReservationOpenedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationOpenedEventOrBuilder getReservationOpenedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationClosedEvent reservationClosedEvent = 145;
  boolean hasReservationClosedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationClosedEvent getReservationClosedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationClosedEventOrBuilder getReservationClosedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.ReservationFinalizedEvent reservationFinalizedEvent = 146;
  boolean hasReservationFinalizedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationFinalizedEvent getReservationFinalizedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.ReservationFinalizedEventOrBuilder getReservationFinalizedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigCreatedEvent deviceConfigCreatedEvent = 150;
  boolean hasDeviceConfigCreatedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigCreatedEvent getDeviceConfigCreatedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigCreatedEventOrBuilder getDeviceConfigCreatedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigUpdatedEvent deviceConfigUpdatedEvent = 151;
  boolean hasDeviceConfigUpdatedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigUpdatedEvent getDeviceConfigUpdatedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigUpdatedEventOrBuilder getDeviceConfigUpdatedEventOrBuilder();
  
  // optional .de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigDeletedEvent deviceConfigDeletedEvent = 152;
  boolean hasDeviceConfigDeletedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigDeletedEvent getDeviceConfigDeletedEvent();
  de.uniluebeck.itm.tr.iwsn.messages.DeviceConfigDeletedEventOrBuilder getDeviceConfigDeletedEventOrBuilder();
}
