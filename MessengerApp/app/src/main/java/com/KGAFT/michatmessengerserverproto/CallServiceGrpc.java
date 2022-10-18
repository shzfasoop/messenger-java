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
    comments = "Source: CallService.proto")
public final class CallServiceGrpc {

  private CallServiceGrpc() {}

  public static final String SERVICE_NAME = "com.KGAFT.michatmessengerserverproto.CallService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> getSendCallDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendCallData",
      requestType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData.class,
      responseType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> getSendCallDataMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> getSendCallDataMethod;
    if ((getSendCallDataMethod = CallServiceGrpc.getSendCallDataMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getSendCallDataMethod = CallServiceGrpc.getSendCallDataMethod) == null) {
          CallServiceGrpc.getSendCallDataMethod = getSendCallDataMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendCallData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("sendCallData"))
              .build();
        }
      }
    }
    return getSendCallDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse> getReceiveCallDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveCallData",
      requestType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData.class,
      responseType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse> getReceiveCallDataMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse> getReceiveCallDataMethod;
    if ((getReceiveCallDataMethod = CallServiceGrpc.getReceiveCallDataMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getReceiveCallDataMethod = CallServiceGrpc.getReceiveCallDataMethod) == null) {
          CallServiceGrpc.getReceiveCallDataMethod = getReceiveCallDataMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveCallData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("receiveCallData"))
              .build();
        }
      }
    }
    return getReceiveCallDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> getSendVideoDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendVideoData",
      requestType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData.class,
      responseType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> getSendVideoDataMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> getSendVideoDataMethod;
    if ((getSendVideoDataMethod = CallServiceGrpc.getSendVideoDataMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getSendVideoDataMethod = CallServiceGrpc.getSendVideoDataMethod) == null) {
          CallServiceGrpc.getSendVideoDataMethod = getSendVideoDataMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendVideoData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("sendVideoData"))
              .build();
        }
      }
    }
    return getSendVideoDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse> getReceiveVideoCallDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveVideoCallData",
      requestType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData.class,
      responseType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse> getReceiveVideoCallDataMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse> getReceiveVideoCallDataMethod;
    if ((getReceiveVideoCallDataMethod = CallServiceGrpc.getReceiveVideoCallDataMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getReceiveVideoCallDataMethod = CallServiceGrpc.getReceiveVideoCallDataMethod) == null) {
          CallServiceGrpc.getReceiveVideoCallDataMethod = getReceiveVideoCallDataMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveVideoCallData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("receiveVideoCallData"))
              .build();
        }
      }
    }
    return getReceiveVideoCallDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse> getCheckNewRoomsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkNewRooms",
      requestType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest.class,
      responseType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse> getCheckNewRoomsMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse> getCheckNewRoomsMethod;
    if ((getCheckNewRoomsMethod = CallServiceGrpc.getCheckNewRoomsMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getCheckNewRoomsMethod = CallServiceGrpc.getCheckNewRoomsMethod) == null) {
          CallServiceGrpc.getCheckNewRoomsMethod = getCheckNewRoomsMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkNewRooms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("checkNewRooms"))
              .build();
        }
      }
    }
    return getCheckNewRoomsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse> getCheckRoomExistMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkRoomExist",
      requestType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest.class,
      responseType = com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest,
      com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse> getCheckRoomExistMethod() {
    io.grpc.MethodDescriptor<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse> getCheckRoomExistMethod;
    if ((getCheckRoomExistMethod = CallServiceGrpc.getCheckRoomExistMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getCheckRoomExistMethod = CallServiceGrpc.getCheckRoomExistMethod) == null) {
          CallServiceGrpc.getCheckRoomExistMethod = getCheckRoomExistMethod =
              io.grpc.MethodDescriptor.<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest, com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkRoomExist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("checkRoomExist"))
              .build();
        }
      }
    }
    return getCheckRoomExistMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CallServiceStub newStub(io.grpc.Channel channel) {
    return new CallServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CallServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CallServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CallServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CallServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CallServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendCallDataMethod(), responseObserver);
    }

    /**
     */
    public void receiveCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveCallDataMethod(), responseObserver);
    }

    /**
     */
    public void sendVideoData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendVideoDataMethod(), responseObserver);
    }

    /**
     */
    public void receiveVideoCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveVideoCallDataMethod(), responseObserver);
    }

    /**
     */
    public void checkNewRooms(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckNewRoomsMethod(), responseObserver);
    }

    /**
     */
    public void checkRoomExist(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckRoomExistMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendCallDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData,
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse>(
                  this, METHODID_SEND_CALL_DATA)))
          .addMethod(
            getReceiveCallDataMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData,
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse>(
                  this, METHODID_RECEIVE_CALL_DATA)))
          .addMethod(
            getSendVideoDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData,
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse>(
                  this, METHODID_SEND_VIDEO_DATA)))
          .addMethod(
            getReceiveVideoCallDataMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData,
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse>(
                  this, METHODID_RECEIVE_VIDEO_CALL_DATA)))
          .addMethod(
            getCheckNewRoomsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest,
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse>(
                  this, METHODID_CHECK_NEW_ROOMS)))
          .addMethod(
            getCheckRoomExistMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest,
                com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse>(
                  this, METHODID_CHECK_ROOM_EXIST)))
          .build();
    }
  }

  /**
   */
  public static final class CallServiceStub extends io.grpc.stub.AbstractStub<CallServiceStub> {
    private CallServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CallServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendCallDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveCallDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendVideoData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendVideoDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveVideoCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveVideoCallDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkNewRooms(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getCheckNewRoomsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkRoomExist(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest request,
        io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckRoomExistMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CallServiceBlockingStub extends io.grpc.stub.AbstractStub<CallServiceBlockingStub> {
    private CallServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CallServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse sendCallData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData request) {
      return blockingUnaryCall(
          getChannel(), getSendCallDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse> receiveCallData(
        com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveCallDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse sendVideoData(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData request) {
      return blockingUnaryCall(
          getChannel(), getSendVideoDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse> receiveVideoCallData(
        com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveVideoCallDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse> checkNewRooms(
        com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getCheckNewRoomsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse checkRoomExist(com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckRoomExistMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CallServiceFutureStub extends io.grpc.stub.AbstractStub<CallServiceFutureStub> {
    private CallServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CallServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> sendCallData(
        com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData request) {
      return futureUnaryCall(
          getChannel().newCall(getSendCallDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse> sendVideoData(
        com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData request) {
      return futureUnaryCall(
          getChannel().newCall(getSendVideoDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse> checkRoomExist(
        com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckRoomExistMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_CALL_DATA = 0;
  private static final int METHODID_RECEIVE_CALL_DATA = 1;
  private static final int METHODID_SEND_VIDEO_DATA = 2;
  private static final int METHODID_RECEIVE_VIDEO_CALL_DATA = 3;
  private static final int METHODID_CHECK_NEW_ROOMS = 4;
  private static final int METHODID_CHECK_ROOM_EXIST = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CallServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CallServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_CALL_DATA:
          serviceImpl.sendCallData((com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallData) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_CALL_DATA:
          serviceImpl.receiveCallData((com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.CallDataResponse>) responseObserver);
          break;
        case METHODID_SEND_VIDEO_DATA:
          serviceImpl.sendVideoData((com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallData) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.nullResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_VIDEO_CALL_DATA:
          serviceImpl.receiveVideoCallData((com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.ReceiveData) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.VideoCallDataResponse>) responseObserver);
          break;
        case METHODID_CHECK_NEW_ROOMS:
          serviceImpl.checkNewRooms((com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomsResponse>) responseObserver);
          break;
        case METHODID_CHECK_ROOM_EXIST:
          serviceImpl.checkRoomExist((com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.RoomStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.roomStatusResponse>) responseObserver);
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

  private static abstract class CallServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CallServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.KGAFT.michatmessengerserverproto.CallServiceOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CallService");
    }
  }

  private static final class CallServiceFileDescriptorSupplier
      extends CallServiceBaseDescriptorSupplier {
    CallServiceFileDescriptorSupplier() {}
  }

  private static final class CallServiceMethodDescriptorSupplier
      extends CallServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CallServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CallServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CallServiceFileDescriptorSupplier())
              .addMethod(getSendCallDataMethod())
              .addMethod(getReceiveCallDataMethod())
              .addMethod(getSendVideoDataMethod())
              .addMethod(getReceiveVideoCallDataMethod())
              .addMethod(getCheckNewRoomsMethod())
              .addMethod(getCheckRoomExistMethod())
              .build();
        }
      }
    }
    return result;
  }
}
