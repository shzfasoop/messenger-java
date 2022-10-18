package com.KGAFT.messenger.BackEnd.DataBase;

import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Column;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static Connection dataBaseConnection;
    private static ArrayList<Class> allowedTables = new ArrayList<>();
    public static void initialize(Connection connection){
        dataBaseConnection = connection;
    }
    protected static void allowTableEntity(Class tableEntityClass){
        allowedTables.add(tableEntityClass);
    }
    public static void delete(String tableName, String[] arguments, String[] values) throws SQLException {
        String sql = "DELETE FROM "+tableName;
        if(arguments!=null){
            sql+=" WHERE ";
            for (String argument : arguments) {
                sql+=argument+" AND " ;
            }
            sql = sql.substring(0, sql.length()-4);
        }

        sql+=";";
        PreparedStatement pstmt = dataBaseConnection.prepareStatement(sql);
        if(values!=null){
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i+1, values[i]);
            }
        }

        pstmt.execute();
    }
    public static List<Object> findByColumnValue(Class entityClass, String[] columnNames, String[] columnValues, String orderBy) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if(allowedTables.contains(entityClass)){
            String sql = "SELECT * FROM "+getClassName(entityClass.getName());
            if(columnNames!=null){
                sql+=" WHERE ";
                for (String columnName : columnNames) {
                    sql+=columnName+"=?";
                    sql+=" AND ";
                }
                sql = sql.substring(0, sql.length()-4);
            }

            sql+=orderBy==null?";":orderBy+";";
            PreparedStatement pstmt = dataBaseConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            if(columnValues!=null){
                for (int i = 0; i < columnValues.length; i++) {
                    pstmt.setObject(i+1, columnValues[i]);
                }
            }

            ResultSet resultSet = pstmt.executeQuery();
            return resultSetToEntity(entityClass, resultSet);
        }
        return null;
    }
    public static List<Object> resultSetToEntity(Class entityClass, ResultSet rs) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Object> results = new ArrayList<>();
        if(rs.first()){
            do{
                results.add(processResultSetToObject(entityClass, rs));
            }while (rs.next());
        }
        return results;
    }
    private static Object processResultSetToObject(Class entityClass, ResultSet resultSet) throws IllegalAccessException, InstantiationException, SQLException, InvocationTargetException {
        Object result = entityClass.newInstance();
        for (Field declaredField : entityClass.getDeclaredFields()) {
            if(declaredField.getAnnotation(Column.class)!=null){
                if (Integer.class.equals(declaredField.getType())) {
                    getSetterForField(entityClass, declaredField).invoke(result, resultSet.getInt(declaredField.getName()));
                } else if (Byte.class.equals(declaredField.getType())) {
                    getSetterForField(entityClass, declaredField).invoke(result, Byte.valueOf(resultSet.getByte(declaredField.getName())));
                } else if (Long.class.equals(declaredField.getType())) {
                    getSetterForField(entityClass, declaredField).invoke(result, Long.valueOf(resultSet.getLong(declaredField.getName())));
                } else if (Float.class.equals(declaredField.getType())) {
                    getSetterForField(entityClass, declaredField).invoke(result, resultSet.getFloat(declaredField.getName()));
                } else if (String.class.equals(declaredField.getType())) {
                    getSetterForField(entityClass, declaredField).invoke(result, resultSet.getString(declaredField.getName()));
                } else if (byte[].class.equals(declaredField.getType())) {
                    getSetterForField(entityClass, declaredField).invoke(result, resultSet.getBytes(declaredField.getName()));
                }

            }
        }
        return result;
    }

    public static void saveEntity(Object entity) throws SQLException, InvocationTargetException, IllegalAccessException {
        if(allowedTables.contains(entity.getClass())){
            PreparedStatement pstmt = prepareStatementForEntity(entity);
            pstmt.execute();
        }
    }
    private static PreparedStatement prepareStatementForEntity(Object entity) throws SQLException, InvocationTargetException, IllegalAccessException {
        PreparedStatement pstmt = dataBaseConnection.prepareStatement("INSERT INTO "+getClassName(entity.getClass().getName())+" "+prepareFieldsToInsert(entity), PreparedStatement.RETURN_GENERATED_KEYS);
        int counter = 0;
        for (Method method : getGetters(entity)) {
            counter++;
            pstmt.setObject(counter, method.invoke(entity, null));
        }
        return pstmt;
    }


    private static List<Method> getGetters(Object entity){
        List<Method> results = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            if(field.getAnnotation(Column.class)!=null){
                Method method = getGetterForField(entity, field);
                results.add(method);
            }
        }
        return results;
    }

    private static Method getSetterForField(Class entityClass, Field field){
        for (Method method : entityClass.getMethods()) {
            if(method.getModifiers() == Modifier.PUBLIC && method.getName().toLowerCase().contains("set")
                    && method.getName().toLowerCase().contains(field.getName().toLowerCase())){
                return method;
            }
        }
        return null;
    }
    private static Method getGetterForField(Object entity, Field field){
        for (Method method : entity.getClass().getMethods()) {
            if(method.getModifiers() == Modifier.PUBLIC && method.getName().toLowerCase().contains("get")
                    && method.getName().toLowerCase().contains(field.getName().toLowerCase())){
                return method;
            }
        }
        return null;
    }
    private static String prepareFieldsToInsert(Object entity){
        String sql = "(";
        for (Field field : entity.getClass().getDeclaredFields()) {
            sql+=field.getName()+", ";
        }
        sql = sql.substring(0, sql.length()-2);
        sql+=") VALUES(";
        for (int i = 0; i < entity.getClass().getDeclaredFields().length; i++) {
            sql+="?, ";
        }
        sql = sql.substring(0, sql.length()-2);
        sql+=");";
        return sql;
    }
    private static String getClassName(String name){
        String last = "";
        for(int counter = 0; counter<name.length(); counter++){
            if(name.charAt(counter)=='.'){
                last = "";
            }
            else{
                last+=name.charAt(counter);
            }
        }
        return last;
    }
}
