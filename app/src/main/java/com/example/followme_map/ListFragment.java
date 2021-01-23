package com.example.followme_map;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.followme_map.databinding.FragmentListBinding;


public class ListFragment extends Fragment {

    private FragmentListBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater);
        getChildFragmentManager().beginTransaction().replace(R.id.childFrame, new ClinicListFragment()).addToBackStack(null).commit();

        binding.paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab1);
                getChildFragmentManager().beginTransaction().replace(R.id.childFrame, new PaymentListFragment()).addToBackStack(null).commit();


            }
        });

        binding.clinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab2);
                getChildFragmentManager().beginTransaction().replace(R.id.childFrame, new ClinicListFragment()).addToBackStack(null).commit();

            }
        });


        return binding.getRoot();
    }
}
