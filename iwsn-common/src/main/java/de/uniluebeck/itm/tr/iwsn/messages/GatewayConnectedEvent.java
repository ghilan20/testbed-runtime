// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public  final class GatewayConnectedEvent extends
    com.google.protobuf.GeneratedMessage
    implements GatewayConnectedEventOrBuilder {
  // Use GatewayConnectedEvent.newBuilder() to construct.
  private GatewayConnectedEvent(Builder builder) {
    super(builder);
  }
  private GatewayConnectedEvent(boolean noInit) {}
  
  private static final GatewayConnectedEvent defaultInstance;
  public static GatewayConnectedEvent getDefaultInstance() {
    return defaultInstance;
  }
  
  public GatewayConnectedEvent getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_GatewayConnectedEvent_descriptor;
  }
  
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_GatewayConnectedEvent_fieldAccessorTable;
  }
  
  private int bitField0_;
  // required uint64 timestamp = 1;
  public static final int TIMESTAMP_FIELD_NUMBER = 1;
  private long timestamp_;
  public boolean hasTimestamp() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  public long getTimestamp() {
    return timestamp_;
  }
  
  // required string hostname = 2;
  public static final int HOSTNAME_FIELD_NUMBER = 2;
  private java.lang.Object hostname_;
  public boolean hasHostname() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  public String getHostname() {
    java.lang.Object ref = hostname_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      if (com.google.protobuf.Internal.isValidUtf8(bs)) {
        hostname_ = s;
      }
      return s;
    }
  }
  private com.google.protobuf.ByteString getHostnameBytes() {
    java.lang.Object ref = hostname_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8((String) ref);
      hostname_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }
  
  private void initFields() {
    timestamp_ = 0L;
    hostname_ = "";
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;
    
    if (!hasTimestamp()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHostname()) {
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
      output.writeUInt64(1, timestamp_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBytes(2, getHostnameBytes());
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
        .computeUInt64Size(1, timestamp_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, getHostnameBytes());
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
  
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseDelimitedFrom(
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
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  
  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent prototype) {
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
     implements de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_GatewayConnectedEvent_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_GatewayConnectedEvent_fieldAccessorTable;
    }
    
    // Construct using de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent.newBuilder()
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
      timestamp_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      hostname_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    
    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }
    
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent.getDescriptor();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent getDefaultInstanceForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent.getDefaultInstance();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent build() {
      de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }
    
    private de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent buildParsed()
        throws com.google.protobuf.InvalidProtocolBufferException {
      de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(
          result).asInvalidProtocolBufferException();
      }
      return result;
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent buildPartial() {
      de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent result = new de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.timestamp_ = timestamp_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.hostname_ = hostname_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent) {
        return mergeFrom((de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }
    
    public Builder mergeFrom(de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent other) {
      if (other == de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent.getDefaultInstance()) return this;
      if (other.hasTimestamp()) {
        setTimestamp(other.getTimestamp());
      }
      if (other.hasHostname()) {
        setHostname(other.getHostname());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
    
    public final boolean isInitialized() {
      if (!hasTimestamp()) {
        
        return false;
      }
      if (!hasHostname()) {
        
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
            timestamp_ = input.readUInt64();
            break;
          }
          case 18: {
            bitField0_ |= 0x00000002;
            hostname_ = input.readBytes();
            break;
          }
        }
      }
    }
    
    private int bitField0_;
    
    // required uint64 timestamp = 1;
    private long timestamp_ ;
    public boolean hasTimestamp() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public long getTimestamp() {
      return timestamp_;
    }
    public Builder setTimestamp(long value) {
      bitField0_ |= 0x00000001;
      timestamp_ = value;
      onChanged();
      return this;
    }
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000001);
      timestamp_ = 0L;
      onChanged();
      return this;
    }
    
    // required string hostname = 2;
    private java.lang.Object hostname_ = "";
    public boolean hasHostname() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public String getHostname() {
      java.lang.Object ref = hostname_;
      if (!(ref instanceof String)) {
        String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
        hostname_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    public Builder setHostname(String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      hostname_ = value;
      onChanged();
      return this;
    }
    public Builder clearHostname() {
      bitField0_ = (bitField0_ & ~0x00000002);
      hostname_ = getDefaultInstance().getHostname();
      onChanged();
      return this;
    }
    void setHostname(com.google.protobuf.ByteString value) {
      bitField0_ |= 0x00000002;
      hostname_ = value;
      onChanged();
    }
    
    // @@protoc_insertion_point(builder_scope:de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent)
  }
  
  static {
    defaultInstance = new GatewayConnectedEvent(true);
    defaultInstance.initFields();
  }
  
  // @@protoc_insertion_point(class_scope:de.uniluebeck.itm.tr.iwsn.messages.GatewayConnectedEvent)
}

