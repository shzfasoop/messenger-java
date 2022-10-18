package com.kgaft.michatmessengerserver.Controller;

import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import com.kgaft.michatmessengerserver.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/firstTimeAuthorize")
    public String authorizeByLoginAndPassword(@RequestParam String login, @RequestParam String password){
        return authorizationService.createTokenAndAuthorize(login, password);
    }
    @GetMapping("/isUserExist")
    public boolean isUserExists(@RequestParam String login){
        return userInfoService.isUserExists(login);
    }
    @PostMapping("/register")
    public User registerUser(@RequestParam  String name, @RequestParam String login, @RequestParam String password){
        return userInfoService.createUser(name, login, password);
    }
    @GetMapping("/authorize")
    public User authorize(@RequestParam String token){
        return authorizationService.getUserByToken(token);
    }
    @GetMapping("/checkAuthorization")
    public boolean checkAuthorization(@RequestParam String token){
        return authorizationService.authorizeToken(token);
    }
    @GetMapping("/checkConnection")
    public boolean checkConnection(){
        return true;
    }



}
