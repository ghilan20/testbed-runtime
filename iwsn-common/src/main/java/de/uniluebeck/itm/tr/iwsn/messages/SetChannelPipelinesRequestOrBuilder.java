// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public interface SetChannelPipelinesRequestOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // required .de.uniluebeck.itm.tr.iwsn.messages.Header header = 1;
  boolean hasHeader();
  de.uniluebeck.itm.tr.iwsn.messages.Header getHeader();
  de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder getHeaderOrBuilder();
  
  // repeated .de.uniluebeck.itm.tr.iwsn.messages.ChannelHandlerConfiguration channelHandlerConfigurations = 2;
  java.util.List<de.uniluebeck.itm.tr.iwsn.messages.ChannelHandlerConfiguration> 
      getChannelHandlerConfigurationsList();
  de.uniluebeck.itm.tr.iwsn.messages.ChannelHandlerConfiguration getChannelHandlerConfigurations(int index);
  int getChannelHandlerConfigurationsCount();
  java.util.List<? extends de.uniluebeck.itm.tr.iwsn.messages.ChannelHandlerConfigurationOrBuilder> 
      getChannelHandlerConfigurationsOrBuilderList();
  de.uniluebeck.itm.tr.iwsn.messages.ChannelHandlerConfigurationOrBuilder getChannelHandlerConfigurationsOrBuilder(
      int index);
}
