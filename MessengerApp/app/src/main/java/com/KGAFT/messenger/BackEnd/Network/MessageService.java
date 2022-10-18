package com.KGAFT.messenger.BackEnd.Network;

import android.util.Log;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.BackEnd.Entities.User;

import com.KGAFT.michatmessengerserverproto.MessageServiceGrpc;
import com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass;
import com.google.protobuf.ByteString;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MessageService {

    public static Thread startMessageReceiverThread(OnMessageReceived onMessageReceivedAction, AtomicBoolean running, long chatId){
        Thread thread = new Thread(()->{
            ManagedChannel managedChannel = ManagedChannelBuilder.forTarget(getRpcAddress()).usePlaintext().build();
            MessageServiceGrpc.MessageServiceBlockingStub messageServiceBlockingStub = MessageServiceGrpc.newBlockingStub(managedChannel);
            AtomicLong lastCheckTime = new AtomicLong(0);
            while (running.get()){
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Iterator<MessageServiceOuterClass.MessageResponse> messages =  messageServiceBlockingStub.connectToMessageThread(
                                MessageServiceOuterClass.authorizationChatRequest
                                        .newBuilder().setToken(getAppToken()).setLastCheckTime(lastCheckTime.get())
                                        .setChatId(chatId).build());
                if(messages.hasNext()){
                    while (messages.hasNext()){
                        onMessageReceivedAction
                                .messageReceived(
                                        decryptMessage(
                                                convertResponseToMessage(
                                                 messages.next()),
                                                EncryptionKeyService.getEncryptionKeyForChat(chatId).getDecryptedKey())
                                );
                    }
                }
                lastCheckTime.set(System.currentTimeMillis());

            }
        });
        return thread;
    }
    public static Thread prepareNotificationsThread(OnMessageReceived action, AtomicBoolean running){
        Thread thread = new Thread(()->{
            ManagedChannel managedChannel = ManagedChannelBuilder.forTarget(getRpcAddress()).usePlaintext().build();
            MessageServiceGrpc.MessageServiceBlockingStub messageServiceBlockingStub = MessageServiceGrpc.newBlockingStub(managedChannel);
            AtomicLong lastCheckTime = new AtomicLong(0);
            while(running.get()){
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try{
                    MessageServiceOuterClass.authorizationNotificationRequest request = MessageServiceOuterClass
                            .authorizationNotificationRequest.newBuilder().setToken(getAppToken())
                            .setLastCheckTime(lastCheckTime.get()).build();
                    Iterator<MessageServiceOuterClass.MessageResponse> messages = messageServiceBlockingStub.connectToNotifications(request);
                    while(messages.hasNext()){
                        MessageServiceOuterClass.MessageResponse message = messages.next();
                        if(!message.getSender().getLogin().equals(getAppLogin())){
                            action.messageReceived(decryptMessage(convertResponseToMessage(message), EncryptionKeyService.getEncryptionKeyForChat(message.getTarget().getChatId()).getDecryptedKey()));
                        }

                    }
                    lastCheckTime.set(System.currentTimeMillis());
                }catch (Exception e){

                }

            }
        });
        return thread;
    }

    public static boolean sendMessage(Message message){
        ManagedChannel managedChannel = ManagedChannelBuilder.forTarget(getRpcAddress()).usePlaintext().build();
        MessageServiceGrpc.MessageServiceBlockingStub messageServiceBlockingStub = MessageServiceGrpc.newBlockingStub(managedChannel);
        MessageServiceOuterClass.MessageAcceptedResponse messageAcceptedResponse = messageServiceBlockingStub.sendMessage(messageToRequest(message));
        managedChannel.shutdownNow();
        return messageAcceptedResponse.getAccepted();
    }

    public static Message encryptMessage(Message message, byte[] encryptionKey){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"));
            message.setMessageTextContent(cipher.doFinal(message.getMessageTextContent(), 0, message.getMessageTextContent().length));
            return message;
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static MessageServiceOuterClass.MessageRequest messageToRequest(Message message){
        MessageServiceOuterClass.MessageRequest.Builder messageRequest = MessageServiceOuterClass.MessageRequest.newBuilder()
                .setMessageText(ByteString.copyFrom(message.getMessageTextContent())).setChatTarget(message.getTarget().getChatId())
                .setToken(getAppToken());
        try{
            HashSet<Long> filesIds = new HashSet<>();
            message.getAttachedFileEncrypted().forEach(file->{
                filesIds.add(file.getFileId());
            });
            messageRequest.addAllFiles(filesIds);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return messageRequest.build();
    }
    public static Message decryptMessage(Message message, byte[] encryptionKey){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"));
            message.setMessageTextContent(cipher.doFinal(message.getMessageTextContent(), 0, message.getMessageTextContent().length));
            return message;
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getRpcAddress(){
        try {
            return ((AppSettings)Repository.findByColumnValue(AppSettings.class, null, null, null).get(0)).getServerRpcConnectionAddress();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getAppLogin(){
        try {
            String login = ((AppSettings)Repository.findByColumnValue(AppSettings.class, null, null, null).get(0)).getLogin();
            return login;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Message convertResponseToMessage(MessageServiceOuterClass.MessageResponse messageResponse){
        Message message = new Message();
        message.setMessageTextContent(messageResponse.getMessageText().toByteArray());
        message.setSender(fromUser(messageResponse.getSender()));
        message.setDate(messageResponse.getDate());
        message.setAttachedFilesIds(messageResponse.getAttachedFilesList());
        message.setMessageId(messageResponse.getMessageId());
        return message;
    }
    private static User fromUser(MessageServiceOuterClass.User userResp){
        User user = new User();
        user.setId(userResp.getUserId());
        user.setUserIconId(userResp.getUserIconId());
        user.setLastActive(userResp.getLastActive());
        user.setLogin(userResp.getLogin());
        user.setName(user.getName());
        user.setNumber(user.getNumber());
        return user;
    }
    private static String getAppToken(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getAppToken();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
