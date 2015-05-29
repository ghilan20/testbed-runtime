// Generated by the protocol buffer compiler.  DO NOT EDIT!

package de.uniluebeck.itm.tr.iwsn.messages;

public  final class ReservationEndedEvent extends
    com.google.protobuf.GeneratedMessage
    implements ReservationEndedEventOrBuilder {
  // Use ReservationEndedEvent.newBuilder() to construct.
  private ReservationEndedEvent(Builder builder) {
    super(builder);
  }
  private ReservationEndedEvent(boolean noInit) {}
  
  private static final ReservationEndedEvent defaultInstance;
  public static ReservationEndedEvent getDefaultInstance() {
    return defaultInstance;
  }
  
  public ReservationEndedEvent getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEndedEvent_descriptor;
  }
  
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEndedEvent_fieldAccessorTable;
  }
  
  private int bitField0_;
  // required .de.uniluebeck.itm.tr.iwsn.messages.Header header = 1;
  public static final int HEADER_FIELD_NUMBER = 1;
  private de.uniluebeck.itm.tr.iwsn.messages.Header header_;
  public boolean hasHeader() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  public de.uniluebeck.itm.tr.iwsn.messages.Header getHeader() {
    return header_;
  }
  public de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder getHeaderOrBuilder() {
    return header_;
  }
  
  private void initFields() {
    header_ = de.uniluebeck.itm.tr.iwsn.messages.Header.getDefaultInstance();
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;
    
    if (!hasHeader()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getHeader().isInitialized()) {
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
      output.writeMessage(1, header_);
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
        .computeMessageSize(1, header_);
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
  
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseDelimitedFrom(
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
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  
  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent prototype) {
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
     implements de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEndedEvent_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return de.uniluebeck.itm.tr.iwsn.messages.Messages.internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEndedEvent_fieldAccessorTable;
    }
    
    // Construct using de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        getHeaderFieldBuilder();
      }
    }
    private static Builder create() {
      return new Builder();
    }
    
    public Builder clear() {
      super.clear();
      if (headerBuilder_ == null) {
        header_ = de.uniluebeck.itm.tr.iwsn.messages.Header.getDefaultInstance();
      } else {
        headerBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    
    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }
    
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent.getDescriptor();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent getDefaultInstanceForType() {
      return de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent.getDefaultInstance();
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent build() {
      de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }
    
    private de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent buildParsed()
        throws com.google.protobuf.InvalidProtocolBufferException {
      de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(
          result).asInvalidProtocolBufferException();
      }
      return result;
    }
    
    public de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent buildPartial() {
      de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent result = new de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (headerBuilder_ == null) {
        result.header_ = header_;
      } else {
        result.header_ = headerBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent) {
        return mergeFrom((de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }
    
    public Builder mergeFrom(de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent other) {
      if (other == de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent.getDefaultInstance()) return this;
      if (other.hasHeader()) {
        mergeHeader(other.getHeader());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
    
    public final boolean isInitialized() {
      if (!hasHeader()) {
        
        return false;
      }
      if (!getHeader().isInitialized()) {
        
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
            de.uniluebeck.itm.tr.iwsn.messages.Header.Builder subBuilder = de.uniluebeck.itm.tr.iwsn.messages.Header.newBuilder();
            if (hasHeader()) {
              subBuilder.mergeFrom(getHeader());
            }
            input.readMessage(subBuilder, extensionRegistry);
            setHeader(subBuilder.buildPartial());
            break;
          }
        }
      }
    }
    
    private int bitField0_;
    
    // required .de.uniluebeck.itm.tr.iwsn.messages.Header header = 1;
    private de.uniluebeck.itm.tr.iwsn.messages.Header header_ = de.uniluebeck.itm.tr.iwsn.messages.Header.getDefaultInstance();
    private com.google.protobuf.SingleFieldBuilder<
        de.uniluebeck.itm.tr.iwsn.messages.Header, de.uniluebeck.itm.tr.iwsn.messages.Header.Builder, de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder> headerBuilder_;
    public boolean hasHeader() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public de.uniluebeck.itm.tr.iwsn.messages.Header getHeader() {
      if (headerBuilder_ == null) {
        return header_;
      } else {
        return headerBuilder_.getMessage();
      }
    }
    public Builder setHeader(de.uniluebeck.itm.tr.iwsn.messages.Header value) {
      if (headerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        header_ = value;
        onChanged();
      } else {
        headerBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    public Builder setHeader(
        de.uniluebeck.itm.tr.iwsn.messages.Header.Builder builderForValue) {
      if (headerBuilder_ == null) {
        header_ = builderForValue.build();
        onChanged();
      } else {
        headerBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    public Builder mergeHeader(de.uniluebeck.itm.tr.iwsn.messages.Header value) {
      if (headerBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            header_ != de.uniluebeck.itm.tr.iwsn.messages.Header.getDefaultInstance()) {
          header_ =
            de.uniluebeck.itm.tr.iwsn.messages.Header.newBuilder(header_).mergeFrom(value).buildPartial();
        } else {
          header_ = value;
        }
        onChanged();
      } else {
        headerBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    public Builder clearHeader() {
      if (headerBuilder_ == null) {
        header_ = de.uniluebeck.itm.tr.iwsn.messages.Header.getDefaultInstance();
        onChanged();
      } else {
        headerBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    public de.uniluebeck.itm.tr.iwsn.messages.Header.Builder getHeaderBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getHeaderFieldBuilder().getBuilder();
    }
    public de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder getHeaderOrBuilder() {
      if (headerBuilder_ != null) {
        return headerBuilder_.getMessageOrBuilder();
      } else {
        return header_;
      }
    }
    private com.google.protobuf.SingleFieldBuilder<
        de.uniluebeck.itm.tr.iwsn.messages.Header, de.uniluebeck.itm.tr.iwsn.messages.Header.Builder, de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder> 
        getHeaderFieldBuilder() {
      if (headerBuilder_ == null) {
        headerBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            de.uniluebeck.itm.tr.iwsn.messages.Header, de.uniluebeck.itm.tr.iwsn.messages.Header.Builder, de.uniluebeck.itm.tr.iwsn.messages.HeaderOrBuilder>(
                header_,
                getParentForChildren(),
                isClean());
        header_ = null;
      }
      return headerBuilder_;
    }
    
    // @@protoc_insertion_point(builder_scope:de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent)
  }
  
  static {
    defaultInstance = new ReservationEndedEvent(true);
    defaultInstance.initFields();
  }
  
  // @@protoc_insertion_point(class_scope:de.uniluebeck.itm.tr.iwsn.messages.ReservationEndedEvent)
}

