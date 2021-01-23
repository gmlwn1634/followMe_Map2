package com.example.followme_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.followme_map.databinding.ActivityPaymentBinding;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {


    private ActivityPaymentBinding binding;
    private final String TAG = "PaymentActivity";


    //recyclerView
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<PaymentInfo> paymentInfoArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //recyclerView
        binding.recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,1);
        binding.recyclerView.setLayoutManager(mLayoutManager);

        paymentInfoArrayList.add(new PaymentInfo("09:00","혈액검사(230)",20000));
        paymentInfoArrayList.add(new PaymentInfo("09:25","MRI검사(324)",150000));
        paymentInfoArrayList.add(new PaymentInfo("10:02","CT검사(328)",50000));

        PaymentAdapter paymentAdapter = new PaymentAdapter(paymentInfoArrayList);
        binding.recyclerView.setAdapter(paymentAdapter);




    }
}