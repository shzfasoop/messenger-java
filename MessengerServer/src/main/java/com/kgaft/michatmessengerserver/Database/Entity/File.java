package com.kgaft.michatmessengerserver.Database.Entity;

import javax.persistence.*;

@Entity
@Table(name="filesNotEncrypted")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long fileContentId;
    private String fileName;
    private long uploadedDate;
    @ManyToOne
    private User owner;

    public File(long id, long fileContentId, String fileName, long uploadedDate, User owner) {
        this.id = id;
        this.fileContentId = fileContentId;
        this.fileName = fileName;
        this.uploadedDate = uploadedDate;
        this.owner = owner;
    }

    public File() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
