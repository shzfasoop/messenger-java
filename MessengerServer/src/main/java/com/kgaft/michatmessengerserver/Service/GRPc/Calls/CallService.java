package com.kgaft.michatmessengerserver.Service.GRPc.Calls;


import com.KGAFT.michatmessengerserverproto.CallServiceGrpc;
import com.KGAFT.michatmessengerserverproto.CallServiceOuterClass;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Service.GRPc.Calls.DAO.Rooms;
import com.kgaft.michatmessengerserver.Service.GRPc.Calls.Entity.Room;
import com.kgaft.michatmessengerserver.Service.UserInfoService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class CallService extends CallServiceGrpc.CallServiceImplBase {
    @Autowired
    private Rooms rooms;
    @Autowired
    private UserInfoService infoService;
    @PostConstruct
    private void roomGarbageCollectorThread(){
        new Thread(()->{
            try{
                while(true){
                    rooms.getRooms().forEach(room -> {
                        if(System.currentTimeMillis()-room.getLastPacketDate()>=1000*60*2){
                            rooms.getRooms().remove(room);
                        }
                    });
                }
            }catch (Exception e){

            }
        }).start();
    }

    @Override
    public void sendCallData(CallServiceOuterClass.CallData request, StreamObserver<CallServiceOuterClass.nullResponse> responseObserver) {
        Room room = rooms.findRoomById(request.getRoomId());
        if (room != null) {
            for (User allowedUser : room.getAllowedUsers()) {
                if (allowedUser.getId() == request.getUserId()) {
                    CallServiceOuterClass.CallData callData = room.getAudioLastData().get(allowedUser);
                    if (callData != null) {
                        Random random = new Random();
                        while (request.getPacketId() == callData.getPacketId()) {
                            request = request.toBuilder().setPacketId(random.nextLong()).build();
                        }
                    }
                    try {
                        room.getAudioLastData().remove(allowedUser);
                    } catch (Exception e) {

                    }
                    room.getAudioLastData().put(allowedUser, request);
                    room.setLastPacketDate(System.currentTimeMillis());
                    responseObserver.onNext(CallServiceOuterClass.nullResponse.newBuilder().build());
                    responseObserver.onCompleted();
                    break;
                }
            }

        }

    }

    @Override
    public void receiveCallData(CallServiceOuterClass.ReceiveData request, StreamObserver<CallServiceOuterClass.CallDataResponse> responseObserver) {
        Room room = rooms.findRoomById(request.getRoomId());
        if (room != null) {
            try {
                room.getAudioLastData().forEach((user, callData) -> {
                    if (user.getId() != request.getUserId()) {
                        CallServiceOuterClass.CallDataResponse response = CallServiceOuterClass.CallDataResponse.newBuilder().setData(callData.getCallData())
                                .setPacketId(callData.getPacketId()).setUserId(callData.getUserId()).build();
                        responseObserver.onNext(response);

                    }
                });
            } catch (Exception e) {

            }

            responseObserver.onCompleted();
        }
    }

    @Override
    public void sendVideoData(CallServiceOuterClass.VideoCallData request, StreamObserver<CallServiceOuterClass.nullResponse> responseObserver) {
        Room room = rooms.findRoomById(request.getRoomId());
        if (room != null) {
            for (User allowedUser : room.getAllowedUsers()) {
                if (allowedUser.getId() == request.getUserId()) {
                    CallServiceOuterClass.VideoCallData callData = room.getVideoLastData().get(allowedUser);
                    if (callData != null) {
                        Random random = new Random();
                        while (request.getPacketId() == callData.getPacketId()) {
                            request = request.toBuilder().setPacketId(random.nextLong()).build();
                        }
                    }
                    try {
                        room.getVideoLastData().remove(allowedUser);
                    } catch (Exception e) {

                    }
                    room.getVideoLastData().put(allowedUser, request);
                    responseObserver.onNext(CallServiceOuterClass.nullResponse.newBuilder().build());
                    responseObserver.onCompleted();
                    break;
                }
            }

        }
    }

    @Override
    public void receiveVideoCallData(CallServiceOuterClass.ReceiveData request, StreamObserver<CallServiceOuterClass.VideoCallDataResponse> responseObserver) {
        Room room = rooms.findRoomById(request.getRoomId());
        if (room != null) {
            try {
                room.getVideoLastData().forEach((user, callData) -> {
                    if (user.getId() != request.getUserId()) {
                        CallServiceOuterClass.VideoCallDataResponse response = CallServiceOuterClass.VideoCallDataResponse.newBuilder()
                                .setData(callData
                                        .getVideoCallData())
                                .setPacketId(callData.getPacketId()).setUserId(callData.getUserId()).build();
                        responseObserver.onNext(response);
                    }
                });
            } catch (Exception e) {

            }
            responseObserver.onCompleted();
        }
    }

    @Override
    public void checkNewRooms(CallServiceOuterClass.RoomsRequest request, StreamObserver<CallServiceOuterClass.RoomsResponse> responseObserver) {
        User user = infoService.findUserById(request.getUserId());
        if(user!=null){
            rooms.getRooms().forEach(room -> {
                if(room.getAllowedUsers().contains(user) && room.getCreationDate()>=request.getCheckTime()){
                    CallServiceOuterClass.RoomsResponse.Builder response = CallServiceOuterClass.RoomsResponse.newBuilder().setRoomId(room.getRoomId());
                    room.getAllowedUsers().forEach(roomUser->{
                        if(roomUser.equals(user)){
                            response.setRoomName(roomUser.getName());
                            response.setRoomIconId(roomUser.getUserIconId());
                        }
                    });
                    responseObserver.onNext(response.build());
                }
            });
        }
        responseObserver.onCompleted();
    }

    @Override
    public void checkRoomExist(CallServiceOuterClass.RoomStatusRequest request, StreamObserver<CallServiceOuterClass.roomStatusResponse> responseObserver) {
        Room room = new Room();
        room.setRoomId(request.getRoomId());
        CallServiceOuterClass.roomStatusResponse response = CallServiceOuterClass.roomStatusResponse.newBuilder()
                .setExist(rooms.exist(room)).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
