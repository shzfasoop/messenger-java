package com.kgaft.michatmessengerserver.Controller;

import com.kgaft.michatmessengerserver.Database.Entity.Chat;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import com.kgaft.michatmessengerserver.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatsController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private ChatService chatService;
    @PostMapping("/createChat")
    public Chat createChat(@RequestParam String token, @RequestParam String receiverLogin){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            return chatService.createChat(receiverLogin, user);
        }
        return null;
    }


    @GetMapping("/getAllChats")
    public List<Chat> getAllChats(@RequestParam String token){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            return chatService.getUserChats(user);
        }
        return null;
    }
    @MessageMapping("/getChatsByCreationDate")
    @SendTo("/topic/chats")
    public List<Chat> getChatsByCreationDate(String token, long start, long end){
        User user = authorizationService.getUserByToken(token);
        return chatService.getChatsByCreationDate(user, start, end);
    }
    @GetMapping("/getChatById")
    public Chat getChatById(@RequestParam String token, @RequestParam long chatId){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            return chatService.getChatById(chatId, user);
        }
        return null;
    }
    @DeleteMapping("/exitFromChat")
    public void exitFromChat(@RequestParam String token, @RequestParam long chatId){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            chatService.exitFromChat(chatId, user);
        }

    }

}
