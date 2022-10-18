package com.KGAFT.messenger.FrontEnd.Activities.FileViewActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.BackEnd.IOUtil;
import com.KGAFT.messenger.BackEnd.Network.EncryptionKeyService;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImagePreviewFragment extends Fragment {
    private FileEncrypted fileEncrypted;
    private File file;

    public ImagePreviewFragment(FileEncrypted fileEncrypted) {
        this.fileEncrypted = fileEncrypted;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(()->{
            try {
                file = FileService.downloadEncryptedFile(fileEncrypted.getFileId(), getContext().getCacheDir().getAbsolutePath(), fileEncrypted.getFileName(),
                        EncryptionKeyService.getEncryptionKeyForChat(fileEncrypted.getChatId()).getDecryptedKey());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                getActivity().runOnUiThread(()->{
                    ((ImageView)view.findViewById(R.id.imagePreview)).setImageBitmap(bitmap);
                    view.findViewById(R.id.imagePreview).invalidate();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        view.findViewById(R.id.imagePreview).setOnLongClickListener(event->{
            if(file!=null){
                new Thread(()->{
                    File target = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + fileEncrypted.getFileName());
                    try {
                        FileOutputStream fos = new FileOutputStream(target);
                        IOUtil.writeInputStreamToOutputStream(new FileInputStream(file), fos);
                        fos.flush();
                        fos.close();
                        getActivity().runOnUiThread(() -> {
                            Toast toast = Toast.makeText(getContext(), "Файл загружен в: " + target.getAbsolutePath(), Toast.LENGTH_LONG);
                            toast.show();
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
            return true;
        });
    }
}