package com.KGAFT.messenger.FrontEnd.Activities.CallActivity;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Services.CallService;

import com.KGAFT.michatmessengerserverproto.CallServiceGrpc;
import com.KGAFT.michatmessengerserverproto.CallServiceOuterClass;
import com.google.protobuf.ByteString;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CallController {
    private static AudioTrack audioTrack;
    private static long roomId;
    private static boolean running = false;
    private static boolean microRecording = true;
    private static OnCallAccepted onCallAccepted;
    public static void setAudioTrack(AudioTrack audioTrack) {
        CallController.audioTrack = audioTrack;
    }

    public static void setRoomId(long roomId) {
        CallController.roomId = roomId;
    }
    public static void start(){
        running = true;
        audioTrack = prepareTrack();
        byte[] sendBuffer = new byte[8196];
        AtomicReference<ByteString> toPlay = new AtomicReference<>();
        AtomicInteger sendRead = new AtomicInteger();
        new Thread(()->{
            CallServiceGrpc.CallServiceBlockingStub receiveStub = CallServiceGrpc.newBlockingStub(CallService.getChannel());
            CallServiceOuterClass.ReceiveData data = CallServiceOuterClass.ReceiveData.newBuilder().
                    setRoomId(roomId)
                    .setUserId(1)
                    .build();
            boolean actionWorked = false;
            long lastPacketId = 0;

            while (running){
                Iterator<CallServiceOuterClass.CallDataResponse> responses = receiveStub.receiveCallData(data);


                while (responses.hasNext()) {
                    if(!actionWorked){
                        try{
                            onCallAccepted.accepted();
                        }catch (Exception e){

                        }
                        actionWorked = true;
                    }
                    CallServiceOuterClass.CallDataResponse response = responses.next();
                    if (response.getPacketId() != lastPacketId) {
                        toPlay.set(response.getData());
                    }
                }
            }
        }).start();
        new Thread(()->{
            CallServiceOuterClass.CallData.Builder callData = CallServiceOuterClass.CallData.newBuilder()
                    .setRoomId(roomId)
                    .setPacketId(1)
                    .setUserId(getAppUserId());

            CallServiceGrpc.CallServiceBlockingStub upStub = CallServiceGrpc.newBlockingStub(CallService.getChannel());

            while (true){
                if(microRecording){
                    try{
                        callData.setCallData(ByteString.copyFrom(sendBuffer, 0, sendRead.get()));
                        upStub.sendCallData(callData.build());
                    }catch (Exception e){

                    }

                }
            }
        }).start();
        new Thread(()->{
            AudioRecord record = prepareAudioRecord();
            record.startRecording();
            while(running){
                sendRead.set(record.read(sendBuffer, 0, sendBuffer.length));
            }
        }).start();
        new Thread(()->{
            audioTrack.play();
            while (running){
                byte[] toPlayArray = new byte[8196];
                try{
                    toPlayArray = toPlay.get().toByteArray();
                }catch (Exception e){

                }
                audioTrack.write(toPlayArray, 0, toPlayArray.length);
            }
        }).start();
    }
    public static void stop(){
        running = false;
    }

    public static void pauseMicrophone(){
        microRecording = false;
    }
    public static void startMicrophone(){
        microRecording = true;
    }

    public static void setOnCallAccepted(OnCallAccepted onCallAccepted) {
        CallController.onCallAccepted = onCallAccepted;
    }
    private static AudioTrack prepareTrack(){
        int sampleRate = 16000; // 44100 for music
        int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        return new AudioTrack(AudioManager.STREAM_VOICE_CALL, sampleRate, channelConfig, audioFormat, 8196, AudioTrack.MODE_STREAM);
    }
    private static AudioRecord prepareAudioRecord(){
        int sampleRate = 16000; // 44100 for music
        int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        return new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig, audioFormat, 8196*10);
    }
    private static long getAppUserId(){
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
}
