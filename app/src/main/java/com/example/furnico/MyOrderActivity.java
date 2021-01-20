package com.example.furnico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.Orders;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.recycler.RecyclerViewMyOrderAdapter;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyOrderActivity extends AppCompatActivity implements RecyclerViewMyOrderAdapter.UserDataInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        EncryptCustomer encryptCustomer = new EncryptCustomer();
        encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        Call<ArrayList<Orders>> response = iPostApi.getOrders(encryptCustomer);
        Callback<ArrayList<Orders>> callback = new Callback<ArrayList<Orders>>() {
            @Override
            public void onResponse(Call<ArrayList<Orders>> call, Response<ArrayList<Orders>> response) {
                RecyclerView recyclerView = findViewById(R.id.recyclerView_mainorder);
                RecyclerViewMyOrderAdapter recyclerViewAdapter = new RecyclerViewMyOrderAdapter(response.body(), MyOrderActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Orders>> call, Throwable t) {

            }
        };
        response.enqueue(callback);
        findViewById(R.id.ibt_my_order_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrderActivity.this, AccountOptions.class));
            }
        });
    }

    @Override
    public void onUserClick(Product productInfo) {
        Intent intent = new Intent(MyOrderActivity.this, ProductListActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "Image Clicked for" + productInfo.getProductName(), Toast.LENGTH_SHORT).show();


    }
}