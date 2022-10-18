package com.kgaft.callclienttest;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.Surface;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class CameraService {
    private String mCameraID;
    private CameraDevice mCameraDevice = null;
    private CameraCaptureSession mSession;
    private CaptureRequest.Builder mPreviewBuilder;
    private MediaCodec mCodec;
    private CameraManager mCameraManager;
    public CameraService(CameraManager cameraManager, String cameraID) {

        mCameraManager = cameraManager;
        mCameraID = cameraID;

    }

    private CameraDevice.StateCallback mCameraCallback = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            Log.i(LOG_TAG, "Open camera  with id:" + mCameraDevice.getId());

            startCameraPreviewSession();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            mCameraDevice.close();


            mCameraDevice = null;
        }

        @Override
        public void onError(CameraDevice camera, int error) {

        }
    };

    private void startCameraPreviewSession() {
        SurfaceTexture texture = mImageView.getSurfaceTexture();
        texture.setDefaultBufferSize(320, 240);
        surface = new Surface(texture);


        try {

            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mPreviewBuilder.addTarget(surface);
            mPreviewBuilder.addTarget(mEncoderSurface);


            mCameraDevice.createCaptureSession(Arrays.asList(surface, mEncoderSurface),

                    new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(CameraCaptureSession session) {
                            mSession = session;

                            try {
                                mSession.setRepeatingRequest(mPreviewBuilder.build(), null, mBackgroundHandler);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession session) {
                        }
                    }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }


    public boolean isOpen() {
        if (mCameraDevice == null) {
            return false;
        } else {
            return true;
        }
    }


    public void openCamera() {
        try {
            mCameraManager.openCamera(mCameraID, mCameraCallback, mBackgroundHandler);


        } catch (CameraAccessException e) {
            Log.i(LOG_TAG, e.getMessage());

        }
    }

    public void closeCamera() {

        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    public void stopStreamingVideo() {

        if (mCameraDevice != null & mCodec != null) {

            try {
                mSession.stopRepeating();
                mSession.abortCaptures();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            mCodec.stop();
            mCodec.release();
            mEncoderSurface.release();
            closeCamera();
        }
        MediaRecorder recorder;
        recorder.start();

    }


    private void setUpMediaCodec() {


        try {
            mCodec = MediaCodec.createEncoderByType("video/avc"); // H264 кодек

        } catch (Exception e) {

        }

        int width = 320; // ширина видео
        int height = 240; // высота видео
        int colorFormat = MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface; // формат ввода цвета
        int videoBitrate = 500000; // битрейт видео в bps (бит в секунду)
        int videoFramePerSecond = 20; // FPS
        int iframeInterval = 3; // I-Frame интервал в секундах

        MediaFormat format = MediaFormat.createVideoFormat("video/avc", width, height);
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, colorFormat);
        format.setInteger(MediaFormat.KEY_BIT_RATE, videoBitrate);
        format.setInteger(MediaFormat.KEY_FRAME_RATE, videoFramePerSecond);
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframeInterval);


        mCodec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE); // конфигурируем кодек как кодер
        mEncoderSurface = mCodec.createInputSurface(); // получаем Surface кодера

        mCodec.setCallback(new EncoderCallback());
        mCodec.start(); // запускаем кодер


    }
}



