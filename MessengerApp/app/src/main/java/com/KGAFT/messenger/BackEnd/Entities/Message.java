package com.KGAFT.messenger.BackEnd.Entities;

import android.util.Base64;

import androidx.annotation.Nullable;

import java.util.List;

public class Message {
    private long messageId;
    private User sender;
    private Chat target;
    private long date;
    private String messageText;
    private byte[] messageTextContent;
    private List<Long> attachedFilesIds;
    private List<FileEncrypted> attachedFileEncrypted;

    public Message(long messageId, User sender, Chat target, byte[] messageTextContent, long date, List<FileEncrypted> attachedFileEncrypted) {
        this.messageId = messageId;
        this.sender = sender;
        this.target = target;
        this.date = date;
        this.messageTextContent = messageTextContent;
        this.attachedFileEncrypted = attachedFileEncrypted;
    }

    public Message() {
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Chat getTarget() {
        return target;
    }

    public void setTarget(Chat target) {
        this.target = target;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public byte[] getMessageTextContent() {
        return messageTextContent;
    }

    public void setMessageTextContent(byte[] messageTextContent) {
        this.messageTextContent = messageTextContent;
    }

    public List<FileEncrypted> getAttachedFileEncrypted() {
        return attachedFileEncrypted;
    }
    public FileEncrypted getFileByIndex(int index){
        return this.attachedFileEncrypted.get(index);
    }

    public void setAttachedFileEncrypted(List<FileEncrypted> attachedFileEncrypted) {
        this.attachedFileEncrypted = attachedFileEncrypted;
    }

    public String getMessageText() {
        return messageText;
    }
    public void init(){
        this.messageTextContent = Base64.decode(messageText, Base64.DEFAULT);
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
        this.messageTextContent = Base64.decode(messageText, Base64.DEFAULT);
    }

    public List<Long> getAttachedFilesIds() {
        return attachedFilesIds;
    }

    public void setAttachedFilesIds(List<Long> attachedFilesIds) {
        this.attachedFilesIds = attachedFilesIds;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Message)obj).getMessageId()==this.messageId;
    }
}
