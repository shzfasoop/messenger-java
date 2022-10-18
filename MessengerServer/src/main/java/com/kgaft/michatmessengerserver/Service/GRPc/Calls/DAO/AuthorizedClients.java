package com.kgaft.michatmessengerserver.CallServer.DAO;

import com.kgaft.michatmessengerserver.Database.Entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope(scopeName = "singleton")
public class AuthorizedClients {
    private HashMap<String, User> authorizedUsers = new HashMap<>();

    public HashMap<String, User> getAuthorizedUsers() {
        return authorizedUsers;
    }
    public User getUserByHost(String host){
        return authorizedUsers.get(host);
    }
    public void addAuthorizedUser(String host, User client) {
        try{
            authorizedUsers.remove(host);
        }catch (Exception e){

        }
        authorizedUsers.put(host, client);
    }


}
