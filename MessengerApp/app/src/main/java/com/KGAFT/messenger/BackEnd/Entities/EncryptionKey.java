package com.KGAFT.messenger.BackEnd.Entities;

public class EncryptionKey {
    private long keyId;
    private String encryptionKey;
    private long chatId;
    private byte[] decryptedKey;

    public EncryptionKey(long keyId, String encryptionKey, long chatId, byte[] decryptedKey) {
        this.keyId = keyId;
        this.encryptionKey = encryptionKey;
        this.chatId = chatId;
        this.decryptedKey = decryptedKey;
    }

    public EncryptionKey() {
    }

    public long getKeyId() {
        return keyId;
    }

    public void setKeyId(long keyId) {
        this.keyId = keyId;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public byte[] getDecryptedKey() {
        return decryptedKey;
    }

    public void setDecryptedKey(byte[] decryptedKey) {
        this.decryptedKey = decryptedKey;
    }
}
