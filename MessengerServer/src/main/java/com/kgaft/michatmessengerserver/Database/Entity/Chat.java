package com.kgaft.michatmessengerserver.Database.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chatId;
    @ManyToMany
    private List<User> chatUsers;
    private long creationDate;
    @ManyToOne
    private Message lastMessage;
    public Chat(long chatId, String chatName, File chatIcon, List<User> chatUsers, long creationDate) {
        this.chatId = chatId;
        this.chatUsers = chatUsers;
        this.creationDate = creationDate;
    }

    public Chat() {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }


    public List<User> getChatUsers() {
        return chatUsers;
    }

    public void setChatUsers(List<User> chatUsers) {
        this.chatUsers = chatUsers;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }


    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
