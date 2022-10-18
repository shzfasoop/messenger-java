package com.KGAFT.messenger.BackEnd.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.BackEnd.Network.MessageService;
import com.KGAFT.messenger.BackEnd.Network.OnMessageReceived;
import com.KGAFT.messenger.FrontEnd.Activities.AuthorizationActivity.AuthorizationActivity;
import com.KGAFT.messenger.R;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class NotificationService extends Service implements OnMessageReceived {
    public static boolean isRunning = false;
    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        Thread thread = MessageService.prepareNotificationsThread(this::messageReceived, new AtomicBoolean(true));
        thread.start();
        return Service.START_STICKY;
    }

    @Override
    public void messageReceived(Message message) {
        sendNotify("MiChat", message.getSender().getLogin(), new String(message.getMessageTextContent()));
    }
    private void sendNotify(String appName, String title, String messageText) {
        NotificationManager mNotificationManager;  //Creating notifier manager

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(), "notify_001"); //Create builder of notifications
        Intent ii = new Intent(getApplicationContext(), AuthorizationActivity.class); //Creating intent of mainActivity
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0); //Creating pending intent for mainActivity intent

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(messageText);
        bigText.setBigContentTitle(title);
        bigText.setSummaryText(messageText);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.ic_logo);
        mBuilder.setContentTitle(messageText);
        mBuilder.setContentText(messageText);
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "MessagesChannel";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    appName,
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        Random rand = new Random();
        int id = rand.nextInt(1000);
        mNotificationManager.notify(id, mBuilder.build());
    }
}