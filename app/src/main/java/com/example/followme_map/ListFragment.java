package com.example.followme_map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.followme_map.databinding.FragmentListBinding;


public class ListFragment extends Fragment {

    private FragmentListBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater);

        binding.paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab1);
                setFrag(0);
            }
        });

        binding.clinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tab.setBackgroundResource(R.drawable.tab2);
                setFrag(1);
            }
        });


        setFrag(0);

        return binding.getRoot();
    }

    private void setFrag(int n) {
        switch (n) {
            case 0:
                getChildFragmentManager().beginTransaction().replace(R.id.childFrame, new PaymentListFragment()).commit();
                break;
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.childFrame, new ClinicListFragment()).commit();
                break;
        }
    }

}
