// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/internal-messages.proto

package de.uniluebeck.itm.tr.iwsn.messages;

public final class InternalMessages {
  private InternalMessages() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEvent_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEvent_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_de_uniluebeck_itm_tr_iwsn_messages_InternalMessage_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_de_uniluebeck_itm_tr_iwsn_messages_InternalMessage_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n*src/main/resources/internal-messages.p" +
      "roto\022\"de.uniluebeck.itm.tr.iwsn.messages" +
      "\"\332\001\n\020ReservationEvent\022G\n\004type\030\001 \002(\01629.de" +
      ".uniluebeck.itm.tr.iwsn.messages.Reserva" +
      "tionEvent.Type\022\013\n\003key\030\002 \002(\t\022\020\n\010username\030" +
      "\003 \002(\t\022\020\n\010nodeUrns\030\004 \003(\t\022\026\n\016interval_star" +
      "t\030\005 \002(\t\022\024\n\014interval_end\030\006 \002(\t\"\036\n\004Type\022\013\n" +
      "\007STARTED\020\001\022\t\n\005ENDED\020\002\"\310\001\n\017InternalMessag" +
      "e\022F\n\004type\030\001 \002(\01628.de.uniluebeck.itm.tr.i" +
      "wsn.messages.InternalMessage.Type\022N\n\020res",
      "ervationEvent\030e \001(\01324.de.uniluebeck.itm." +
      "tr.iwsn.messages.ReservationEvent\"\035\n\004Typ" +
      "e\022\025\n\021RESERVATION_EVENT\020\001B\024B\020InternalMess" +
      "agesP\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEvent_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEvent_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_de_uniluebeck_itm_tr_iwsn_messages_ReservationEvent_descriptor,
              new java.lang.String[] { "Type", "Key", "Username", "NodeUrns", "IntervalStart", "IntervalEnd", },
              de.uniluebeck.itm.tr.iwsn.messages.ReservationEvent.class,
              de.uniluebeck.itm.tr.iwsn.messages.ReservationEvent.Builder.class);
          internal_static_de_uniluebeck_itm_tr_iwsn_messages_InternalMessage_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_de_uniluebeck_itm_tr_iwsn_messages_InternalMessage_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_de_uniluebeck_itm_tr_iwsn_messages_InternalMessage_descriptor,
              new java.lang.String[] { "Type", "ReservationEvent", },
              de.uniluebeck.itm.tr.iwsn.messages.InternalMessage.class,
              de.uniluebeck.itm.tr.iwsn.messages.InternalMessage.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
