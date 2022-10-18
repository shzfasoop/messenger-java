package com.KGAFT.CallClient;


import com.KGAFT.michatmessengerserverproto.CallServiceGrpc;
import com.KGAFT.michatmessengerserverproto.CallServiceOuterClass;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {


        ManagedChannel receiveChannel = ManagedChannelBuilder.forTarget("localhost:9393").usePlaintext().build();

        CallServiceGrpc.CallServiceBlockingStub receiveStub = CallServiceGrpc.newBlockingStub(receiveChannel);
        CallServiceOuterClass.CallData.Builder callData = CallServiceOuterClass.CallData.newBuilder()
                .setRoomId(1)
                .setPacketId(1)
                .setUserId(1);
        AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("E:/Музыка/Lynyrd Skynyrd - Sweet Home Alabama.wav")));
        new Thread(() -> {
            while (true) {
                try {
                CallServiceOuterClass.ReceiveData data = CallServiceOuterClass.ReceiveData.newBuilder().
                        setRoomId(1)
                        .setUserId(1)
                        .build();
                Iterator<CallServiceOuterClass.CallDataResponse> responses = receiveStub.receiveCallData(data);
                Clip clip = AudioSystem.getClip();
                byte[] buffer = new byte[4*1024];
                ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
                AudioInputStream aus = new AudioInputStream(bis, ais.getFormat(), ais.getFrameLength());
                clip.open();
                clip.start();
                    while (responses.hasNext()) {
                        CallServiceOuterClass.CallDataResponse response = responses.next();
                        bis.read(response.getData().toByteArray());

                    }
                } catch (Exception e) {

                }

            }

        }).start();

        new Thread(() -> {
            ManagedChannel upChannel = ManagedChannelBuilder.forTarget("localhost:9393").usePlaintext().build();
            CallServiceGrpc.CallServiceBlockingStub upStub = CallServiceGrpc.newBlockingStub(upChannel);
            byte[] buffer = new byte[4*1024];
            while (true) {
                try {
                    int read = ais.read(buffer, 0, buffer.length);
                    callData.setCallData(ByteString.copyFrom(buffer, 0, read));
                    upStub.sendCallData(callData.build());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
