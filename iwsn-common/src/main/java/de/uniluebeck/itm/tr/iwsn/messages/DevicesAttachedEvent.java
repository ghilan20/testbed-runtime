// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public  final class DevicesAttachedEvent extends
    com.google.protobuf.GeneratedMessage
    implements DevicesAttachedEventOrBuilder {
  // Use DevicesAttachedEvent.newBuilder() to construct.
  private DevicesAttachedEvent(Builder builder) {
    super(builder);
  }
  private DevicesAttachedEvent(boolean noInit) {}
  
  private static final DevicesAttachedEvent defaultInstance;
  public static DevicesAttachedEvent getDefaultInstance() {
    return defaultInstance;
  }
  
  public DevicesAttachedEvent getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_DevicesAttachedEvent_descriptor;
  }
  
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_DevicesAttachedEvent_fieldAccessorTable;
  }
  
  private int bitField0_;
  // repeated string nodeUrns = 1;
  public static final int NODEURNS_FIELD_NUMBER = 1;
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
  
  // required uint64 timestamp = 2;
  public static final int TIMESTAMP_FIELD_NUMBER = 2;
  private long timestamp_;
  public boolean hasTimestamp() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  public long getTimestamp() {
    return timestamp_;
  }
  
  private void initFields() {
    nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    timestamp_ = 0L;
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;
    
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
    for (int i = 0; i < nodeUrns_.size(); i++) {
      output.writeBytes(1, nodeUrns_.getByteString(i));
    }
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeUInt64(2, timestamp_);
    }
    getUnknownFields().writeTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;
  
    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < nodeUrns_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(nodeUrns_.getByteString(i));
      }
      size += dataSize;
      size += 1 * getNodeUrnsList().size();
    }
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, timestamp_);
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
  
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseDelimitedFrom(
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
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  
  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent prototype) {
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
     implements de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_DevicesAttachedEvent_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_DevicesAttachedEvent_fieldAccessorTable;
    }
    
    // Construct using de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent.newBuilder()
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
      nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      timestamp_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    
    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }
    
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent.getDescriptor();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent getDefaultInstanceForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent.getDefaultInstance();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent build() {
      de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }
    
    private de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent buildParsed()
        throws com.google.protobuf.InvalidProtocolBufferException {
      de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(
          result).asInvalidProtocolBufferException();
      }
      return result;
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent buildPartial() {
      de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent result = new de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        nodeUrns_ = new com.google.protobuf.UnmodifiableLazyStringList(
            nodeUrns_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.nodeUrns_ = nodeUrns_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000001;
      }
      result.timestamp_ = timestamp_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent) {
        return mergeFrom((de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }
    
    public Builder mergeFrom(de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent other) {
      if (other == de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent.getDefaultInstance()) return this;
      if (!other.nodeUrns_.isEmpty()) {
        if (nodeUrns_.isEmpty()) {
          nodeUrns_ = other.nodeUrns_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureNodeUrnsIsMutable();
          nodeUrns_.addAll(other.nodeUrns_);
        }
        onChanged();
      }
      if (other.hasTimestamp()) {
        setTimestamp(other.getTimestamp());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
    
    public final boolean isInitialized() {
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
          case 10: {
            ensureNodeUrnsIsMutable();
            nodeUrns_.add(input.readBytes());
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            timestamp_ = input.readUInt64();
            break;
          }
        }
      }
    }
    
    private int bitField0_;
    
    // repeated string nodeUrns = 1;
    private com.google.protobuf.LazyStringList nodeUrns_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureNodeUrnsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        nodeUrns_ = new com.google.protobuf.LazyStringArrayList(nodeUrns_);
        bitField0_ |= 0x00000001;
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
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    void addNodeUrns(com.google.protobuf.ByteString value) {
      ensureNodeUrnsIsMutable();
      nodeUrns_.add(value);
      onChanged();
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
    
    // @@protoc_insertion_point(builder_scope:de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent)
  }
  
  static {
    defaultInstance = new DevicesAttachedEvent(true);
    defaultInstance.initFields();
  }
  
  // @@protoc_insertion_point(class_scope:de.uniluebeck.itm.tr.iwsn.messages.DevicesAttachedEvent)
}

