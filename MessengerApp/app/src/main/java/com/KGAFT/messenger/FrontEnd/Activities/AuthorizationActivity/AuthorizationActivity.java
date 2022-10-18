package com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.KGAFT.messenger.BackEnd.DataBase.DataBase;
import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.User;
import com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.Fragments.HelloFragment;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.MainActivity;
import com.KGAFT.messenger.R;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBase.registerTableForInitializing(AppSettings.class);
        DataBase.registerTableForInitializing(User.class);
        requestPermissions();
        try {
            DataBase.initialize(this);
            if(Repository.findByColumnValue(AppSettings.class, null, null, null).size()==0){
                AppSettings appSettings = new AppSettings(0, "http://192.168.1.68:8080");
                appSettings.setServerRpcConnectionAddress("192.168.1.68:8383");
                appSettings.setCallRpcConnection("192.168.1.68:9393");
                Repository.saveEntity(appSettings);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        try {
            if(((AppSettings)Repository.findByColumnValue(AppSettings.class, null,
                    null, null).get(0)).getAppToken().length()>0){
                startActivity(new Intent(this, MainActivity.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_authorization);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new HelloFragment()).commitNow();
    }
    private void requestPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 100);

        }
    }
}