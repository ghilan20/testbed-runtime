// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public interface DisableVirtualLinksRequestOrBuilder
    extends com.google.protobuf.MessageOrBuilder {
  
  // required .de.uniluebeck.itm.tr.iwsn.messages.Header header = 1;
  boolean hasHeader();
  de.uniluebeck.itm.tr.iwsn.messages.Header getHeader();
  de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder getHeaderOrBuilder();
  
  // repeated .de.uniluebeck.itm.tr.iwsn.messages.Link links = 2;
  java.util.List<de.uniluebeck.itm.tr.iwsn.messages.Link> 
      getLinksList();
  de.uniluebeck.itm.tr.iwsn.messages.Link getLinks(int index);
  int getLinksCount();
  java.util.List<? extends de.uniluebeck.itm.tr.iwsn.messages.LinkOrBuilder> 
      getLinksOrBuilderList();
  de.uniluebeck.itm.tr.iwsn.messages.LinkOrBuilder getLinksOrBuilder(
      int index);
}
