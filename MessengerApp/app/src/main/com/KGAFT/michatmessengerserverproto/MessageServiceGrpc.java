package com.KGAFT.messenger;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.0)",
    comments = "Source: MessageService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MessageServiceGrpc {

  private MessageServiceGrpc() {}

  public static final String SERVICE_NAME = "com.KGAFT.messenger.MessageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest,
      com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest.class,
      responseType = com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest,
      com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse> getSendMessageMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest, com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse> getSendMessageMethod;
    if ((getSendMessageMethod = MessageServiceGrpc.getSendMessageMethod) == null) {
      synchronized (MessageServiceGrpc.class) {
        if ((getSendMessageMethod = MessageServiceGrpc.getSendMessageMethod) == null) {
          MessageServiceGrpc.getSendMessageMethod = getSendMessageMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest, com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest,
      com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse> getConnectToNotificationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "connectToNotifications",
      requestType = com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest.class,
      responseType = com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest,
      com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse> getConnectToNotificationsMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest, com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse> getConnectToNotificationsMethod;
    if ((getConnectToNotificationsMethod = MessageServiceGrpc.getConnectToNotificationsMethod) == null) {
      synchronized (MessageServiceGrpc.class) {
        if ((getConnectToNotificationsMethod = MessageServiceGrpc.getConnectToNotificationsMethod) == null) {
          MessageServiceGrpc.getConnectToNotificationsMethod = getConnectToNotificationsMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest, com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "connectToNotifications"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getConnectToNotificationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessageServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessageServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessageServiceStub>() {
        @java.lang.Override
        public MessageServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessageServiceStub(channel, callOptions);
        }
      };
    return MessageServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessageServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessageServiceBlockingStub>() {
        @java.lang.Override
        public MessageServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessageServiceBlockingStub(channel, callOptions);
        }
      };
    return MessageServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessageServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessageServiceFutureStub>() {
        @java.lang.Override
        public MessageServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessageServiceFutureStub(channel, callOptions);
        }
      };
    return MessageServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MessageServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    public void connectToNotifications(com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConnectToNotificationsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest,
                com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getConnectToNotificationsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest,
                com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse>(
                  this, METHODID_CONNECT_TO_NOTIFICATIONS)))
          .build();
    }
  }

  /**
   */
  public static final class MessageServiceStub extends io.grpc.stub.AbstractAsyncStub<MessageServiceStub> {
    private MessageServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessageServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void connectToNotifications(com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConnectToNotificationsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MessageServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MessageServiceBlockingStub> {
    private MessageServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse sendMessage(com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse connectToNotifications(com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConnectToNotificationsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessageServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MessageServiceFutureStub> {
    private MessageServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse> sendMessage(
        com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse> connectToNotifications(
        com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConnectToNotificationsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;
  private static final int METHODID_CONNECT_TO_NOTIFICATIONS = 1;

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

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageAcceptedResponse>) responseObserver);
          break;
        case METHODID_CONNECT_TO_NOTIFICATIONS:
          serviceImpl.connectToNotifications((com.KGAFT.messenger.Generated.MessageServiceOuterClass.authorizationRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.messenger.Generated.MessageServiceOuterClass.MessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
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
              .addMethod(getSendMessageMethod())
              .addMethod(getConnectToNotificationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
