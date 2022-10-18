package com.kgaft.michatmessengerserver.Service;

import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.UsersPassword;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.Repository.FileRepository;
import com.kgaft.michatmessengerserver.Database.Repository.UsersPasswordRepository;
import com.kgaft.michatmessengerserver.Database.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersPasswordRepository usersPasswordRepository;
    @Autowired
    private FileRepository fileRepository;
    public User createUser(String name, String login, String password){
        if(!usersRepository.existsByLogin(login)){
            User user = new User(name, login);
            usersRepository.save(user);
            UsersPassword userPassword = new UsersPassword();
            userPassword.setPassword(password);
            userPassword.setUserId(usersRepository.findUserByLogin(login).getId());
            usersPasswordRepository.save(userPassword);
            return user;
        }
        return null;
    }
    public User createUser(String name, String login, String password, long iconId){
        if(!usersRepository.existsByLogin(login)){
            User user = new User(name, login);
            user.setUserIconId(iconId);
            usersRepository.save(user);
            UsersPassword userPassword = new UsersPassword();
            userPassword.setPassword(password);
            userPassword.setUserId(usersRepository.findUserByLogin(login).getId());
            usersPasswordRepository.save(userPassword);
            return user;
        }
        return null;
    }
    public void setUserImage(long fileId, User user){
        user.setUserIconId(fileId);
        usersRepository.save(user);
    }
    public User findUserByNumber(String phoneNumber){
        return usersRepository.findByNumber(phoneNumber);
    }
    public void setUserPhoneNumber(User user, String phoneNumber){
        user.setNumber(phoneNumber);
        usersRepository.save(user);
    }
    public User getUserByLogin(String login){
        return usersRepository.findUserByLogin(login);
    }
    public void updateUserNameOrPassword(String userLogin, String userName, String userPassword, boolean nameChanging, boolean passwordChanging){
        User user = usersRepository.findUserByLogin(userLogin);
        UsersPassword usersPassword = usersPasswordRepository.findUsersPasswordByUserId(user.getId());
        if(nameChanging){
            user.setName(userName);
        }
        if(passwordChanging){
            usersPassword.setPassword(userPassword);
        }
        usersRepository.save(user);
        usersPasswordRepository.save(usersPassword);
    }
    public boolean isUserExists(String login){
        return usersRepository.existsByLogin(login);
    }
    public User findUserById(long userId){
        return usersRepository.findById(userId).get();
    }
}
