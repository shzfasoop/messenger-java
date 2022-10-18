package com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.KGAFT.messenger.BackEnd.Network.AuthorizationService;
import com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.AuthorizationController;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.MainActivity;
import com.KGAFT.messenger.R;


public class PasswordFragment extends Fragment {

    public static final byte REGISTER_MODE = 0;
    public static final byte LOGIN_MODE = 1;

    private byte currentMode;
    private EditText passwordInput;
    private EditText passwordRepeatInput;

    public PasswordFragment(byte currentMode) {
        this.currentMode = currentMode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAnimations(view);
        passwordInput = view.findViewById(R.id.passwordInput);
        passwordRepeatInput = view.findViewById(R.id.passwordRepeat);
        if (currentMode == LOGIN_MODE) {
            view.findViewById(R.id.passwordRepeatFrame).setVisibility(View.INVISIBLE);
        }

        view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.continueButton).setOnClickListener(event -> {
            if (passwordInput.getText().length() > 0) {
                new Thread(() -> {
                    switch (currentMode) {
                        case LOGIN_MODE:
                            AuthorizationController.setPassword(passwordInput.getText().toString());
                            try {
                                if (AuthorizationController.authorizeUser()) {
                                    getActivity().runOnUiThread(() -> {
                                        view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
                                        startActivity(new Intent(getContext(), MainActivity.class));
                                    });
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        case REGISTER_MODE:
                            if(passwordInput.getText().toString().equals(passwordRepeatInput.getText().toString())){
                                AuthorizationController.setPassword(passwordInput.getText().toString());
                                getActivity().runOnUiThread(()->{
                                    view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
                                    getParentFragmentManager().beginTransaction().add(R.id.fragmentContainer, new UserInfoFragment()).commitNow();

                                });

                            }
                    }
                }).start();
            }
        });
    }

    private void startAnimations(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translate_from_right_to_start_pos));
        view.findViewById(R.id.pageTitle).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.passwordHint).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.passwordInputFrame).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));

    }
}