package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.furnico.recycler.ProductRecyclerActivityAdapter;
import com.example.furnico.retrofit.model.PageVars;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListActivity extends AppCompatActivity implements ProductRecyclerActivityAdapter.ProductRecyclerInterface{

    ProductRecyclerActivityAdapter productRecyclerActivityAdapter;
    ArrayList<Product> products;
    int page = 0;
    int category = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        if(page == 0){ (findViewById(R.id.btn_list_previous_page)).setVisibility(View.INVISIBLE); }
        findViewById(R.id.ibt_product_list_cart).setOnClickListener(v -> {
            startActivity(new Intent(ProductListActivity.this,CartRecycleActivity.class));
        });
        findViewById(R.id.bt_product_list_menu).setOnClickListener(v -> {
            startActivity(new Intent(ProductListActivity.this,UserDetails.class));
        });
    }
    //Elastic Search Query

    @Override
    protected void onStart() {
        super.onStart();
        int mode = getIntent().getIntExtra("mode", 0);
        (findViewById(R.id.btn_list_next_page)).setOnClickListener(v -> { page++;
        loadProductList(new PageVars(category,page,10),mode); });
        (findViewById(R.id.btn_list_previous_page)).setOnClickListener(v -> { page--;
        loadProductList(new PageVars(category,page,10),mode); });
        loadProductList(new PageVars(category,page,10),mode);
    }

    public void loadProductList(PageVars pageVars,int mode){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
        pageVars.setCategory(getIntent().getIntExtra("category", 1));
        Call<ArrayList<Product>> responses;
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        responses = iPostApi.fetchAllPaged(pageVars);


        if(mode==0) responses = iPostApi.fetchAllPaged(pageVars);
        else if(mode == 1 ) responses = iPostApi.fetchAllByCategory(pageVars);
        else if(mode==2) responses = iPostApi.searchQuery(getIntent().getStringExtra("query"));

        responses.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                System.out.println("THIS IS THE RESPONSE PLEASE TELL ME"+response);
                products = response.body();
                System.out.println("THIS IS THE PRODUCTS PLEASE TELL ME"+products);
                if(mode!=2) {
                    if (page == 0) {
                        (findViewById(R.id.btn_list_previous_page)).setVisibility(View.INVISIBLE);
                        (findViewById(R.id.btn_list_next_page)).setVisibility(View.VISIBLE);
                    }
                    if (page > 0) {
                        (findViewById(R.id.btn_list_previous_page)).setVisibility(View.VISIBLE);
                    }
                    if (products.size() == 0)
                        (findViewById(R.id.btn_list_next_page)).setVisibility(View.INVISIBLE);
                    else {
                        System.out.println(products.size());
                        //
                        for (Product i : products) System.out.println(i.getBestPrice());
                        productRecyclerActivityAdapter = new ProductRecyclerActivityAdapter(products, ProductListActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
                        recyclerView.setAdapter(productRecyclerActivityAdapter);
                    }
                }else {
                    System.out.println("this is page: "+getIntent().getStringExtra("query"));
                    products = response.body();
                    for (Product i : products) System.out.println(i.getBestPrice());
                    productRecyclerActivityAdapter = new ProductRecyclerActivityAdapter(products, ProductListActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
                    recyclerView.setAdapter(productRecyclerActivityAdapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onUserClick(Product product) {
        Intent intent = new Intent(ProductListActivity.this,ProductPageActivity.class);
        intent.putExtra("product",product.getId());
        startActivity(intent);

    }
}