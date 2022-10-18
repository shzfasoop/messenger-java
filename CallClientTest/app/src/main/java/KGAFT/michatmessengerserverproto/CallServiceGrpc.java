package KGAFT.michatmessengerserverproto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.*;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.*;

/**
 */

public final class CallServiceGrpc {

  private CallServiceGrpc() {}

  public static final String SERVICE_NAME = "com.KGAFT.michatmessengerserverproto.CallService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CallServiceOuterClass.CallData,
      CallServiceOuterClass.nullResponse> getSendCallDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendCallData",
      requestType = CallServiceOuterClass.CallData.class,
      responseType = CallServiceOuterClass.nullResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<CallServiceOuterClass.CallData,
      CallServiceOuterClass.nullResponse> getSendCallDataMethod() {
    io.grpc.MethodDescriptor<CallServiceOuterClass.CallData, CallServiceOuterClass.nullResponse> getSendCallDataMethod;
    if ((getSendCallDataMethod = CallServiceGrpc.getSendCallDataMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getSendCallDataMethod = CallServiceGrpc.getSendCallDataMethod) == null) {
          CallServiceGrpc.getSendCallDataMethod = getSendCallDataMethod =
              io.grpc.MethodDescriptor.<CallServiceOuterClass.CallData, CallServiceOuterClass.nullResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendCallData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CallServiceOuterClass.CallData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CallServiceOuterClass.nullResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("sendCallData"))
              .build();
        }
      }
    }
    return getSendCallDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<CallServiceOuterClass.ReceiveData,
      CallServiceOuterClass.CallDataResponse> getReceiveCallDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveCallData",
      requestType = CallServiceOuterClass.ReceiveData.class,
      responseType = CallServiceOuterClass.CallDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<CallServiceOuterClass.ReceiveData,
      CallServiceOuterClass.CallDataResponse> getReceiveCallDataMethod() {
    io.grpc.MethodDescriptor<CallServiceOuterClass.ReceiveData, CallServiceOuterClass.CallDataResponse> getReceiveCallDataMethod;
    if ((getReceiveCallDataMethod = CallServiceGrpc.getReceiveCallDataMethod) == null) {
      synchronized (CallServiceGrpc.class) {
        if ((getReceiveCallDataMethod = CallServiceGrpc.getReceiveCallDataMethod) == null) {
          CallServiceGrpc.getReceiveCallDataMethod = getReceiveCallDataMethod =
              io.grpc.MethodDescriptor.<CallServiceOuterClass.ReceiveData, CallServiceOuterClass.CallDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "receiveCallData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CallServiceOuterClass.ReceiveData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CallServiceOuterClass.CallDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallServiceMethodDescriptorSupplier("receiveCallData"))
              .build();
        }
      }
    }
    return getReceiveCallDataMethod;
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
    public void sendCallData(CallServiceOuterClass.CallData request,
                             io.grpc.stub.StreamObserver<CallServiceOuterClass.nullResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendCallDataMethod(), responseObserver);
    }

    /**
     */
    public void receiveCallData(CallServiceOuterClass.ReceiveData request,
                                io.grpc.stub.StreamObserver<CallServiceOuterClass.CallDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveCallDataMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendCallDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                CallServiceOuterClass.CallData,
                CallServiceOuterClass.nullResponse>(
                  this, METHODID_SEND_CALL_DATA)))
          .addMethod(
            getReceiveCallDataMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                CallServiceOuterClass.ReceiveData,
                CallServiceOuterClass.CallDataResponse>(
                  this, METHODID_RECEIVE_CALL_DATA)))
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
    public void sendCallData(CallServiceOuterClass.CallData request,
                             io.grpc.stub.StreamObserver<CallServiceOuterClass.nullResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendCallDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveCallData(CallServiceOuterClass.ReceiveData request,
                                io.grpc.stub.StreamObserver<CallServiceOuterClass.CallDataResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveCallDataMethod(), getCallOptions()), request, responseObserver);
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
    public CallServiceOuterClass.nullResponse sendCallData(CallServiceOuterClass.CallData request) {
      return blockingUnaryCall(
          getChannel(), getSendCallDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<CallServiceOuterClass.CallDataResponse> receiveCallData(
        CallServiceOuterClass.ReceiveData request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveCallDataMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<CallServiceOuterClass.nullResponse> sendCallData(
        CallServiceOuterClass.CallData request) {
      return futureUnaryCall(
          getChannel().newCall(getSendCallDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_CALL_DATA = 0;
  private static final int METHODID_RECEIVE_CALL_DATA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      UnaryMethod<Req, Resp>,
      ServerStreamingMethod<Req, Resp>,
      ClientStreamingMethod<Req, Resp>,
      BidiStreamingMethod<Req, Resp> {
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
          serviceImpl.sendCallData((CallServiceOuterClass.CallData) request,
              (io.grpc.stub.StreamObserver<CallServiceOuterClass.nullResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_CALL_DATA:
          serviceImpl.receiveCallData((CallServiceOuterClass.ReceiveData) request,
              (io.grpc.stub.StreamObserver<CallServiceOuterClass.CallDataResponse>) responseObserver);
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
      return CallServiceOuterClass.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
