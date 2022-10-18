package com.KGAFT.messenger.FrontEnd.Activities.CreateChatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import com.KGAFT.messenger.BackEnd.Entities.User;
import com.KGAFT.messenger.BackEnd.Network.FileService;
import com.KGAFT.messenger.BackEnd.Network.UserInfoService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateChatController {
    private static ContentResolver contentResolver;
    private static Context context;
    public static List<ContactPreviewFragment> preparePreviews(){
        List<ContactPreviewFragment> results = new ArrayList<>();
        readContacts().keySet().forEach(phoneNumber->{
            User user = UserInfoService.findUserByPhoneNumber(phoneNumber);
            if(user!=null){
                Bitmap userIcon = null;
                try {
                    if(user.getUserIconId()!=0){
                        File file = FileService.downloadFile(user.getUserIconId(), context.getCacheDir().getAbsolutePath(), "userIcon"+user.getId());
                        userIcon = BitmapFactory.decodeFile(file.getAbsolutePath());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                ContactPreviewFragment contactPreviewFragment = new ContactPreviewFragment(user.getName(), user.getLogin());
                contactPreviewFragment.setContactImage(userIcon);
                results.add(contactPreviewFragment);
            }

        });
        return results;
    }

    @SuppressLint("Range")
    private static HashMap<String, String> readContacts(){
        HashMap<String, String> results = new HashMap<>();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))>0){
                    String id = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));
                    Cursor contactCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    if(contactCursor.moveToFirst()){
                        String phoneNumber = contactCursor.getString(contactCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        results.put(phoneNumber, name);
                    }
                }
            }while (cursor.moveToNext());
        }
        return results;
    }

    public static ContentResolver getContentResolver() {
        return contentResolver;
    }

    public static void setContentResolver(ContentResolver contentResolver) {
        CreateChatController.contentResolver = contentResolver;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        CreateChatController.context = context;
    }
}
