package com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.ChatsFragment;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.Chat;
import com.KGAFT.messenger.BackEnd.Network.ChatService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatListController {

    private static Context context;

    public static List<ChatPreviewFragment> prepareFragments(){
        List<ChatPreviewFragment> chatPreviewFragments = new ArrayList<>();
        sortChatByActivityTime(prepareChats()).forEach(chat->{

            ChatPreviewFragment chatPreviewFragment = new ChatPreviewFragment(chat.getChatName(), chat.getChatId());
            if(chat.getDownloadedIcon()!=null){
                chatPreviewFragment.setChatIcon(BitmapFactory.decodeFile(chat.getDownloadedIcon().getAbsolutePath()));
            }
            if(chat.getLastMessage()!=null){
                chatPreviewFragment.setLastMessageText(new String(chat.getLastMessage().getMessageTextContent()));
                chatPreviewFragment.setLastMessageDate(chat.getLastMessage().getDate());
            }
            chatPreviewFragments.add(chatPreviewFragment);
        });
        return chatPreviewFragments;
    }


    private static List<Chat> sortChatByActivityTime(List<Chat> chats){
        int changes = 1;
        while (changes>0){
            changes = 0;
            for(int counter = 0; counter<chats.size()-1; counter++){
                if(chats.get(counter).getLastMessage()!=null && chats.get(counter+1).getLastMessage()!=null){
                    if((chats.get(counter)).getLastMessage().getDate()<(chats.get(counter+1)).getLastMessage().getDate()){
                        Chat temp = chats.get(counter);
                        chats.set(counter, chats.get(counter+1));
                        chats.set(counter+1, temp);
                        changes++;
                    }
                }

            }
        }
        return chats;
    }
    private static List<Chat> prepareChats(){
        return ChatService.getAllChatsFromServer(context);
    }
    private static String getAppToken(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getAppToken();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setContext(Context context) {
        ChatListController.context = context;
    }
}
