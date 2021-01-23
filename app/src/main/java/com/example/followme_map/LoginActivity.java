package com.example.followme_map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.followme_map.databinding.ActivityLoginBinding;
import com.example.followme_map.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private final String TAG = "LoginActivity";
    static String patientToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAPI();

                //test
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                //>>>>>>>>>>>
            }
        });

        binding.JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

    }

    public void loginAPI() {

        final String id = binding.id.getText().toString();
        final String password = binding.password.getText().toString();


        //공란 체크
        if (id.equals("") || password.equals(""))
            Toast.makeText(getApplication(), "공란이 있습니다.", Toast.LENGTH_SHORT).show();

        else {
            String url = "http://172.26.3.122:8000/api/patient/login";
            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() { //응답을 잘 받았을 때 이 메소드가 자동으로 호출
                        @Override
                        public void onResponse(String response) {
                            System.out.println("로그인 요청됨");

                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                JSONObject patientObj = jsonObject.getJSONObject("patient_info");

                                String status = jsonObject.getString("status");
                                int patientId = patientObj.getInt("patient_id");

                                String patientName = patientObj.getString("patient_name");
                                String loginId = patientObj.getString("login_id");
                                String residentNumber = patientObj.getString("resident_number");
                                int postalCode = patientObj.getInt("postal_code");
                                String address = patientObj.getString("address");
                                String detailAddress = patientObj.getString("detail_address");
                                String phoneNumber = patientObj.getString("phone_number");
                                String notes = patientObj.getString("notes");
                                String createdAt = patientObj.getString("created_at");
                                String updatedAt = patientObj.getString("updated_at");

                                patientToken = jsonObject.getString("token");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Log.d(TAG, response);
                            Toast.makeText(getApplication(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },
                    new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                        @Override
                        public void onErrorResponse(VolleyError e) {
                            e.printStackTrace();
                            Log.i(TAG, "로그인 실패" + e.getMessage());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("login_id", id);
                    params.put("password", password);


                    return params;
                }
            };


            request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
            AppHelper.requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
            AppHelper.requestQueue.add(request);
        }


    }


    String getPatientToken() {
        return patientToken;
    }


}