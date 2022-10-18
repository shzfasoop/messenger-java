package com.KGAFT.messenger.FrontEnd.Activities.CreateChatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.KGAFT.messenger.R;

public class CreateChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);
        CreateChatController.setContext(this);
        CreateChatController.setContentResolver(getContentResolver());
        new Thread(()->{
            CreateChatController.preparePreviews().forEach(contactPreviewFragment -> {
                runOnUiThread(()->{
                    getSupportFragmentManager().beginTransaction().add(R.id.contactsContainer, contactPreviewFragment).commitNow();
                });
            });
        }).start();
    }


}