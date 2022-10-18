package com.kgaft.michatmessengerserver.Service.GRPc;

import com.KGAFT.michatmessengerserverproto.MessageServiceOuterClass;
import com.google.protobuf.ByteString;
import com.kgaft.michatmessengerserver.Database.Entity.Chat;
import com.kgaft.michatmessengerserver.Database.Entity.FileEncrypted;
import com.kgaft.michatmessengerserver.Database.Entity.Message;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.Repository.ChatRepository;
import com.kgaft.michatmessengerserver.Database.Repository.FileEncryptedRepository;
import com.kgaft.michatmessengerserver.Database.Repository.MessageRepository;
import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope(scopeName = "singleton")
public class MessageService extends com.KGAFT.michatmessengerserverproto.MessageServiceGrpc.MessageServiceImplBase {
    @Autowired
    private FileEncryptedRepository fileEncryptedRepository;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void sendMessage(MessageServiceOuterClass.MessageRequest request, StreamObserver<MessageServiceOuterClass.MessageAcceptedResponse> responseObserver) {
        User user = authorizationService.getUserByToken(request.getToken());
        if (user != null) {
            sendMessage(user, request.getMessageText().toByteArray(), request.getFilesList(), request.getChatTarget());
            MessageServiceOuterClass.MessageAcceptedResponse response = MessageServiceOuterClass.MessageAcceptedResponse.newBuilder().setAccepted(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void connectToNotifications(MessageServiceOuterClass.authorizationNotificationRequest request, StreamObserver<MessageServiceOuterClass.MessageResponse> responseObserver) {
        if (authorizationService.authorizeToken(request.getToken())) {
            new Thread(() -> {
                User user = authorizationService.getUserByToken(request.getToken());
                    chatRepository.findChatsByChatUsersContaining(user).forEach(chat -> {
                        long freezed = System.currentTimeMillis();
                        if (messageRepository.existsByDateBetweenAndTarget(request.getLastCheckTime(), freezed, chat)) {
                            messageRepository.findMessagesByDateBetweenAndTargetOrderByDateAsc(request.getLastCheckTime(), freezed, chat).forEach(message -> {
                                responseObserver.onNext(messageToReturn(message));
                            });
                        }
                    });
                    responseObserver.onCompleted();

            }).start();
        }
    }

    @Override
    public void connectToMessageThread(MessageServiceOuterClass.authorizationChatRequest request, StreamObserver<MessageServiceOuterClass.MessageResponse> responseObserver) {

        if (authorizationService.authorizeToken(request.getToken())) {
            new Thread(() -> {
                AtomicLong lastCheckTime = new AtomicLong(request.getLastCheckTime());
                Chat chat = chatRepository.findById(request.getChatId()).get();
                long freezed = System.currentTimeMillis();
                if (lastCheckTime.get() != 0) {
                    messageRepository.findMessagesByDateBetweenAndTargetOrderByDateAsc(lastCheckTime.get(), freezed, chat).forEach(message -> {
                        responseObserver.onNext(messageToReturn(message));
                    });
                    lastCheckTime.set(freezed);
                } else {
                    messageRepository.findMessagesByTargetOrderByDateAsc(chatRepository.findById(request.getChatId()).get()).forEach(message -> {
                        responseObserver.onNext(messageToReturn(message));
                    });
                    lastCheckTime.set(freezed);
                }
                responseObserver.onCompleted();
            }).start();
        }
    }

    private MessageServiceOuterClass.MessageResponse messageToReturn(Message message) {
        MessageServiceOuterClass.MessageResponse.Builder builder = MessageServiceOuterClass.MessageResponse.newBuilder()
                .setMessageText(ByteString.copyFrom(message.getMessageText())).setMessageId(message.getMessageId());
        HashSet<Long> filesIds = new HashSet<>();
        message.getAttachedFiles().forEach(file->{
            filesIds.add(file.getFileId());
        });
        builder.addAllAttachedFiles(filesIds);
        builder.setDate(message.getDate());
        builder.setSender(fromUser(message.getSender()));
        builder.setTarget(fromChat(message.getTarget()));
        return builder.build();
    }

    private MessageServiceOuterClass.Chat fromChat(Chat chat) {
        MessageServiceOuterClass.Chat.Builder builder = MessageServiceOuterClass.Chat.newBuilder().setChatId(chat.getChatId())
                .setCreationDate(chat.getCreationDate());

        return builder.build();
    }

    private MessageServiceOuterClass.User fromUser(User user) {
        return MessageServiceOuterClass.User.newBuilder().setUserId(user.getId())
                .setLogin(user.getLogin()).setUserIconId(user.getUserIconId()).setName(user.getName())
                .setLastActive(user.getLastActive()).setNumber("")
                .build();
    }

    private void sendMessage(User sender, byte[] messageText, List<Long> filesIds, long chatId) {
        Message message = new Message();
        message.setMessageText(messageText);
        Chat target = chatRepository.findById(chatId).get();
        message.setTarget(target);
        List<FileEncrypted> fileEncrypteds = new ArrayList<>();
        filesIds.forEach(fileId -> {
            fileEncrypteds.add(fileEncryptedRepository.findById(fileId).get());
        });
        message.setAttachedFiles(fileEncrypteds);
        message.setSender(sender);
        message.setDate(System.currentTimeMillis());
        message = messageRepository.save(message);
        target.setLastMessage(message);
        chatRepository.save(target);
    }

    public List<Message> getMessagesBetweenDates(long start, long end, long chatId) {
        Chat chat = chatRepository.findById(chatId).get();
        return messageRepository.findMessagesByDateBetweenAndTargetOrderByDateAsc(start, end, chat);
    }
}
