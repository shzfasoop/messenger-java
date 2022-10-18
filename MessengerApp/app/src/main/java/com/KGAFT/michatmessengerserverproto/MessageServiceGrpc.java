package com.KGAFT.michatmessengerserverproto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: MessageService.proto")
public final class MessageServiceGrpc {

  private MessageServiceGrpc() {}

  public static final String SERVICE_NAME = "com.KGAFT.michatmessengerserverproto.MessageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest,
      com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest.class,
      responseType = com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest,
      com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse> getSendMessageMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest, com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse> getSendMessageMethod;
    if ((getSendMessageMethod = MessageServiceGrpc.getSendMessageMethod) == null) {
      synchronized (MessageServiceGrpc.class) {
        if ((getSendMessageMethod = MessageServiceGrpc.getSendMessageMethod) == null) {
          MessageServiceGrpc.getSendMessageMethod = getSendMessageMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest, com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MessageServiceMethodDescriptorSupplier("sendMessage"))
              .build();
        }
      }
    }
    return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest,
      com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> getConnectToNotificationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "connectToNotifications",
      requestType = com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest.class,
      responseType = com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest,
      com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> getConnectToNotificationsMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest, com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> getConnectToNotificationsMethod;
    if ((getConnectToNotificationsMethod = MessageServiceGrpc.getConnectToNotificationsMethod) == null) {
      synchronized (MessageServiceGrpc.class) {
        if ((getConnectToNotificationsMethod = MessageServiceGrpc.getConnectToNotificationsMethod) == null) {
          MessageServiceGrpc.getConnectToNotificationsMethod = getConnectToNotificationsMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest, com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "connectToNotifications"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MessageServiceMethodDescriptorSupplier("connectToNotifications"))
              .build();
        }
      }
    }
    return getConnectToNotificationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest,
      com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> getConnectToMessageThreadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "connectToMessageThread",
      requestType = com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest.class,
      responseType = com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest,
      com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> getConnectToMessageThreadMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest, com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> getConnectToMessageThreadMethod;
    if ((getConnectToMessageThreadMethod = MessageServiceGrpc.getConnectToMessageThreadMethod) == null) {
      synchronized (MessageServiceGrpc.class) {
        if ((getConnectToMessageThreadMethod = MessageServiceGrpc.getConnectToMessageThreadMethod) == null) {
          MessageServiceGrpc.getConnectToMessageThreadMethod = getConnectToMessageThreadMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest, com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "connectToMessageThread"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MessageServiceMethodDescriptorSupplier("connectToMessageThread"))
              .build();
        }
      }
    }
    return getConnectToMessageThreadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessageServiceStub newStub(io.grpc.Channel channel) {
    return new MessageServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MessageServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MessageServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MessageServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    public void connectToNotifications(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectToNotificationsMethod(), responseObserver);
    }

    /**
     */
    public void connectToMessageThread(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectToMessageThreadMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest,
                com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getConnectToNotificationsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest,
                com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse>(
                  this, METHODID_CONNECT_TO_NOTIFICATIONS)))
          .addMethod(
            getConnectToMessageThreadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest,
                com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse>(
                  this, METHODID_CONNECT_TO_MESSAGE_THREAD)))
          .build();
    }
  }

  /**
   */
  public static final class MessageServiceStub extends io.grpc.stub.AbstractStub<MessageServiceStub> {
    private MessageServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MessageServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void connectToNotifications(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getConnectToNotificationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void connectToMessageThread(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getConnectToMessageThreadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MessageServiceBlockingStub extends io.grpc.stub.AbstractStub<MessageServiceBlockingStub> {
    private MessageServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MessageServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse sendMessage(com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> connectToNotifications(
        com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getConnectToNotificationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse> connectToMessageThread(
        com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getConnectToMessageThreadMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessageServiceFutureStub extends io.grpc.stub.AbstractStub<MessageServiceFutureStub> {
    private MessageServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MessageServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse> sendMessage(
        com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;
  private static final int METHODID_CONNECT_TO_NOTIFICATIONS = 1;
  private static final int METHODID_CONNECT_TO_MESSAGE_THREAD = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessageServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessageServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageAcceptedResponse>) responseObserver);
          break;
        case METHODID_CONNECT_TO_NOTIFICATIONS:
          serviceImpl.connectToNotifications((com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationNotificationRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse>) responseObserver);
          break;
        case METHODID_CONNECT_TO_MESSAGE_THREAD:
          serviceImpl.connectToMessageThread((com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.authorizationChatRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.MessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessageServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MessageService");
    }
  }

  private static final class MessageServiceFileDescriptorSupplier
      extends MessageServiceBaseDescriptorSupplier {
    MessageServiceFileDescriptorSupplier() {}
  }

  private static final class MessageServiceMethodDescriptorSupplier
      extends MessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessageServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MessageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessageServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .addMethod(getConnectToNotificationsMethod())
              .addMethod(getConnectToMessageThreadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
