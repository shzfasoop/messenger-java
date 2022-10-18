package com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.ChatsFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.KGAFT.messenger.FrontEnd.Activities.CreateChatActivity.CreateChatActivity;
import com.KGAFT.messenger.R;

import java.util.ArrayList;
import java.util.List;


public class ChatListFragment extends Fragment {
    private List<ChatPreviewFragment> chatFragments = new ArrayList<>();
    private Activity context;

    public ChatListFragment(Activity context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ChatListController.setContext(getContext());
        view.findViewById(R.id.createChatButton).setOnClickListener(event->{
            startActivity(new Intent(getContext(), CreateChatActivity.class));
        });
        new Thread(()->{
            ChatListController.prepareFragments().forEach(chatPreviewFragment -> {
                context.runOnUiThread(()->{
                    chatFragments.add(chatPreviewFragment);
                    getChildFragmentManager().beginTransaction().add(R.id.chatsPreviewContainer, chatPreviewFragment).commitNow();
                });
            });
        }).start();
    }
    public void refreshSearch(String text){
        new Thread(()->{
            chatFragments.forEach(fragment->{
                context.runOnUiThread(()-> {
                    getChildFragmentManager().beginTransaction().remove(fragment).commitNow();
                });
            });
            chatFragments = ChatListController.prepareFragments();
            chatFragments.forEach(fragment->{
                if(fragment.getChatName().contains(text)){
                    context.runOnUiThread(()->{
                        getChildFragmentManager().beginTransaction()
                                .add(R.id.chatsPreviewContainer, fragment).commitNow();
                    });

                }
            });
        }).start();


    }
    public void closeSearch(){
        new Thread(()->{
            chatFragments.forEach(fragment->{
                context.runOnUiThread(()-> {
                    try{
                        getChildFragmentManager().beginTransaction().remove(fragment).commitNow();
                    }catch (Exception e){

                    }
                });
            });
            chatFragments = ChatListController.prepareFragments();
            chatFragments.forEach(chatPreviewFragment -> {
                try{
                    context.runOnUiThread(()->{
                        getChildFragmentManager().beginTransaction().add(R.id.chatsPreviewContainer, chatPreviewFragment).commitNow();
                    });
                }catch (Exception e){

                }

            });

        }).start();
    }
}