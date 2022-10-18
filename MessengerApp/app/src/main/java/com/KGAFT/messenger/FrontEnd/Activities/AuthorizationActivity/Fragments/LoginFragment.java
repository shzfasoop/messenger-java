package com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.Fragments;

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
import com.KGAFT.messenger.R;


public class LoginFragment extends Fragment {
    private EditText loginInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_enter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginInput = view.findViewById(R.id.loginInput);
        playStartAnimations(view);
        view.findViewById(R.id.continueButton).setOnClickListener(event -> {
            if (loginInput.getText().length() > 0) {
                AuthorizationController.clearData();
                AuthorizationController.setLogin(loginInput.getText().toString());
                new Thread(() -> {
                    PasswordFragment passwordFragment;
                    passwordFragment = new PasswordFragment(AuthorizationController.checkUserLogin() ? PasswordFragment.LOGIN_MODE : PasswordFragment.REGISTER_MODE);
                    getActivity().runOnUiThread(() -> {
                        view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
                        getParentFragmentManager().beginTransaction().add(R.id.fragmentContainer, passwordFragment).commitNow();
                    });
                }).start();
            }


        });
    }

    private void playStartAnimations(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translate_from_right_to_start_pos));
        view.findViewById(R.id.pageTitle).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.loginHint).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.loginInputFrame).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
    }
}