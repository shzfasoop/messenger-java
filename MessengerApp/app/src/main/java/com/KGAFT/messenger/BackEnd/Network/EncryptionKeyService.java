package com.KGAFT.messenger.BackEnd.Network;

import android.util.Base64;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.EncryptionKey;


import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionKeyService {

    public static EncryptionKey getEncryptionKeyForChat(long chatId){
        byte[] encryptionPinCode  = getEncryptionKeysPinCode();
        if(encryptionPinCode==null){
            registerNewPinCodeForEncryptionKeys();
            encryptionPinCode = getEncryptionKeysPinCode();
        }
        EncryptionKey encryptionKey = (EncryptionKey) ServerHttpConnection.getDataFromServer(EncryptionKey.class, "/getEncryptionKey", "token="+getAppToken()+"&chatId="+chatId, "GET");
        encryptionKey.setDecryptedKey(Base64.decode(encryptionKey.getEncryptionKey(), Base64.DEFAULT));
        try {
            return decryptEncryptionKey(encryptionPinCode, encryptionKey);
        }
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException | InvalidKeyException e) {
            registerNewPinCodeForEncryptionKeys();
            encryptionPinCode = getEncryptionKeysPinCode();
            try {
                return decryptEncryptionKey(encryptionPinCode, encryptionKey);
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static EncryptionKey decryptEncryptionKey(byte[] pinCode, EncryptionKey encryptionKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(pinCode, "AES"));
        encryptionKey.setDecryptedKey(cipher.doFinal(encryptionKey.getDecryptedKey(), 0, encryptionKey.getDecryptedKey().length));
        return encryptionKey;
    }
    private static void registerNewPinCodeForEncryptionKeys(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            byte[] pinCode = keyGenerator.generateKey().getEncoded();
            String pinCodeString = Base64.encodeToString(pinCode, Base64.DEFAULT);
            String[] loginAndPassword = getAppAuthCredentials();
            ServerHttpConnection.getRawDataFromServer("/registerEncryptionKeyPinCode", "login="+loginAndPassword[0]+"&password="+loginAndPassword[1]+"&pinCode="+pinCodeString, "POST");
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            appSettings.setPinCodeForEncryptionKeys(pinCode);
            Repository.delete("AppSettings", null, null);
            Repository.saveEntity(appSettings);
        } catch (NoSuchAlgorithmException | SQLException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }


    private static String getAppToken(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getAppToken();
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static byte[] getEncryptionKeysPinCode(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getPinCodeForEncryptionKeys();
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String[] getAppAuthCredentials(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return new String[]{appSettings.getLogin(), appSettings.getPassword()};
        } catch (SQLException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
