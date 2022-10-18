package com.KGAFT.messenger.FrontEnd.Activities.FileViewActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.BackEnd.IOUtil;
import com.KGAFT.messenger.BackEnd.Network.EncryptionKeyService;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class VideoViewFragment extends Fragment {
    private File file;
    private VideoView videoView;
    private FileEncrypted fileEncrypted;

    public VideoViewFragment(FileEncrypted fileEncrypted) {
        this.fileEncrypted = fileEncrypted;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoView = view.findViewById(R.id.videoPreview);

        videoView.pause();

        videoView.setOnLongClickListener(event -> {
            new Thread(() -> {
                File target = new File(Environment.DIRECTORY_DOWNLOADS + "/" + fileEncrypted.getFileName());
                try {
                    FileOutputStream fos = new FileOutputStream(target);
                    IOUtil.writeInputStreamToOutputStream(new FileInputStream(file), fos);
                    fos.flush();
                    fos.close();
                    Toast toast = Toast.makeText(getContext(), "Файл загружен в: " + target.getAbsolutePath(), Toast.LENGTH_SHORT);
                    getActivity().runOnUiThread(() -> {
                        toast.show();
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            return true;
        });
        initDownloadButton(view);

    }

    private void initDownloadButton(View view){
        view.findViewById(R.id.downloadButton).setOnClickListener(event -> {
            view.findViewById(R.id.downloadButton).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.downloadButton).setActivated(false);
            new Thread(() -> {
                try {
                    file = FileService.downloadEncryptedFile(fileEncrypted.getFileId(), getContext().getCacheDir().getAbsolutePath(), fileEncrypted.getFileName()
                    , EncryptionKeyService.getEncryptionKeyForChat(fileEncrypted.getChatId()).getDecryptedKey());
                    getActivity().runOnUiThread(() -> {

                        videoView.setVideoPath(file.getAbsolutePath());
                        MediaController mediaController = new MediaController(getContext());
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setOnErrorListener(myVideoViewErrorListener);

                    });

                } catch (IOException e) {
                    getActivity().runOnUiThread(() -> {
                        view.findViewById(R.id.downloadButton).setVisibility(View.VISIBLE);
                        view.findViewById(R.id.downloadButton).setActivated(true);
                    });

                }
            }).start();
        });
    }
    MediaPlayer.OnErrorListener myVideoViewErrorListener
            = (arg0, arg1, arg2) -> {
                Toast.makeText(getContext(),
                        "Error!!!",
                        Toast.LENGTH_LONG).show();
                return true;
            };
}