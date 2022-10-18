package com.kgaft.michatmessengerserver.Controller;

import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.EncryptionKey;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import com.kgaft.michatmessengerserver.Service.EncryptionKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionKeysController {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private EncryptionKeysService encryptionKeysService;

    @PostMapping("/registerEncryptionKeyPinCode")
    public void registerEncryptionKeyPinCode(@RequestParam String pinCode, @RequestParam String login, @RequestParam String password){
        User user = authorizationService.authorizeByLoginAndPassword(login, password);
        if(user!=null){
            encryptionKeysService.createPinCode(pinCode, user);
        }
    }
    @GetMapping("/getEncryptionKey")
    public EncryptionKey getEncryptionKey(@RequestParam String token, @RequestParam long chatId){
       User user = authorizationService.getUserByToken(token);
       if(user!=null){
           return encryptionKeysService.encryptEncryptionKey(encryptionKeysService.getEncryptionKeyForChat(chatId, user), encryptionKeysService.getEncryptionKeyPinCode(user));
       }
       return null;
    }
}
