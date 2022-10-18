package com.KGAFT.messenger.BackEnd.Entities;

import java.io.File;
import java.util.List;

public class Chat {
    private long chatId;
    private String chatName;
    private long creationDate;
    private List<User> chatUsers;
    private Message lastMessage;
    private File downloadedIcon;
    public Chat(long chatId, String chatName, FileNotEncrypted chatIcon, long creationDate, List<User> chatUsers) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.creationDate = creationDate;
        this.chatUsers = chatUsers;
    }

    public Chat() {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }


    public List<User> getChatUsers() {
        return chatUsers;
    }

    public void setChatUsers(List<User> chatUsers) {
        this.chatUsers = chatUsers;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }


    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public File getDownloadedIcon() {
        return downloadedIcon;
    }

    public void setDownloadedIcon(File downloadedIcon) {
        this.downloadedIcon = downloadedIcon;
    }
}
