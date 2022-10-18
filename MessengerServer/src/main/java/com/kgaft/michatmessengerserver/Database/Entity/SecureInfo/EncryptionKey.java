package com.kgaft.michatmessengerserver.Database.Entity.SecureInfo;

import javax.persistence.*;

@Entity
@Table(name = "EncryptionKeys")
public class EncryptionKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long keyId;
    private byte[] encryptionKey;
    private long chatId;

    public EncryptionKey(long keyId, byte[] encryptionKey, long chatId) {
        this.keyId = keyId;
        this.encryptionKey = encryptionKey;
        this.chatId = chatId;
    }

    public EncryptionKey() {
    }

    public long getKeyId() {
        return keyId;
    }

    public void setKeyId(long keyId) {
        this.keyId = keyId;
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
