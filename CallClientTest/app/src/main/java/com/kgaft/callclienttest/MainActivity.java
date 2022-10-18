package com.kgaft.callclienttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;

import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import KGAFT.michatmessengerserverproto.CallServiceGrpc;
import KGAFT.michatmessengerserverproto.CallServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MainActivity extends AppCompatActivity {
    private int port = 50005;

    private AudioRecord recorder;
    private AudioTrack audioTrack;
    private int sampleRate = 16000; // 44100 for music
    private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
    private SecretKey secretKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!checkPermissions()) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA
            }, 1);
        }
        findViewById(R.id.startButton).setOnClickListener(event -> {
            receiveThread.start();
            streamThread.start();
        });

    }

    long lastSend = 0;
    ManagedChannel channel = ManagedChannelBuilder.forTarget("0.tcp.eu.ngrok.io:19106").usePlaintext().build();
    Thread receiveThread = new Thread(() -> {
        audioTrack = new AudioTrack(AudioManager.STREAM_VOICE_CALL, sampleRate, channelConfig, audioFormat, 8196, AudioTrack.MODE_STREAM);

        CallServiceGrpc.CallServiceBlockingStub receiveStub = CallServiceGrpc.newBlockingStub(channel);
        CallServiceOuterClass.ReceiveData data = CallServiceOuterClass.ReceiveData.newBuilder().
                setRoomId(1)
                .setUserId(1)
                .build();
        audioTrack.play();


            while (true) {
                try {
                    Iterator<CallServiceOuterClass.CallDataResponse> responses = receiveStub.receiveCallData(data);
                    long lastPacketId = 0;
                    while (responses.hasNext()) {
                        CallServiceOuterClass.CallDataResponse response = responses.next();
                        if (response.getPacketId() != lastPacketId) {
                            byte[] audio = response.getData().toByteArray();
                            audioTrack.write(audio, 0, audio.length);
                            lastPacketId = response.getPacketId();
                        }

                    }
                } catch (Exception e) {

                }

            }


    });
    Thread streamThread = new Thread(() -> {
        CallServiceOuterClass.CallData.Builder callData = CallServiceOuterClass.CallData.newBuilder()
                .setRoomId(1)
                .setPacketId(1)
                .setUserId(1);

        CallServiceGrpc.CallServiceBlockingStub upStub = CallServiceGrpc.newBlockingStub(channel);
        byte[] buffer = new byte[8196];


            recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig, audioFormat, 8196*10);
            recorder.startRecording();
            while (true) {
                int read = recorder.read(buffer, 0, buffer.length);

                callData.setCallData(ByteString.copyFrom(buffer, 0, read));
                upStub.sendCallData(callData.build());
                lastSend = System.currentTimeMillis();
            }


    });

    private void prepareCameras(){
         CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {

            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean checkPermissions() {
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED;
    }



}