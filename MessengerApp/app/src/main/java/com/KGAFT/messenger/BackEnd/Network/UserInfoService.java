package com.KGAFT.messenger.BackEnd.Network;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserInfoService {
    public static User findUserByPhoneNumber(String phoneNumber){
        return (User)ServerHttpConnection.getDataFromServer(User.class, "/getUserByPhoneNumber", "token="+getAppToken()+"&phoneNumber="+phoneNumber, "GET");
    }
    public static long createRoomForCall(long receiverId){
        return Long.parseLong(ServerHttpConnection.getRawDataFromServer("/callToUser", "token="+getAppToken()+"&receiverId="+receiverId, "POST"));
    }
    private static String getAppToken(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getAppToken();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
