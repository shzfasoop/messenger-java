package com.KGAFT.messenger.BackEnd.Network;

import android.content.Context;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.Chat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    public static List<Chat> getAllChatsFromServer(Context context){
        List<Object> chatsWithEncryptedMessages = ServerHttpConnection.getArrayDataFromServer(Chat.class, "/getAllChats", "token="+getAppToken(), "GET");
        List<Chat> decryptedChats = new ArrayList<>();
        chatsWithEncryptedMessages.forEach(chatDat->{
            Chat chat = (Chat) chatDat;
            if(chat.getLastMessage()!=null){
                chat.getLastMessage().init();
                chat.setLastMessage(MessageService.decryptMessage(chat.getLastMessage(), EncryptionKeyService.getEncryptionKeyForChat(chat.getChatId()).getDecryptedKey())) ;
            }
            StringBuilder chatName = new StringBuilder();
            chat.getChatUsers().forEach(user->{
                if(!user.getLogin().equals(getUserLogin())){
                    chatName.append(user.getName());
                    if(context!=null && user.getUserIconId()!=0){
                        try {
                            chat.setDownloadedIcon(FileService.downloadFile(user.getUserIconId(), context.getCacheDir().getAbsolutePath(), "userIcon"+user.getId()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            chat.setChatName(chatName.toString());
            });

            decryptedChats.add(chat);
        });
        return decryptedChats;
    }
    public static Chat getChatById(long chatId, Context context){
        Chat chat = (Chat) ServerHttpConnection.getDataFromServer(Chat.class, "/getChatById", "token="+getAppToken()+"&chatId="+chatId, "GET");
        StringBuilder chatName = new StringBuilder();
        chat.getChatUsers().forEach(user->{
            if(!user.getLogin().equals(getUserLogin())){
                chatName.append(user.getName());
                if(context!=null && user.getUserIconId()!=0){
                    try {
                        chat.setDownloadedIcon(FileService.downloadFile(user.getUserIconId(), context.getCacheDir().getAbsolutePath(), "userIcon"+user.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            chat.setChatName(chatName.toString());
        });
        return chat;
    }
    public static void createChat(String chatName, String receiverLogin){
        ServerHttpConnection.getRawDataFromServer("/createChat", "token="+getAppToken()+"&receiverLogin="+receiverLogin, "POST");
    }
    private static String getAppToken(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getAppToken();
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getUserLogin(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getLogin();
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
