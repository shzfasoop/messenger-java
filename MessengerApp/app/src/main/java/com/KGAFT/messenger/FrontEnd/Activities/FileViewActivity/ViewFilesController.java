package com.KGAFT.messenger.FrontEnd.Activities.FileViewActivity;

import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.BackEnd.Network.FileService;

import java.util.ArrayList;

public class ViewFilesController {
    private static ArrayList<Long> filesIds = new ArrayList<>();
    public static void prepareFiles(OnFragmentReady onFragmentReady){
        new Thread(()->{
            if(filesIds!=null){
                for (Long filesId : filesIds) {
                    FileEncrypted fileEncrypted = FileService.getFileEncryptedInfo(filesId);
                    if(fileEncrypted!=null){
                        switch (FileService.getFileExtension(fileEncrypted.getFileName()).toLowerCase()){
                            case "jpg":
                            case "png":
                            case "jpeg":
                            case "webp":
                                onFragmentReady.fragmentReady(new ImagePreviewFragment(fileEncrypted));
                                break;
                            case "3gp":
                            case "3gpp":
                            case "mp4":
                            case "mkv":
                            case "webm":
                                onFragmentReady.fragmentReady(new VideoViewFragment(fileEncrypted));
                                break;
                        }
                    }
                }
            }
        }).start();
    }

    public static ArrayList<Long> getFilesIds() {
        return filesIds;
    }

    public static void setFilesIds(ArrayList<Long> filesIds) {
        ViewFilesController.filesIds = filesIds;
    }
}
