package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.Chat;
import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.BackEnd.IOUtil;
import com.KGAFT.messenger.BackEnd.Network.ChatService;
import com.KGAFT.messenger.BackEnd.Network.MessageService;
import com.KGAFT.messenger.BackEnd.Network.UserInfoService;
import com.KGAFT.messenger.BackEnd.Services.CallService;
import com.KGAFT.messenger.FrontEnd.Activities.CallActivity.CallActivity;
import com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments.FilePreviewFragment;
import com.KGAFT.messenger.R;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatActivity extends AppCompatActivity {
    private TextView chatName;
    private long currentUserId;
    private List<FilePreviewFragment> filePreviewFragmentList = new ArrayList<>();
    private Chat chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ChatController.clear();
        ChatController.setContext(this);
        chatName = findViewById(R.id.chatName);
        findViewById(R.id.backButton).setOnClickListener(event->{
            findViewById(R.id.backButton).startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_fastest));
            onBackPressed();
        });
        findViewById(R.id.callButton).setOnClickListener(event->{
            findViewById(R.id.callButton).startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_fastest));
            new Thread(()->{
                CallService.pauseThread();
                long roomId = UserInfoService.createRoomForCall(chat.getChatUsers().get(0).getId()==currentUserId?chat.getChatUsers().get(1).getId():chat.getChatUsers().get(0).getId());
                CallService.getRoomsIgnored().add(roomId);
                Intent intent = new Intent(this, CallActivity.class);
                intent.putExtra("mode", CallActivity.CALL_TO_USER_MODE);
                intent.putExtra("roomId", roomId);
                long roomIconId = chat.getChatUsers().get(0).getId()==currentUserId?chat.getChatUsers().get(1).getUserIconId():chat.getChatUsers().get(0).getUserIconId();
                intent.putExtra("roomIconId", roomIconId);
                intent.putExtra("roomName", chat.getChatUsers().get(0).getId()==currentUserId?chat.getChatUsers().get(1).getName():chat.getChatUsers().get(0).getName());
                startActivity(intent);
            }).start();

        });
        findViewById(R.id.sendButton).setOnClickListener(event->{
            findViewById(R.id.sendButton).startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_fastest));
            new Thread(()->{
                ChatController.setMessageText(((TextView)findViewById(R.id.messageInputText)).getText().toString());
                if(ChatController.sendMessage()){
                    runOnUiThread(()->{
                        ((TextView)findViewById(R.id.messageInputText)).setText("");
                        filePreviewFragmentList.forEach(filePreviewFragment -> {
                            getSupportFragmentManager().beginTransaction().remove(filePreviewFragment).commitNow();
                        });
                        filePreviewFragmentList.clear();
                    });
                }
            }).start();
        });
        findViewById(R.id.attachFileButton).setOnClickListener(event->{
            findViewById(R.id.attachFileButton).startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_fastest));
            getFile.launch("*/*");
        });
        AtomicBoolean running = new AtomicBoolean(true);
        currentUserId = getCurrentUserId();
        MessageController messageController = new MessageController(R.id.messageContainer, this, getSupportFragmentManager());
        MessageService.startMessageReceiverThread(messageController, running, getIntent().getLongExtra("chatId", -1)).start();
        initViews(getIntent().getLongExtra("chatId", -1));

    }

    private void initViews(long chatId){
        new Thread(()->{
            Chat chat = ChatService.getChatById(chatId, this);
            this.chat = chat;
            ChatController.setChat(chat);
            ChatController.init();
            runOnUiThread(()->{
                chatName.setText(chat.getChatName());
            });
        }).start();
    }
    private ActivityResultLauncher getFile = registerForActivityResult(new ActivityResultContracts.GetContent(), result->{
        new Thread(()->{
            try{
                File file = IOUtil.transferUriToCacheDir(result, getContentResolver(), getCacheDir().getAbsolutePath());
                FileEncrypted fileEncrypted = ChatController.uploadFile(file);
                Bitmap filePreview = BitmapFactory.decodeFile(file.getAbsolutePath());
                FilePreviewFragment filePreviewFragment = null;
                if(filePreview!=null){
                    filePreviewFragment = new FilePreviewFragment(fileEncrypted, filePreview);
                }
                else{
                    filePreviewFragment = new FilePreviewFragment(fileEncrypted);
                }
                FilePreviewFragment finalFilePreviewFragment = filePreviewFragment;
                runOnUiThread(()->{
                    getSupportFragmentManager().beginTransaction().add(R.id.filePreviewContainer, finalFilePreviewFragment).commitNow();
                    filePreviewFragmentList.add(finalFilePreviewFragment);
                    ChatController.attachFile(fileEncrypted);
                });
            }catch (Exception e){

            }

        }).start();
    });
    private long getCurrentUserId(){
        try {
            return ((AppSettings)(Repository.findByColumnValue(AppSettings.class, null, null, null).get(0))).getCurrentUser();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return -1;
    }
}