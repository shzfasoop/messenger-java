package com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.KGAFT.messenger.R;


public class HelloFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translate_from_bottom_to_start_pos));
        view.findViewById(R.id.helloHint).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha));
        view.findViewById(R.id.continueButton).setOnClickListener(event->{
            view.findViewById(R.id.continueButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
            getParentFragmentManager().beginTransaction().add(R.id.fragmentContainer, new LoginFragment()).commitNow();
            //getParentFragmentManager().beginTransaction().remove(this).commitNow();
        });
    }
}