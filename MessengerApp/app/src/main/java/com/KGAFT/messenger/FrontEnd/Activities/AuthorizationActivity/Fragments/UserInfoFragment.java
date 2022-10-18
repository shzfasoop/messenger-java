package com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.KGAFT.messenger.BackEnd.Entities.FileNotEncrypted;
import com.KGAFT.messenger.BackEnd.IOUtil;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.AuthorizationController;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.MainActivity;
import com.KGAFT.messenger.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserInfoFragment extends Fragment {
    private EditText nameInput;
    private ImageView userIcon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameInput = view.findViewById(R.id.nameInput);
        startAnimations(view);
        userIcon = view.findViewById(R.id.userIcon);
        userIcon.setOnClickListener(event->{
            getImage.launch("image/*");
        });
        view.findViewById(R.id.completeButton).setOnClickListener(event->{
            if(nameInput.getText().length()>0){
                new Thread(()->{
                    AuthorizationController.setName(nameInput.getText().toString());
                    try {
                        if(AuthorizationController.registerUser()){
                            getActivity().runOnUiThread(()->{
                                view.findViewById(R.id.completeButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
                                startActivity(new Intent(getContext(), MainActivity.class));
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        });
    }

    private ActivityResultLauncher getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), result->{
        new Thread(()->{
            try{
                File icon = IOUtil.transferUriToCacheDir(result, getActivity().getContentResolver(), getActivity().getCacheDir().getAbsolutePath());
                AuthorizationController.setIcon(icon);
                getActivity().runOnUiThread(()->{
                    Bitmap bitmap;
                    userIcon.setImageBitmap(BitmapFactory.decodeFile(icon.getAbsolutePath()));
                });
            }catch (Exception e){

            }

        }).start();
    });

    private void setIconToPreview(File icon){

    }
    private void startAnimations(View view){
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translate_from_right_to_start_pos));
        view.findViewById(R.id.pageTitle).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.userInfoHint).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.userIcon).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.iconHint).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.nameInputFrame).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.completeButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fast));
        view.findViewById(R.id.completeButton).setOnClickListener(event->{
            view.findViewById(R.id.completeButton).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.alpha_fastest));
            startActivity(new Intent(getContext(), MainActivity.class));
        });
    }

}