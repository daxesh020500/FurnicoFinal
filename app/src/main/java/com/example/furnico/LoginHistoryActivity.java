package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.furnico.recycler.CartRecyclerAdapter;
import com.example.furnico.recycler.LoginHistoryAdapter;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.LoginHistory;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.furnico.R.layout.activity_login_history;

public class LoginHistoryActivity extends AppCompatActivity  {
ArrayList<LoginHistory> loginHistories;
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login_history);
        recyclerView = (findViewById(R.id.rv_recycle));


        //SharedPreferences sharedPreferences = getSharedPreferences("com.example.furnico", Context.MODE_PRIVATE);
        //String token = sharedPreferences.getString("userid","default");
        //encryptCustomer.setToken(token);
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        EncryptCustomer encryptCustomer = new EncryptCustomer();
        encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        Call<ArrayList<LoginHistory>> responses = iPostApi.loginHistory(encryptCustomer);
        Callback<ArrayList<LoginHistory>> userCallback = new Callback<ArrayList<LoginHistory>>() {
            @Override
            public void onResponse(Call<ArrayList<LoginHistory>> call, Response<ArrayList<LoginHistory>> response) {
                LoginHistoryAdapter loginHistoryAdapter = new LoginHistoryAdapter(response.body());
                recyclerView.setLayoutManager(new LinearLayoutManager(LoginHistoryActivity.this));
                recyclerView.setAdapter(loginHistoryAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<LoginHistory>> call, Throwable t) { }
        };
        responses.enqueue(userCallback);
    }

}