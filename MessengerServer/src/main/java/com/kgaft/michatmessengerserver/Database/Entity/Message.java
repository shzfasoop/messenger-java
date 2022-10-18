package com.kgaft.michatmessengerserver.Database.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;
    @ManyToOne
    private User sender;
    @ManyToOne(fetch = FetchType.EAGER)
    private Chat target;
    private long date;
    private byte[] messageText;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<FileEncrypted> attachedFileEncrypted;

    public Message(long messageId, User sender, Chat target, byte[] messageText, long date,  List<FileEncrypted> attachedFileEncrypted) {
        this.messageId = messageId;
        this.sender = sender;
        this.target = target;
        this.date = date;
        this.messageText = messageText;
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

    public byte[] getMessageText() {
        return messageText;
    }

    public void setMessageText(byte[] messageText) {
        this.messageText = messageText;
    }

    public List<FileEncrypted> getAttachedFiles() {
        return attachedFileEncrypted;
    }

    public void setAttachedFiles(List<FileEncrypted> attachedFileEncrypteds) {
        this.attachedFileEncrypted = attachedFileEncrypteds;
    }
}
