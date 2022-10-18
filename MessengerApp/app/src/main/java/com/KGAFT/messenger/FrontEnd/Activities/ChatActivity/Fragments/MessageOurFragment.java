package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.FrontEnd.Activities.FileViewActivity.ViewFilesActivity;
import com.KGAFT.messenger.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MessageOurFragment extends Fragment {
    private Message message;

    public MessageOurFragment(Message message) {
        this.message = message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_our, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String messageText = new String(message.getMessageTextContent());
        ((TextView)view.findViewById(R.id.messageText)).setText(messageText);
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        ((TextView)view.findViewById(R.id.messageTime)).setText(formater.format(new Date(message.getDate())));
        try{
            if(message.getAttachedFilesIds().size()>0){
                ((ImageView)view.findViewById(R.id.viewAttachmentsButton)).setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_file_icon));
                view.findViewById(R.id.messageBigRoot).setOnClickListener(event->{
                    Intent intent = new Intent(getContext(), ViewFilesActivity.class);
                    String filesIds = "";
                    for (Long attachedFilesId : message.getAttachedFilesIds()) {
                        filesIds+=attachedFilesId+";";
                    }
                    intent.putExtra("files", filesIds);
                    startActivity(intent);
                });
                ((ImageView)view.findViewById(R.id.viewAttachmentsButton)).invalidate();
            }

        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}