package com.KGAFT.messenger.BackEnd.Entities;


import androidx.annotation.Nullable;

public class FileEncrypted {
    private long fileId;
    private long fileContentId;
    private String fileName;
    private long uploadedDate;
    private User owner;
    private long chatChatId;

    public FileEncrypted(long fileId, long fileContentId, String fileName, long uploadedDate, User owner, long chatId) {
        this.fileId = fileId;
        this.fileContentId = fileContentId;
        this.fileName = fileName;
        this.uploadedDate = uploadedDate;
        this.owner = owner;
        this.chatChatId = chatId;
    }

    public FileEncrypted() {
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getFileContentId() {
        return fileContentId;
    }

    public void setFileContentId(long fileContentId) {
        this.fileContentId = fileContentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(long uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getChatId() {
        return chatChatId;
    }

    public void setChatId(long chatId) {
        this.chatChatId = chatId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((FileEncrypted)obj).getFileId()==this.fileId;
    }
}
