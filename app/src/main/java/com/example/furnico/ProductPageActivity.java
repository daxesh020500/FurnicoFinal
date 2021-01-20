package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.furnico.retrofit.model.Cart;
import com.example.furnico.retrofit.model.EncryptCart;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductPageActivity extends AppCompatActivity {
    String productName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        int prodid = getIntent().getIntExtra("product",1);//getting the product id from the previous activity
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<Product> responses = iPostApi.getProductById(prodid);
        Callback<Product> userCallback = new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                productName = product.getName();
                Glide.with(ProductPageActivity.this).load(product.getImage()).placeholder(R.drawable.ic_google).into((ImageView)findViewById(R.id.iv_product_page_image));
                ((TextView)findViewById(R.id.tv_product_page_category)).setText(product.getCategory()+"");
                ((TextView)findViewById(R.id.tv_product_page_name)).setText(product.getName());
                ((TextView)findViewById(R.id.tv_product_page_desc)).setText(product.getDescription());
                ((TextView)findViewById(R.id.tv_product_page_price)).setText(product.getBestPrice()+"");
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            }
        };
        responses.enqueue(userCallback);

        (findViewById(R.id.bt_product_page_button)).setOnClickListener(v -> {
            Intent merchantSelect = new Intent(ProductPageActivity.this,MerchantSelectRecycle.class);
            merchantSelect.putExtra("productName",productName);
            merchantSelect.putExtra("productId",prodid);
            startActivity(merchantSelect);
        });


    }
}