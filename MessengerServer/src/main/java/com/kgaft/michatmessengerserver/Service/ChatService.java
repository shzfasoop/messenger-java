package com.kgaft.michatmessengerserver.Service;

import com.kgaft.michatmessengerserver.Database.Entity.Chat;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.Repository.ChatRepository;
import com.kgaft.michatmessengerserver.Database.Repository.FileRepository;
import com.kgaft.michatmessengerserver.Database.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//@TODO ADD_LAST_ACTIVITY_SUUPPORT
@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private EncryptionKeysService encryptionKeysService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FileRepository fileRepository;

    public Chat createChat(String receiverLogin, User chatStartUser){
        Chat chat = new Chat();
        List<User> chatUsers = new ArrayList<>();
        chatUsers.add(chatStartUser);
        chatUsers.add(usersRepository.findUserByLogin(receiverLogin));
        chat.setChatUsers(chatUsers);
        chat.setCreationDate(System.currentTimeMillis());
        chat = chatRepository.save(chat);
        encryptionKeysService.createEncryptionKeyForChat(chat.getChatId());
        return chat;
    }

    public List<Chat> getUserChats(User user){

        return removeTargetFromLastMessage(chatRepository.findChatsByChatUsersContaining(user));
    }
    public List<Chat> getChatsByCreationDate(User user, long start, long end){
        return removeTargetFromLastMessage(chatRepository.findChatsByCreationDateBetweenAndChatUsersContaining(start, end, user));
    }
    public void exitFromChat(long chatId, User user){
        Chat chat = chatRepository.findById(chatId).get();
        chat.getChatUsers().remove(user);
        chatRepository.save(chat);
    }
    public Chat getChatById(long chatId, User user){
        Chat chat = chatRepository.findById(chatId).get();
        if(chat.getChatUsers().contains(user)){
            if(chat.getLastMessage()!=null){
                chat.getLastMessage().setTarget(null);
            }

            return chat;
        }
        return null;
    }
    private List<Chat> removeTargetFromLastMessage(List<Chat> chats){
        try{
            chats.forEach(chat -> {
                chat.getLastMessage().setTarget(null);
            });
        }catch (Exception e){

        }

        return chats;
    }

}
