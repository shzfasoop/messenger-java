package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.R;

import java.util.ArrayList;
import java.util.List;


public class MessageTopic extends Fragment {
    public static final int OUR_TOPIC = 0;
    public static final int OTHER_TOPIC = 1;
    private int messagesAmount = 0;
    public int currentMode;
    public String userLogin;
    public boolean drown;
    private View view;

    private List<Fragment> fragmentsToDraw = new ArrayList<>();
    public MessageTopic(int currentMode, String userLogin) {
        this.currentMode = currentMode;
        this.userLogin = userLogin;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(currentMode==OTHER_TOPIC?R.layout.fragment_messages_other_topic:currentMode==OUR_TOPIC?R.layout.fragment_messages_our_topic:-1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
    }

    public int getCurrentMode() {
        return currentMode;
    }

    public String getUserLogin() {
        return userLogin;
    }
    public void addMessageToDraw(Message message){
        switch (currentMode){
            case OUR_TOPIC:
                fragmentsToDraw.add(new MessageOurFragment(message));
                break;
            case OTHER_TOPIC:
                fragmentsToDraw.add(new MessageOtherFragment(message, messagesAmount==0));
                break;

        }
    }

    public boolean isDrown() {
        return drown;
    }
    public void drawElements(){

        fragmentsToDraw.forEach(fragment -> {
            getChildFragmentManager().beginTransaction().add(R.id.messagesSubContainer, fragment).commitNow();
        });

        fragmentsToDraw.clear();
    }

    public void setDrown(boolean drown) {
        this.drown = drown;
    }
}