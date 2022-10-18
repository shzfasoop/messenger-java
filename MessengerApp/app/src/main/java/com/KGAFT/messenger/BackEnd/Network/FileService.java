package com.KGAFT.messenger.BackEnd.Network;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.FileEncrypted;
import com.KGAFT.messenger.BackEnd.Entities.FileNotEncrypted;
import com.KGAFT.messenger.BackEnd.IOUtil;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class FileService {
    private static String crlf = "\r\n";
    private static String twoHyphens = "--";
    private static String boundary =  "*****";

    public static File downloadFile(com.KGAFT.messenger.BackEnd.Entities.FileNotEncrypted fileInfo, String destination, String name) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(getDownloadNotEncryptedFileUrl()+"?token="+getAppToken()+"&"+"fileId="+fileInfo.getId()).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        File file = new File(destination+"/"+name);
        FileOutputStream fos = new FileOutputStream(file);
        connection.connect();
        IOUtil.writeInputStreamToOutputStream(connection.getInputStream(), fos);
        fos.flush();
        return file;
    }
    public static File downloadFile(long fileId, String destination, String name) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(getDownloadNotEncryptedFileUrl()+"?token="+getAppToken()+"&"+"fileId="+fileId)).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        try{
            File file = new File(destination+"/"+name+"."+getFileExtension(name));
            FileOutputStream fos = new FileOutputStream(file);
            connection.connect();
            IOUtil.writeInputStreamToOutputStream(connection.getInputStream(), fos);
            fos.flush();
            return file;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
    }
    public static File downloadEncryptedFile(long fileId, String destination, String name, byte[] encryptionKey) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(getDownloadEncryptedFileUrl()+"?token="+getAppToken()+"&"+"fileId="+fileId)).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        try{
            File file = generateRandomFileName(destination);
            FileOutputStream fos = new FileOutputStream(file);
            connection.connect();
            IOUtil.writeInputStreamToOutputStream(connection.getInputStream(), fos);
            fos.flush();
            File result = new File(destination+"/"+name);
            try {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"));
                CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(result), cipher);
                IOUtil.writeInputStreamToOutputStream(new FileInputStream(file), cos);
                cos.flush();
                cos.close();
                return result;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }

            return file;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
    }
    public static FileEncrypted uploadEncryptedFile(File file, long chatId, String fileName){
        try{
            URL url = new URL(getUploadEncryptedFileUrl()+"?token="+getAppToken()+"&fileName="+fileName+"&chatId="+chatId);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());
            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" +
                    "file" + "\";filename=\"" +
                    fileName + "\"" +  crlf);
            request.writeBytes(crlf);
            IOUtil.writeInputStreamToOutputStream(new FileInputStream(file), request);
            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary +
                    twoHyphens + crlf);

            request.flush();
            request.close();
            httpUrlConnection.connect();
            return new Gson().fromJson(IOUtil.inputStreamToString(httpUrlConnection.getInputStream()), FileEncrypted.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static File saveTempEncryptedFile(String cacheDirectory, File notEncryptedFile, byte[] encryptionKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"));
        File file = generateRandomFileName(cacheDirectory);
        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(file), cipher);
        IOUtil.writeInputStreamToOutputStream(new FileInputStream(notEncryptedFile), cos);
        cos.flush();
        cos.close();
        return file;
    }
    public static FileNotEncrypted uploadFile(File file){
        try{
            URL url = new URL(getUploadNotEncryptedFileUrl()+"?token="+getAppToken()+"&fileName="+file.getName());
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());
            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" +
                    "file" + "\";filename=\"" +
                    file.getName() + "\"" +  crlf);
            request.writeBytes(crlf);
            IOUtil.writeInputStreamToOutputStream(new FileInputStream(file), request);
            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary +
                    twoHyphens + crlf);

            request.flush();
            request.close();
            httpUrlConnection.connect();
            return new Gson().fromJson(IOUtil.inputStreamToString(httpUrlConnection.getInputStream()), FileNotEncrypted.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static FileNotEncrypted getFileInfo(long fileId){
        return (FileNotEncrypted) ServerHttpConnection.getDataFromServer(FileNotEncrypted.class, "/getFileInfo", "token="+getAppToken()+"&fileId="+fileId, "GET");
    }
    public static FileEncrypted getFileEncryptedInfo(long fileId){
        return (FileEncrypted) ServerHttpConnection.getDataFromServer(FileEncrypted.class, "/getFileEncryptedInfo", "token="+getAppToken()+"&fileId="+fileId, "GET");
    }
    public static String getFileExtension(String fileName){
        String ext = "";
        boolean startedExtension = false;
        for(int counter = 0; counter<fileName.length(); counter++){
            if(startedExtension){
                ext+=fileName.charAt(counter);
            }
            if(fileName.charAt(counter)=='.'){
                startedExtension = true;
                ext = "";
            }

        }
        return ext;
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
    private static String getUploadNotEncryptedFileUrl(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getServerHttpConnectionAddress()+"/uploadFile";
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
    private static String getUploadEncryptedFileUrl(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getServerHttpConnectionAddress()+"/uploadEncryptedFile";
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
    private static String getDownloadNotEncryptedFileUrl(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getServerHttpConnectionAddress()+"/getFileContent";
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
    private static String getDownloadEncryptedFileUrl(){
        try {
            AppSettings appSettings = (AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0);
            return appSettings.getServerHttpConnectionAddress()+"/getFileEncryptedContent";
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
    public static File generateRandomFileName(String baseDir){
        Random random = new Random();
        File file = new File(baseDir+"/"+"temp"+random.nextLong());
        while(file.exists()){
            file = new File(baseDir+"/"+"temp"+random.nextLong());
        }
        return file;
    }
}
