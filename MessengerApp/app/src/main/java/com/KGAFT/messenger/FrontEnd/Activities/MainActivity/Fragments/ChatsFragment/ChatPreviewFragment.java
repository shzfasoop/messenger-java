package com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.ChatsFragment;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.ChatActivity;
import com.KGAFT.messenger.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatPreviewFragment extends Fragment {

    private String chatName;
    private String lastMessageText;
    private Bitmap chatIcon;
    private long chatId;
    private long lastMessageDate;
    public ChatPreviewFragment(String chatName,  long chatId) {
        this.chatName = chatName;
        this.chatId = chatId;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPreviews(view);
        startAnimations(view);

    }
    private void initPreviews(View view){
        if(chatIcon!=null){
            ((ImageView)view.findViewById(R.id.chatIcon)).setImageBitmap(chatIcon);
        }

        ((TextView)view.findViewById(R.id.chatName)).setText(chatName);
        ((TextView)view.findViewById(R.id.lastMessageText)).setText(lastMessageText);
        if(lastMessageDate!=0){
            ((TextView)view.findViewById(R.id.lastMessageDate)).setText(convertDateToText());
        }

        view.findViewById(R.id.chatLayoutRoot).setOnClickListener(event->{
            view.findViewById(R.id.chatLayoutRoot).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("chatId", chatId);
            startActivity(intent);
        });
    }
    private void startAnimations(View view){
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.chatIcon).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.chatName).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));

        view.findViewById(R.id.lastMessageText).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public void setLastMessageText(String lastMessageText) {
        this.lastMessageText = lastMessageText;
    }

    public long getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(long lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public Bitmap getChatIcon() {
        return chatIcon;
    }

    public void setChatIcon(Bitmap chatIcon) {
        this.chatIcon = chatIcon;
    }

    public long getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
    private String convertDateToText(){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        return translateDateToRus(formater.format(new Date(lastMessageDate)));
    }
    private String translateDateToRus(String dateTextContent){
        String[] dateArgs = dateTextContent.split("-");
        String newDate = dateArgs[0];
        switch (Integer.parseInt(dateArgs[1])){
            case 1:
                newDate+=" Января";
                break;
            case 2:
                newDate+=" Февраля";
                break;
            case 3:
                newDate+=" Марта";
                break;
            case 4:
                newDate+=" Апреля";
                break;
            case 5:
                newDate+=" Мая";
                break;
            case 6:
                newDate+=" Июня";
                break;
            case 7:
                newDate+=" Июля";
                break;
            case 8:
                newDate+=" Августа";
                break;
            case 9:
                newDate+=" Сентября";
                break;
            case 10:
                newDate+=" Октября";
                break;
            case 11:
                newDate+=" Ноября";
                break;
            case 12:
                newDate+=" Декабря";
                break;
        }
        newDate+=" "+dateArgs[2];
        dateTextContent = newDate;
        return dateTextContent;
    }
}