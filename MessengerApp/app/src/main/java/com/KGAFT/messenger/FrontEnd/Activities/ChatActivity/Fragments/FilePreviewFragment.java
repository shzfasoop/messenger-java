package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.ChatController;
import com.KGAFT.messenger.R;


public class FilePreviewFragment extends Fragment {

    private FileEncrypted fileEncrypted;
    private Bitmap imagePreview;

    public FilePreviewFragment(FileEncrypted fileEncrypted) {
        this.fileEncrypted = fileEncrypted;
    }

    public FilePreviewFragment(FileEncrypted fileEncrypted, Bitmap imagePreview) {
        this.fileEncrypted = fileEncrypted;
        this.imagePreview = imagePreview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(imagePreview!=null){
            ((ImageView)view.findViewById(R.id.filePreview)).setImageBitmap(imagePreview);
        }
        ((TextView)view.findViewById(R.id.fileName)).setText(fileEncrypted.getFileName());
        view.findViewById(R.id.detachFileIcon).setOnClickListener(event->{
            ChatController.detachFile(fileEncrypted);
            getParentFragmentManager().beginTransaction().remove(this).commitNow();
        });
        view.findViewById(R.id.filePreviewRoot).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
    }
}