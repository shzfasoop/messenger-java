package com.KGAFT.messenger.BackEnd.DataBase;


import android.content.Context;

import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Column;
import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Id;
import com.KGAFT.messenger.BackEnd.DataBase.Annotations.Table;
import com.KGAFT.messenger.BackEnd.DataBase.Exception.DeclaringFieldException;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import kotlin.TypeCastException;

public class DataBase {
    private static Connection connection;
    private static ArrayList<Class> tablesToScan = new ArrayList<>();

    public static void initialize(Context context) throws SQLException {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            connection = DriverManager.getConnection("jdbc:sqlite:" + context.getFilesDir() + "MessengerDataBase.db");
            Repository.initialize(connection);
            initTableCluster();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void registerTableForInitializing(Class classToRegister) {
        tablesToScan.add(classToRegister);
        Repository.allowTableEntity(classToRegister);
    }

    private static void initTableCluster() {
        tablesToScan.forEach(classForScanning -> {
            if (classForScanning.getAnnotation(Table.class) != null) {
                try {
                    createTable(classForScanning);
                } catch (DeclaringFieldException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createTable(Class classForScanning) throws DeclaringFieldException, SQLException {
        String sql = "CREATE TABLE " + getClassName(classForScanning.getName()) + "(";
        Field idField = null;
        for (Field declaredField : classForScanning.getDeclaredFields()) {
            if (declaredField.getAnnotation(Column.class) != null) {
                if (checkIsGetterAndSetterPresented(classForScanning, declaredField.getName())) {
                    if (declaredField.getAnnotation(Id.class) != null) {
                        idField = declaredField;
                        sql += fieldToSQl(declaredField) + " PRIMARY KEY AUTOINCREMENT, ";
                    }else{
                        sql += fieldToSQl(declaredField) + ", ";
                    }

                } else {
                    throw new DeclaringFieldException("Getter or setter not presented for field with name " + declaredField.getName());
                }
            }
        }
        if (idField != null) {
            sql = sql.substring(0, sql.length()-2);
            sql += ");";
            executeSQl(sql);
        } else {
            throw new NullPointerException("Cannot find id field for table");
        }
    }

    private static void executeSQl(String sql) throws SQLException {
        connection.prepareStatement(sql).execute();
    }

    private static boolean checkIsGetterAndSetterPresented(Class classForScanning, String fieldName) {
        boolean getterPresented = false;
        boolean setterPresented = false;
        for (Method method : classForScanning.getMethods()) {
            if (method.getModifiers() == Modifier.PUBLIC && method.getName().toLowerCase().contains("get")
                    && method.getName().toLowerCase().contains(fieldName.toLowerCase())) {
                getterPresented = true;

            } else if (method.getModifiers() == Modifier.PUBLIC && method.getName().toLowerCase().contains("set")
                    && method.getName().toLowerCase().contains(fieldName.toLowerCase())) {
                setterPresented = true;
            }
            if (getterPresented && setterPresented) {
                return true;
            }
        }
        if (getterPresented && setterPresented) {
            return true;
        }
        return false;

    }

    private static String fieldToSQl(Field field) {
        String sql = field.getName();
        Class<?> type = field.getType();
        if (Integer.class.equals(type)) {
            sql += " INTEGER";
        } else if (Byte.class.equals(type)) {
            sql += " INTEGER";
        } else if (Long.class.equals(type)) {
            sql += " INTEGER";
        } else if (Float.class.equals(type)) {
            sql += " REAL";
        } else if (String.class.equals(type)) {
            sql += " TEXT";
        } else if (byte[].class.equals(type)) {
            sql += " BLOB";
        } else {
            throw new TypeCastException("cannot convert java type: " + field.getType() + " to sql");
        }
        return sql;
    }

    private static String getClassName(String name) {
        String last = "";
        for (int counter = 0; counter < name.length(); counter++) {
            if (name.charAt(counter) == '.') {
                last = "";
            } else {
                last += name.charAt(counter);
            }
        }
        return last;
    }
}
