package com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity;

import android.content.Intent;
import android.view.animation.AnimationUtils;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.FileNotEncrypted;
import com.KGAFT.messenger.BackEnd.Entities.User;
import com.KGAFT.messenger.BackEnd.Network.AuthorizationService;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.MainActivity;
import com.KGAFT.messenger.R;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class AuthorizationController {
    private static String login;
    private static String password;
    private static String name;
    private static User createdUser;
    private static File icon;
    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        AuthorizationController.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        AuthorizationController.password = password;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AuthorizationController.name = name;
    }
    public static boolean checkUserLogin(){
        return AuthorizationService.isUserExist(login);
    }
    public static boolean authorizeUser() throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException, InterruptedException {
        String token = AuthorizationService.firstTimeAuthorize(login, password);
        if(token!=null && !token.equals("null")){
            User user;
            if(createdUser!=null){
                user = createdUser;

            }
            else{
                user = AuthorizationService.authorize(token);
            }
            if(user!=null){
                try{
                    Repository.delete("User", new String[]{"login=?"}, new String[]{user.getLogin()});
                }catch (Exception e){

                }

                Repository.saveEntity(user);
                AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
                appSettings.setAppToken(token);
                appSettings.setCurrentUser(user.getId());
                appSettings.setLogin(login);
                appSettings.setPassword(password);
                Repository.delete("AppSettings", null, null);
                Repository.saveEntity(appSettings);
                return true;
            }

        }
        return false;

    }
    public static boolean registerUser() throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException, InterruptedException {
        User user;
        user = AuthorizationService.registerUser(login, name, password);
        if(user!=null){
            createdUser = user;
            if(icon==null){
                return authorizeUser();
            }
            else{
                if(authorizeUser()){
                    FileNotEncrypted fileNotEncrypted = FileService.uploadFile(icon);
                    AuthorizationService.uploadIcon(getAppToken(), fileNotEncrypted.getId());
                    return true;
                }
            }

        }
        return false;
    }
    public static void clearData(){
        login = null;
        password = null;
        name = null;
        icon = null;
    }

    public static File getIcon() {
        return icon;
    }

    public static void setIcon(File icon) {
        AuthorizationController.icon = icon;
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
