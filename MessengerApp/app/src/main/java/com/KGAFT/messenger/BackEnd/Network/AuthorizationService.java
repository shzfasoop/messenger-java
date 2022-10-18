package com.KGAFT.messenger.BackEnd.Network;

import com.KGAFT.messenger.BackEnd.Entities.User;

public class AuthorizationService {

    public static User authorize(String token){
        return (User) ServerHttpConnection.getDataFromServer(User.class, "/authorize", "token="+token, "GET");
    }

    public static boolean isUserExist(String login){
        return Boolean.parseBoolean(ServerHttpConnection.getRawDataFromServer("/isUserExist", "login="+login, "GET"));
    }
    public static User registerUser(String userLogin, String userName, String password){
        return (User) ServerHttpConnection.getDataFromServer(User.class, "/register", "name="+userName+"&"+"login="+userLogin+"&password="+password, "POST");
    }

    public static boolean checkAuthorization(String token){
        return Boolean.parseBoolean(ServerHttpConnection.getRawDataFromServer("/checkAuthorization", "token="+token, "GET"));
    }
    public static boolean checkConnection(){
        return Boolean.parseBoolean(ServerHttpConnection.getRawDataFromServer("/checkConnection", "", "GET"));
    }
    public static String firstTimeAuthorize(String login, String password){
        return ServerHttpConnection.getRawDataFromServer("/firstTimeAuthorize", "login="+login+"&password="+password, "GET");
    }
    public static void uploadIcon(String token , long iconId){
        ServerHttpConnection.getRawDataFromServer("/uploadUserIcon", "token="+token+"&fileId="+iconId, "POST");
    }
}
