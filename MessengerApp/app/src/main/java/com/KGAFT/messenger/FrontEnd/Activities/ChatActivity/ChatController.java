package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.KGAFT.messenger.BackEnd.Entities.Chat;
import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.BackEnd.Network.EncryptionKeyService;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.BackEnd.Network.MessageService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class ChatController {
    private static Chat chat;
    private static List<FileEncrypted> attachedFiles = new ArrayList<>();
    private static Context context;
    private static byte[] encryptionKey;
    private static String messageText;
    public static Chat getChat() {
        return chat;
    }
    public static FileEncrypted uploadFile(File file){
        try {
            return FileService.uploadEncryptedFile(FileService
                    .saveTempEncryptedFile(context.getCacheDir().getAbsolutePath(), file, encryptionKey), chat.getChatId(), file.getName());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean sendMessage(){
        Message message = new Message();
        message.setTarget(chat);
        message.setAttachedFileEncrypted(attachedFiles);
        message.setDate(System.currentTimeMillis());
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"));
            message.setMessageTextContent(cipher.doFinal(messageText.getBytes(StandardCharsets.UTF_8)));
            MessageService.sendMessage(message);
            messageText = null;
            attachedFiles.clear();
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void attachFile(FileEncrypted fileEncrypted){
        attachedFiles.add(fileEncrypted);
    }
    public static void detachFile(FileEncrypted fileEncrypted){
        attachedFiles.remove(fileEncrypted);
    }

    public static void init(){
        encryptionKey = EncryptionKeyService.getEncryptionKeyForChat(chat.getChatId()).getDecryptedKey();
    }

    public static void clear(){
        attachedFiles = new ArrayList<>();
        chat = null;
        encryptionKey = null;
        context = null;
    }
    public static void setChat(Chat chat) {
        ChatController.chat = chat;
    }


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ChatController.context = context;
    }

    public static String getMessageText() {
        return messageText;
    }

    public static void setMessageText(String messageText) {
        ChatController.messageText = messageText;
    }
}
