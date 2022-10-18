package com.KGAFT.messenger.FrontEnd.Activities.FileViewActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.KGAFT.messenger.R;

import java.util.ArrayList;

public class ViewFilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);
        ArrayList<Long> filesIds = new ArrayList<>();
        for (String files : getIntent().getStringExtra("files").split(";")) {
            filesIds.add(Long.parseLong(files));
        }
        ViewFilesController.setFilesIds(filesIds);
        ViewFilesController.prepareFiles(fragment -> {
            runOnUiThread(()->{
                try{
                    getSupportFragmentManager().beginTransaction().add(R.id.filesContainer, fragment).commitNow();
                }catch (Exception e){

                }

            });
        });
    }

}