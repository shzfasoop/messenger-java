package com.KGAFT.messenger.FrontEnd.Activities.ChatActivity;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.fragment.app.FragmentManager;

import com.KGAFT.messenger.BackEnd.DataBase.Repository;
import com.KGAFT.messenger.BackEnd.Entities.AppSettings;
import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.BackEnd.Network.OnMessageReceived;
import com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments.DateFragment;
import com.KGAFT.messenger.FrontEnd.Activities.ChatActivity.Fragments.MessageTopic;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class MessageController implements OnMessageReceived {
    private int containerTarget;
    private LinkedHashMap<Long, List<MessageTopic>> messages = new LinkedHashMap<>();
    private Long lastDate;
    private Activity activity;
    private FragmentManager fragmentManager;
    private List<Message> drawnMessages = new ArrayList<>();
    public MessageController(int containerTarget, Activity activity, FragmentManager fm) {
        this.containerTarget = containerTarget;
        this.activity = activity;
        this.fragmentManager = fm;
    }

    @Override
    public void messageReceived(Message message) {
        if(!drawnMessages.contains(message)){
            checkDate(message.getDate());
            List<MessageTopic> messagesTopics = messages.get(lastDate);
            try {
                if (messagesTopics.get(messagesTopics.size() - 1).getUserLogin().equals(message.getSender().getLogin())) {
                    MessageTopic messageTopic = new MessageTopic(getAppLogin().equals(message.getSender().getLogin()) ? MessageTopic.OUR_TOPIC : MessageTopic.OTHER_TOPIC,
                            message.getSender().getLogin());
                    messageTopic.addMessageToDraw(message);
                    messagesTopics.add(messageTopic);
                } else {
                    messagesTopics.get(messagesTopics.size() - 1).addMessageToDraw(message);
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
                if (messagesTopics == null) {
                    messagesTopics = new ArrayList<>();
                    messages.replace(lastDate, messagesTopics);
                }
                MessageTopic messageTopic = new MessageTopic(getAppLogin().equals(message.getSender().getLogin()) ? MessageTopic.OUR_TOPIC : MessageTopic.OTHER_TOPIC,
                        message.getSender().getLogin());
                messageTopic.addMessageToDraw(message);
                messagesTopics.add(messageTopic);
            }
            drawnMessages.add(message);
            drawElements();
        }


    }

    private void drawElements() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                messages.forEach((date, messagesArray) -> {

                    if(!messagesArray.get(0).isDrown()){
                        fragmentManager.beginTransaction().add(containerTarget, new DateFragment(date)).commitNow();
                    }
                    messagesArray.forEach(messageTopic -> {
                        if(!messageTopic.isDrown()){
                            fragmentManager.beginTransaction().add(containerTarget, messageTopic).commitNow();
                            messageTopic.setDrown(true);
                        }
                        messageTopic.drawElements();
                    });
                });
            }
        };
        RunnableFuture<Void> task = new FutureTask<>(runnable, null);
        activity.runOnUiThread(task);
        try {
            task.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void checkDate(long currentMessageDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentMessageDate);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long timeInMs = calendar.getTimeInMillis();
        if (lastDate != null) {
            if (timeInMs - lastDate >= 24 * 60 * 60 * 60) {
                lastDate = timeInMs;
                messages.put(lastDate, new ArrayList<>());
            }
        } else {
            lastDate = timeInMs;
            messages.put(lastDate, new ArrayList<>());
        }
    }

    private static String getAppLogin() {
        try {
            String login = ((AppSettings) Repository.findByColumnValue(AppSettings.class, null, null, null).get(0)).getLogin();
            return login;
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
