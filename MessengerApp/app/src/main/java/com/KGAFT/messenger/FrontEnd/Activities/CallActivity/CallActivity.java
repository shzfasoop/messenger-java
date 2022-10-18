package com.KGAFT.messenger.FrontEnd.Activities.CallActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.telephony.mbms.FileInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.KGAFT.messenger.BackEnd.Entities.FileNotEncrypted;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.BackEnd.Services.CallService;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.MainActivity;
import com.KGAFT.messenger.R;

import java.io.File;
import java.io.IOException;

public class CallActivity extends AppCompatActivity {
    public static final int ACCEPT_CALL_MODE = 1;
    public static final int CALL_TO_USER_MODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        switch (getIntent().getIntExtra("mode", 0)){
            case ACCEPT_CALL_MODE:
                CallController.setRoomId(getIntent().getLongExtra("roomId", -1));
                findViewById(R.id.acceptCallButton).setOnClickListener(event->{
                    findViewById(R.id.acceptCallButton).setVisibility(View.INVISIBLE);
                    findViewById(R.id.declineCallButton).setVisibility(View.INVISIBLE);
                    initTabBar();
                    CallController.startMicrophone();
                    CallController.start();
                });
                findViewById(R.id.declineCallButton).setOnClickListener(event->{
                    startActivity(new Intent(this, MainActivity.class));
                });
                break;
            case CALL_TO_USER_MODE:
                findViewById(R.id.acceptCallButton).setVisibility(View.INVISIBLE);
                findViewById(R.id.declineCallButton).setVisibility(View.INVISIBLE);
                initTabBar();
                CallController.start();

                CallController.setOnCallAccepted(CallController::startMicrophone);
                break;
        }
        ((TextView)findViewById(R.id.callName)).setText(getIntent().getStringExtra("roomName"));
        new Thread(()->{
            long roomIconId = getIntent().getLongExtra("roomIconId", -1);
            FileNotEncrypted fileInfo = FileService.getFileInfo(roomIconId);
            try {
                File icon = FileService.downloadFile(fileInfo, getCacheDir().getAbsolutePath(), fileInfo.getFileName());
                Bitmap callIcon = BitmapFactory.decodeFile(icon.getAbsolutePath());
                runOnUiThread(()->{
                    ((ImageView)findViewById(R.id.contactIcon)).setImageBitmap(callIcon);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private  void initTabBar(){
        findViewById(R.id.callTabBarBackground).setVisibility(View.VISIBLE);
        findViewById(R.id.endCall).setOnClickListener(event->{
            CallController.stop();
            startActivity(new Intent(this, MainActivity.class));
        });
        boolean muted = false;
        findViewById(R.id.turnOffMicrophone).setOnClickListener(event->{
            if(!muted){
                CallController.pauseMicrophone();
            }
            else{
                CallController.startMicrophone();
            }
        });
    }

}