package com.kgaft.michatmessengerserver.Database.Entity;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileContent {
    private long fileId;
    private InputStream content;

    public FileContent(long fileId, FileInputStream content) {
        this.fileId = fileId;
        this.content = content;
    }

    public FileContent() {
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }
}
