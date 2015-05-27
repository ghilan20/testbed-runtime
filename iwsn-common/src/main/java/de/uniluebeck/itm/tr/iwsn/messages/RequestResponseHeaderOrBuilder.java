// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public interface RequestResponseHeaderOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // optional string reservationId = 1;
  boolean hasReservationId();
  String getReservationId();
  
  // required int64 requestId = 2;
  boolean hasRequestId();
  long getRequestId();
  
  // required uint64 timestamp = 3;
  boolean hasTimestamp();
  long getTimestamp();
  
  // repeated string nodeUrns = 4;
  java.util.List<String> getNodeUrnsList();
  int getNodeUrnsCount();
  String getNodeUrns(int index);
  
  // optional bool downstream = 5 [default = true];
  boolean hasDownstream();
  boolean getDownstream();
  
  // optional bool upstream = 6 [default = false];
  boolean hasUpstream();
  boolean getUpstream();
}