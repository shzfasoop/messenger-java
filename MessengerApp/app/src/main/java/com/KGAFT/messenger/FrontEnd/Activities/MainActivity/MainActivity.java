package com.KGAFT.messenger.FrontEnd.Activities.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.KGAFT.messenger.BackEnd.Entities.Chat;
import com.KGAFT.messenger.BackEnd.Entities.Message;
import com.KGAFT.messenger.BackEnd.Network.MessageService;
import com.KGAFT.messenger.BackEnd.Services.CallService;
import com.KGAFT.messenger.BackEnd.Services.NotificationService;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.CallsFragment.CallsListFragment;
import com.KGAFT.messenger.FrontEnd.Activities.MainActivity.Fragments.ChatsFragment.ChatListFragment;
import com.KGAFT.messenger.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.charset.StandardCharsets;
//@TODO ADD_LAST_ACTIVITY_SUUPPORT
public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment = new ChatListFragment(this);
        if(!NotificationService.isRunning){
            startService(new Intent(this, NotificationService.class));
        }
        if(!CallService.isRunning)
        {
            startService(new Intent(this, CallService.class));
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentsContainer, currentFragment).commitNow();
        ((BottomNavigationView)findViewById(R.id.fragmentSwitcher)).setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.chatsItem:
                    getSupportFragmentManager().beginTransaction().remove(currentFragment).commitNow();
                    currentFragment = new ChatListFragment(this);
                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentsContainer, currentFragment).commitNow();
                    break;
                case R.id.callsItem:
                    getSupportFragmentManager().beginTransaction().remove(currentFragment).commitNow();
                    currentFragment = new CallsListFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragmentsContainer, currentFragment).commitNow();
                    break;
            }
            return true;
        });
        initSearch();
    }
    private void initSearch(){
        findViewById(R.id.searchButton).setOnClickListener(event->{
            findViewById(R.id.searchButton).startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_fastest));
            findViewById(R.id.searchBar).setVisibility(View.VISIBLE);
            findViewById(R.id.searchBar).startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_from_right_to_start_pos_fastest));
            ((EditText)findViewById(R.id.searchField)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                        if(currentFragment.getClass().equals(ChatListFragment.class)){
                            ((ChatListFragment)currentFragment).refreshSearch(((TextView)findViewById(R.id.searchField))
                                    .getText().toString());
                        }
                    }
                    return true;
                }
            });
        });
        findViewById(R.id.backButton).setOnClickListener(event->{
            findViewById(R.id.searchBar).setVisibility(View.INVISIBLE);
            if(currentFragment.getClass().equals(ChatListFragment.class)){
                ((ChatListFragment)currentFragment).closeSearch();
            }
        });
    }
}