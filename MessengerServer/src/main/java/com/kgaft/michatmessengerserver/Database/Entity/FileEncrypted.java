package com.kgaft.michatmessengerserver.Database.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Files")
public class FileEncrypted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileId;
    private long fileContentId;
    private String fileName;

    private long uploadedDate;
    @ManyToOne
    private User owner;
    private long chatChatId;



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

    public long getChatChatId() {
        return chatChatId;
    }

    public void setChatChatId(long chatChatId) {
        this.chatChatId = chatChatId;
    }
}
