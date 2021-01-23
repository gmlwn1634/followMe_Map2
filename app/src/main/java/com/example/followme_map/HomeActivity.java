package com.example.followme_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.followme_map.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment()).addToBackStack(null).commit();


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab_info);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new InfoFragment()).addToBackStack(null).commit();

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab_home);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment()).addToBackStack(null).commit();


            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab_list);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ListFragment()).addToBackStack(null).commit();


            }
        });

    } //onCreate()

}