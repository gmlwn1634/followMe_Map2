package com.example.followme_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.followme_map.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater);


        binding.flowStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FlowActivity.class);
                startActivity(intent);
            }
        });

        binding.paymentBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}
