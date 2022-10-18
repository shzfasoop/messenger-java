package com.kgaft.michatmessengerserver.Service;

import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.UsersPassword;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.Repository.UsersPasswordRepository;
import com.kgaft.michatmessengerserver.Database.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersPasswordRepository usersPasswordRepository;
    @Autowired
    private JWTTokenProvider tokens;
    public User authorizeByLoginAndPassword(String login, String password){
        User user = usersRepository.findUserByLogin(login);

        return usersPasswordRepository.findUsersPasswordByUserId(user.getId()).getPassword().equals(password)?user:null;
    }
    public String createTokenAndAuthorize(String login, String password){
        User user = usersRepository.findUserByLogin(login);
        UsersPassword usersPassword = usersPasswordRepository.findUsersPasswordByUserId(user.getId());
        if(usersPassword.getPassword().equals(password)){
            return tokens.createToken(login);
        }
        return null;
    }

    public boolean authorizeToken(String token){
        return tokens.authorizeToken(token);
    }
    public User getUserByToken(String token){
        if(tokens.authorizeToken(token)){
            return usersRepository.findUserByLogin(tokens.getUserLogin(token));
        }
        return null;
    }
}
