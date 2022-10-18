package com.KGAFT.messenger.BackEnd.Network;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.IOUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerHttpConnection {
    public static String getRawDataFromServer(String page, String arguments, String method){
        try {
            HttpURLConnection connection = prepareConnection(getServerAddress(), page, arguments, method);
            return IOUtil.inputStreamToString(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
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
    public static Object getDataFromServer(Class castClass, String page, String arguments, String method){
        Gson gson = new Gson();
        try {
            HttpURLConnection connection = prepareConnection(getServerAddress(), page, arguments, method);
            String json = IOUtil.inputStreamToString(connection.getInputStream());
            return gson.fromJson(json, castClass);
        } catch (IOException | InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Object> getArrayDataFromServer(Class castClass, String page, String arguments, String method){
        Gson gson = new Gson();
        try {
            HttpURLConnection connection = prepareConnection(getServerAddress(), page, arguments, method);
            JsonArray jsonArray = new JsonParser().parse(IOUtil.inputStreamToString(connection.getInputStream())).getAsJsonArray();
            List<Object> results = new ArrayList<>();
            jsonArray.forEach(jsonElement -> {
                results.add(gson.fromJson(jsonElement, castClass));
            });
            return results;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    private static HttpURLConnection prepareConnection(String url, String address, String arguments, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url+address+"?"+arguments).openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(!method.toLowerCase().equals("get"));
        connection.setRequestMethod(method);
        return connection;
    }

    private static String getServerAddress() throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return AppSettings.class.cast(Repository.findByColumnValue(AppSettings.class,
                null, null, null).get(0)).getServerHttpConnectionAddress();
    }
}
