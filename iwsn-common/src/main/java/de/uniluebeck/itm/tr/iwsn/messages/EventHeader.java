// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public  final class EventHeader extends
    com.google.protobuf.GeneratedMessage
    implements EventHeaderOrBuilder {
  // Use EventHeader.newBuilder() to construct.
  private EventHeader(Builder builder) {
    super(builder);
  }
  private EventHeader(boolean noInit) {}
  
  private static final EventHeader defaultInstance;
  public static EventHeader getDefaultInstance() {
    return defaultInstance;
  }
  
  public EventHeader getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_EventHeader_descriptor;
  }
  
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_EventHeader_fieldAccessorTable;
  }
  
  private int bitField0_;
  // required int64 eventId = 1;
  public static final int EVENTID_FIELD_NUMBER = 1;
  private long eventId_;
  public boolean hasEventId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  public long getEventId() {
    return eventId_;
  }
  
  // required uint64 timestamp = 2;
  public static final int TIMESTAMP_FIELD_NUMBER = 2;
  private long timestamp_;
  public boolean hasTimestamp() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  public long getTimestamp() {
    return timestamp_;
  }
  
  // repeated string nodeUrns = 3;
  public static final int NODEURNS_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList nodeUrns_;
  public java.util.List<String>
      getNodeUrnsList() {
    return nodeUrns_;
  }
  public int getNodeUrnsCount() {
    return nodeUrns_.size();
  }
  public String getNodeUrns(int index) {
    return nodeUrns_.get(index);
  }
  
  // optional bool downstream = 5 [default = false];
  public static final int DOWNSTREAM_FIELD_NUMBER = 5;
  private boolean downstream_;
  public boolean hasDownstream() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  public boolean getDownstream() {
    return downstream_;
  }
  
  // optional bool upstream = 6 [default = true];
  public static final int UPSTREAM_FIELD_NUMBER = 6;
  private boolean upstream_;
  public boolean hasUpstream() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  public boolean getUpstream() {
    return upstream_;
  }
  
  private void initFields() {
    eventId_ = 0L;
    timestamp_ = 0L;
    nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    downstream_ = false;
    upstream_ = true;
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;
    
    if (!hasEventId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasTimestamp()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }
  
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, eventId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeUInt64(2, timestamp_);
    }
    for (int i = 0; i < nodeUrns_.size(); i++) {
      output.writeBytes(3, nodeUrns_.getByteString(i));
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeBool(5, downstream_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeBool(6, upstream_);
    }
    getUnknownFields().writeTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;
  
    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, eventId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, timestamp_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < nodeUrns_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(nodeUrns_.getByteString(i));
      }
      size += dataSize;
      size += 1 * getNodeUrnsList().size();
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(5, downstream_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(6, upstream_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }
  
  private static final long serialVersionUID = 0L;
  @java.lang.Override
  protected java.lang.Object writeReplace()
      throws java.io.ObjectStreamException {
    return super.writeReplace();
  }
  
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.EventHeader parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  
  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(de.uniluebeck.itm.tr.iwsn.messages.EventHeader prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }
  
  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder>
     implements de.uniluebeck.itm.tr.iwsn.messages.EventHeaderOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_EventHeader_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_EventHeader_fieldAccessorTable;
    }
    
    // Construct using de.uniluebeck.itm.tr.iwsn.messages.EventHeader.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    private static Builder create() {
      return new Builder();
    }
    
    public Builder clear() {
      super.clear();
      eventId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      timestamp_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      downstream_ = false;
      bitField0_ = (bitField0_ & ~0x00000008);
      upstream_ = true;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }
    
    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }
    
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.EventHeader.getDescriptor();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.EventHeader getDefaultInstanceForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.EventHeader.getDefaultInstance();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.EventHeader build() {
      de.uniluebeck.itm.tr.iwsn.messages.EventHeader result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }
    
    private de.uniluebeck.itm.tr.iwsn.messages.EventHeader buildParsed()
        throws com.google.protobuf.InvalidProtocolBufferException {
      de.uniluebeck.itm.tr.iwsn.messages.EventHeader result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(
          result).asInvalidProtocolBufferException();
      }
      return result;
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.EventHeader buildPartial() {
      de.uniluebeck.itm.tr.iwsn.messages.EventHeader result = new de.uniluebeck.itm.tr.iwsn.messages.EventHeader(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.eventId_ = eventId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.timestamp_ = timestamp_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        nodeUrns_ = new com.google.protobuf.UnmodifiableLazyStringList(
            nodeUrns_);
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.nodeUrns_ = nodeUrns_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000004;
      }
      result.downstream_ = downstream_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000008;
      }
      result.upstream_ = upstream_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof de.uniluebeck.itm.tr.iwsn.messages.EventHeader) {
        return mergeFrom((de.uniluebeck.itm.tr.iwsn.messages.EventHeader)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }
    
    public Builder mergeFrom(de.uniluebeck.itm.tr.iwsn.messages.EventHeader other) {
      if (other == de.uniluebeck.itm.tr.iwsn.messages.EventHeader.getDefaultInstance()) return this;
      if (other.hasEventId()) {
        setEventId(other.getEventId());
      }
      if (other.hasTimestamp()) {
        setTimestamp(other.getTimestamp());
      }
      if (!other.nodeUrns_.isEmpty()) {
        if (nodeUrns_.isEmpty()) {
          nodeUrns_ = other.nodeUrns_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureNodeUrnsIsMutable();
          nodeUrns_.addAll(other.nodeUrns_);
        }
        onChanged();
      }
      if (other.hasDownstream()) {
        setDownstream(other.getDownstream());
      }
      if (other.hasUpstream()) {
        setUpstream(other.getUpstream());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
    
    public final boolean isInitialized() {
      if (!hasEventId()) {
        
        return false;
      }
      if (!hasTimestamp()) {
        
        return false;
      }
      return true;
    }
    
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder(
          this.getUnknownFields());
      while (true) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            this.setUnknownFields(unknownFields.build());
            onChanged();
            return this;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            eventId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            timestamp_ = input.readUInt64();
            break;
          }
          case 26: {
            ensureNodeUrnsIsMutable();
            nodeUrns_.add(input.readBytes());
            break;
          }
          case 40: {
            bitField0_ |= 0x00000008;
            downstream_ = input.readBool();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000010;
            upstream_ = input.readBool();
            break;
          }
        }
      }
    }
    
    private int bitField0_;
    
    // required int64 eventId = 1;
    private long eventId_ ;
    public boolean hasEventId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public long getEventId() {
      return eventId_;
    }
    public Builder setEventId(long value) {
      bitField0_ |= 0x00000001;
      eventId_ = value;
      onChanged();
      return this;
    }
    public Builder clearEventId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      eventId_ = 0L;
      onChanged();
      return this;
    }
    
    // required uint64 timestamp = 2;
    private long timestamp_ ;
    public boolean hasTimestamp() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public long getTimestamp() {
      return timestamp_;
    }
    public Builder setTimestamp(long value) {
      bitField0_ |= 0x00000002;
      timestamp_ = value;
      onChanged();
      return this;
    }
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000002);
      timestamp_ = 0L;
      onChanged();
      return this;
    }
    
    // repeated string nodeUrns = 3;
    private com.google.protobuf.LazyStringList nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureNodeUrnsIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        nodeUrns_ = new com.google.protobuf.LazyStringArrayList(nodeUrns_);
        bitField0_ |= 0x00000004;
       }
    }
    public java.util.List<String>
        getNodeUrnsList() {
      return java.util.Collections.unmodifiableList(nodeUrns_);
    }
    public int getNodeUrnsCount() {
      return nodeUrns_.size();
    }
    public String getNodeUrns(int index) {
      return nodeUrns_.get(index);
    }
    public Builder setNodeUrns(
        int index, String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNodeUrnsIsMutable();
      nodeUrns_.set(index, value);
      onChanged();
      return this;
    }
    public Builder addNodeUrns(String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureNodeUrnsIsMutable();
      nodeUrns_.add(value);
      onChanged();
      return this;
    }
    public Builder addAllNodeUrns(
        java.lang.Iterable<String> values) {
      ensureNodeUrnsIsMutable();
      super.addAll(values, nodeUrns_);
      onChanged();
      return this;
    }
    public Builder clearNodeUrns() {
      nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    void addNodeUrns(com.google.protobuf.ByteString value) {
      ensureNodeUrnsIsMutable();
      nodeUrns_.add(value);
      onChanged();
    }
    
    // optional bool downstream = 5 [default = false];
    private boolean downstream_ ;
    public boolean hasDownstream() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public boolean getDownstream() {
      return downstream_;
    }
    public Builder setDownstream(boolean value) {
      bitField0_ |= 0x00000008;
      downstream_ = value;
      onChanged();
      return this;
    }
    public Builder clearDownstream() {
      bitField0_ = (bitField0_ & ~0x00000008);
      downstream_ = false;
      onChanged();
      return this;
    }
    
    // optional bool upstream = 6 [default = true];
    private boolean upstream_ = true;
    public boolean hasUpstream() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public boolean getUpstream() {
      return upstream_;
    }
    public Builder setUpstream(boolean value) {
      bitField0_ |= 0x00000010;
      upstream_ = value;
      onChanged();
      return this;
    }
    public Builder clearUpstream() {
      bitField0_ = (bitField0_ & ~0x00000010);
      upstream_ = true;
      onChanged();
      return this;
    }
    
    // @@protoc_insertion_point(builder_scope:de.uniluebeck.itm.tr.iwsn.messages.EventHeader)
  }
  
  static {
    defaultInstance = new EventHeader(true);
    defaultInstance.initFields();
  }
  
  // @@protoc_insertion_point(class_scope:de.uniluebeck.itm.tr.iwsn.messages.EventHeader)
}

