package com.kgaft.michatmessengerserver.Service;

import com.kgaft.michatmessengerserver.Database.Entity.Chat;
import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.EncryptionKey;
import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.UserEncryptionKeysPinCode;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.Repository.ChatRepository;
import com.kgaft.michatmessengerserver.Database.Repository.EncryptionKeyRepository;
import com.kgaft.michatmessengerserver.Database.Repository.UsersEncryptionKeysPinCodeRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptionKeysService {
    @Autowired
    private UsersEncryptionKeysPinCodeRepository encryptionPinCodeRepository;
    @Autowired
    private EncryptionKeyRepository encryptionKeys;
    @Autowired
    private ChatRepository chatRepository;
    public void createPinCode(String encryptionKeyPinCode, User user){
        byte[] encryptionKeysPinCode = Base64.decodeBase64(encryptionKeyPinCode);
        encryptionPinCodeRepository.deleteByUser(user);
        UserEncryptionKeysPinCode pinCode = new UserEncryptionKeysPinCode();
        pinCode.setPinCode(encryptionKeysPinCode);
        pinCode.setUser(user);
        encryptionPinCodeRepository.save(pinCode);
    }
    public EncryptionKey getEncryptionKeyForChat(long chatId, User user){
        Chat chat = chatRepository.findById(chatId).get();
        if(chat.getChatId()==chatId && chat.getChatUsers().contains(user)){
            return encryptionKeys.findEncryptionKeysByChatId(chatId);
        }
        return null;
    }
    public EncryptionKey encryptEncryptionKey(EncryptionKey encryptionKey, UserEncryptionKeysPinCode pinCode) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(pinCode.getPinCode(), "AES"));
            byte[] encryptionKeyContent = encryptionKey.getEncryptionKey();
            encryptionKeyContent = cipher.doFinal(encryptionKeyContent, 0, encryptionKeyContent.length);
            encryptionKey.setEncryptionKey(encryptionKeyContent);
            return encryptionKey;
        } catch (Exception e) {

        }
        return null;
    }
    public UserEncryptionKeysPinCode getEncryptionKeyPinCode(User user){
        return encryptionPinCodeRepository.findUserEncryptionKeysPinCodeByUser(user).get(0);
    }
    public void createEncryptionKeyForChat(long chatId){
        try {
            EncryptionKey encryptionKey = new EncryptionKey();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            encryptionKey.setEncryptionKey(keyGenerator.generateKey().getEncoded());
            encryptionKey.setChatId(chatId);
            encryptionKeys.save(encryptionKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
