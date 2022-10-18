package com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.CallsFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.KGAFT.messenger.R;

public class CallPreviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.userIcon).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.userName).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.userLogin).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.callDate).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_faster));
        view.findViewById(R.id.callLayoutRoot).setOnClickListener(event->{
            view.findViewById(R.id.callLayoutRoot).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
        });
    }
}