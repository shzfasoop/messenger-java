package com.KGAFT.messenger.BackEnd.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.FrontEnd.Activities.CallActivity.CallActivity;
import com.KGAFT.michatmessengerserverproto.CallServiceGrpc;
import com.KGAFT.michatmessengerserverproto.CallServiceOuterClass;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CallService extends Service {
    public static boolean isRunning = false;

    private static ManagedChannel channel;
    private static ArrayList<Long> roomsIgnored = new ArrayList<>();
    private static boolean paused = false;
    public CallService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        channel = ManagedChannelBuilder.forTarget(getCallRpcConnection()).usePlaintext().build();
        isRunning = true;
        new Thread(()->{
            CallServiceGrpc.CallServiceBlockingStub receiveStub = CallServiceGrpc.newBlockingStub(channel);
            long currentUser = getAppUserId();
            long lastCheckTime = System.currentTimeMillis();
            while (true){
                if(!paused){
                    try{
                        CallServiceOuterClass.RoomsRequest roomsRequest = CallServiceOuterClass.RoomsRequest.newBuilder()
                                .setCheckTime(lastCheckTime)
                                .setUserId(currentUser).build();
                        Iterator<CallServiceOuterClass.RoomsResponse> responseIterator = receiveStub.checkNewRooms(roomsRequest);
                        if(responseIterator.hasNext()){
                            CallServiceOuterClass.RoomsResponse response = responseIterator.next();
                            if(!roomsIgnored.contains(response.getRoomId())){
                                Intent activityIntent = new Intent(getApplicationContext(), CallActivity.class);
                                activityIntent.putExtra("roomId", response.getRoomId());
                                activityIntent.putExtra("roomIconId", response.getRoomIconId());
                                activityIntent.putExtra("roomName", response.getRoomName());
                                activityIntent.putExtra("mode", CallActivity.ACCEPT_CALL_MODE);
                                activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(activityIntent);
                                roomsIgnored.add(response.getRoomId());
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                lastCheckTime = System.currentTimeMillis();

            }
        }).start();
        new Thread(()->{
            while (true){
                try{
                    int roomsSize = roomsIgnored.size();
                    if(roomsSize>0){
                        Thread.sleep(30*1000*60);
                        for(int counter = 0; counter<roomsSize; counter++){
                            roomsIgnored.remove(counter);
                        }
                    }
                }catch (Exception e){

                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return  Service.START_STICKY;
    }
    public static void pauseThread(){
        paused = true;
    }
    public static void resume(){
        paused = false;
    }
    private long getAppUserId(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getCurrentUser();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private String getCallRpcConnection(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getCallRpcConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Long> getRoomsIgnored() {
        return roomsIgnored;
    }

    public static ManagedChannel getChannel() {
        return channel;
    }
}