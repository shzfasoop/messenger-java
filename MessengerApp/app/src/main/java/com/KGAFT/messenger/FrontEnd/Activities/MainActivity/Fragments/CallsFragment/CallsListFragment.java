package com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.CallsFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.KGAFT.messenger.R;


public class CallsListFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int counter = 0; counter<10; counter++){
            getChildFragmentManager().beginTransaction().add(R.id.callPreviewContainer, new CallPreviewFragment()).commitNow();
        }
    }
}