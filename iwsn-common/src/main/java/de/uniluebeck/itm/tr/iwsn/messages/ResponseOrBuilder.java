// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public interface ResponseOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // required .de.uniluebeck.itm.tr.iwsn.messages.Header header = 1;
  boolean hasHeader();
  de.uniluebeck.itm.tr.iwsn.messages.Header getHeader();
  de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder getHeaderOrBuilder();
  
  // optional bytes response = 2;
  boolean hasResponse();
  com.google.protobuf.ByteString getResponse();
  
  // optional int32 statusCode = 3;
  boolean hasStatusCode();
  int getStatusCode();
  
  // optional string errorMessage = 4;
  boolean hasErrorMessage();
  String getErrorMessage();
}
