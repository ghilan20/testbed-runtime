// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public interface SingleNodeProgressOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // required .de.uniluebeck.itm.tr.iwsn.messages.RequestResponseHeader header = 1;
  boolean hasHeader();
  de.uniluebeck.itm.tr.iwsn.messages.RequestResponseHeader getHeader();
  de.uniluebeck.itm.tr.iwsn.messages.RequestResponseHeaderOrBuilder getHeaderOrBuilder();
  
  // required uint32 progressInPercent = 3;
  boolean hasProgressInPercent();
  int getProgressInPercent();
}
