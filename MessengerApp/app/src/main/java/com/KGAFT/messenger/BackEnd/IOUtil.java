package com.KGAFT.messenger.BackEnd;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

public class IOUtil {
    public static void writeInputStreamToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        int read;
        byte[] buffer = new byte[4*1024];
        while((read=inputStream.read(buffer, 0, buffer.length))!=-1){
            outputStream.write(buffer, 0, read);
        }
    }
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        String result = "";
        byte[] buffer = new byte[4*1024];
        int read;
        while((read=inputStream.read(buffer, 0, buffer.length))!=-1){
            result+=new String(buffer, 0, read);
        }
        return result;
    }
    public static File transferUriToCacheDir(Uri file, ContentResolver contentResolver, String cacheDirectory){
        File fileCache = new File(cacheDirectory+"/"+getUriFileName(file, contentResolver));
        try {
            FileOutputStream fos = new FileOutputStream(fileCache);
            InputStream uriIs = contentResolver.openInputStream(file);
            writeInputStreamToOutputStream(uriIs, fos);
            fos.flush();
            uriIs.close();
            fos.close();
            return fileCache;
        } catch (IOException e) {
            return null;
        }

    }


    @SuppressLint("Range")
    public static String getUriFileName(Uri file, ContentResolver contentResolver){
        Cursor fileInfo = contentResolver.query(file, null, null, null, null);
        fileInfo.moveToFirst();
        return fileInfo.getString(fileInfo.getColumnIndex(OpenableColumns.DISPLAY_NAME));
    }
}
