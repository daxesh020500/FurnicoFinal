package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.furnico.recycler.CartRecyclerAdapter;
import com.example.furnico.retrofit.model.Cart;
import com.example.furnico.retrofit.model.CartWithProduct;
import com.example.furnico.retrofit.model.EncryptCart;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.model.ResponseString;
import com.example.furnico.retrofit.model.Transaction;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartRecycleActivity extends AppCompatActivity implements CartRecyclerAdapter.UserDataInterface{
    CartRecyclerAdapter cartRecyclerAdapter;
    ArrayList<Cart> Carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_recycle);
        loadCart();

        findViewById(R.id.bt_activity_cart_recycle_view_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartRecycleActivity.this,UserDetails.class));
            }
        });

        findViewById(R.id.btn_cart_buy).setOnClickListener(v -> {
            Intent intent = new Intent(CartRecycleActivity.this, MerchantSelectRecycle.class);
            startActivity(intent);
        });
    }

    public void loadCart(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.furnico",MODE_PRIVATE);
        RecyclerView recyclerView = (findViewById(R.id.rv_activity_cart_recycle_view));
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        EncryptCustomer encryptCustomer = new EncryptCustomer();
        System.out.println("MY TOKEN: -> " + sharedPreferences.getString("userid"," "));
        encryptCustomer.setToken(sharedPreferences.getString("userid"," "));
        String token = sharedPreferences.getString("userid"," ");


        Call<ArrayList<Cart>> responses = iPostApi.fetchCart(encryptCustomer);

        Callback<ArrayList<Cart>> userCallback = new Callback<ArrayList<Cart>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart>> call, Response<ArrayList<Cart>> response) {
                Carts = response.body();
                if(Carts.size()>0) {
                    //    findViewById(R.id.tv_cart_empty).setVisibility(View.GONE);}else{findViewById(R.id.btn_cart_buy).setVisibility(View.INVISIBLE);}
                    CartRecyclerAdapter cartRecyclerAdapter = new CartRecyclerAdapter(Carts, CartRecycleActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CartRecycleActivity.this));
                    recyclerView.setAdapter(cartRecyclerAdapter);
                    System.out.println(response.body());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Cart>> call, Throwable t) {
                //t.printStackTrace();
            }
        };
        responses.enqueue(userCallback);
    }

    @Override
    public void onIncreaseClick(Cart userData) {
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        userData.setQuantity(userData.getQuantity()+1);
        EncryptCart encryptCart = new EncryptCart();
        encryptCart.setCart(userData);
        //encryptCart.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        encryptCart.setToken(getSharedPreferences("com.example.furnico",MODE_PRIVATE).getString("userid",""));
        Call<String> responses = iPostApi.changeQuantityInCart(encryptCart);
        Callback<String> userCallback = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) { }
            @Override
            public void onFailure(Call<String> call, Throwable t) { }
        };
        responses.enqueue(userCallback);
        loadCart();
    }

    @Override
    public void onDecreaseClick(Cart userData) {
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        userData.setQuantity(userData.getQuantity()-1);
        EncryptCart encryptCart = new EncryptCart();
        encryptCart.setCart(userData);
        encryptCart.setToken(getSharedPreferences("com.example.furnico",MODE_PRIVATE).getString("userid",""));

        Call<String> responses = iPostApi.changeQuantityInCart(encryptCart);
        Callback<String> userCallback = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) { }
            @Override
            public void onFailure(Call<String> call, Throwable t) { }
        };
        responses.enqueue(userCallback);
        loadCart();
    }



    @Override
    public void onDeleteClick(Cart userData, int position) {
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        userData.setQuantity(0);
        EncryptCart encryptCart = new EncryptCart();
        encryptCart.setCart(userData);
        encryptCart.setToken(getSharedPreferences("com.example.furnico",MODE_PRIVATE).getString("userid",""));
        //encryptCart.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        Call<String> responses = iPostApi.changeQuantityInCart(encryptCart);
        Callback<String> userCallback = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) { }
            @Override
            public void onFailure(Call<String> call, Throwable t) { }
        };
        responses.enqueue(userCallback);
        loadCart();
    }

}