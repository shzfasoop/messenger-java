package com.KGAFT.messenger.FrontEnd.Activities.CreateChatActivity;

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

import com.KGAFT.messenger.BackEnd.Network.ChatService;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.MainActivity;
import com.KGAFT.messenger.R;


public class ContactPreviewFragment extends Fragment {
    private String name;
    private String login;
    private Bitmap contactImage;

    public ContactPreviewFragment(String name, String login) {
        this.name = name;
        this.login = login;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(contactImage!=null){
            ((ImageView)view.findViewById(R.id.contactIcon)).setImageBitmap(contactImage);
        }
        ((TextView)view.findViewById(R.id.contactName)).setText(name);
        playAnimations(view);
        view.findViewById(R.id.contactRoot).setOnClickListener(event->{
            view.findViewById(R.id.contactRoot).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
            new Thread(()->{
                ChatService.createChat(name, login);
                getActivity().runOnUiThread(()->{
                    startActivity(new Intent(getContext(), MainActivity.class));
                });
            }).start();
        });
    }
    private void playAnimations(View view){
        view.findViewById(R.id.contactRoot).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.contactIcon).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.contactName).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Bitmap getContactImage() {
        return contactImage;
    }

    public void setContactImage(Bitmap contactImage) {
        this.contactImage = contactImage;
    }
}