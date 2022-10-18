package com.kgaft.michatmessengerserver.Controller;

import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import com.kgaft.michatmessengerserver.Service.GRPc.Calls.DAO.Rooms;
import com.kgaft.michatmessengerserver.Service.GRPc.Calls.Entity.Room;
import com.kgaft.michatmessengerserver.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserInfoController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private Rooms rooms;
    @PostMapping("/uploadUserIcon")
    public boolean uploadUserIcon(@RequestParam String token, @RequestParam long fileId){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            userInfoService.setUserImage(fileId, user);
            return true;
        }
        return false;
    }
    @GetMapping("/getUserByPhoneNumber")
    public User getUserByPhoneNumber(@RequestParam String token, @RequestParam String phoneNumber){
        if(authorizationService.authorizeToken(token)){
            User user = userInfoService.findUserByNumber(phoneNumber);
            return user;
        }
        return null;
    }
    @PatchMapping("/setUserPhoneNumber")
    public void setUserPhoneNumber(@RequestParam String token, @RequestParam String phoneNumber){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            userInfoService.setUserPhoneNumber(user, phoneNumber);
        }
    }
    @PostMapping("/callToUser")
    public long callToUser(@RequestParam String token, @RequestParam long receiverId){
        User user = authorizationService.getUserByToken(token);
        if(user!=null){
            Room room = new Room();
            User receiver = userInfoService.findUserById(receiverId);
            ArrayList<User> allowedUsers = new ArrayList<>();
            allowedUsers.add(user);
            allowedUsers.add(receiver);
            room.setAllowedUsers(allowedUsers);
            room.setCreationDate(System.currentTimeMillis());
            room.setLastPacketDate(System.currentTimeMillis());
            rooms.addRoom(room);
            return room.getRoomId();
        }
        return -1;
    }
}
