package com.example.followme_map;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.followme_map.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //대기순번 laravel-pusher
        PusherOptions pusherOptions = new PusherOptions();
        pusherOptions.setCluster("ap3");
        Pusher pusher = new Pusher("7ed3a4ce8ebfe9741f98", pusherOptions);

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.i("Pusher", "State changed from " + change.getPreviousState() + " to " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.i("Pusher", "There was a problem connecting " + "\ncode" + code + "\nmessage" + message + "\nException" + e);
            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe("FollowMe_standby_number");

        channel.bind("FollowMe_standby_number", new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {
                //대기순번 바뀌었을 때 처리할 부분
                Log.i("Pusher", "Received event with data: " + event.toString());
            }

        });


        binding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_info:
                        setFrag(0);
                        break;
                    case R.id.action_home:
                        setFrag(1);
                        break;
                    case R.id.action_list:
                        setFrag(2);
                        break;
                }

                return true;
            }
        });

        setFrag(1); //첫 프래그먼트 선택

    } //onCreate()


    //fragment 교체
    private void setFrag(int n) {
        switch (n) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new InfoFragment()).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();

                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ListFragment()).commit();
                break;
        }
    }


}