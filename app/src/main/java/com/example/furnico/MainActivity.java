package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<ArrayList<Product>> responses = iPostApi.fetchTopProducts();
        List<String> imageUrl = new ArrayList<>();
        List<Integer> productIds = new ArrayList<>();
        Callback<ArrayList<Product>> userCallback = new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                Log.d("Login", "Reponse" + response.code());
                if(response.body() != null){
                for(Product p:response.body()){
                    //slideModels.add(new SlideModel((String) p.getImage()));
                    imageUrl.add(p.getImage());
                    productIds.add(p.getId());
                }
                }
                Glide.with(MainActivity.this).load(imageUrl.get(0)).
                        placeholder(R.drawable.placeholder).
                        into((ImageView)findViewById(R.id.iv_activity_main_featured1));
                Glide.with(MainActivity.this).load(imageUrl.get(1)).
                        placeholder(R.drawable.placeholder).
                        into((ImageView)findViewById(R.id.iv_activity_main_featured2));
                Glide.with(MainActivity.this).load(imageUrl.get(2)).
                        placeholder(R.drawable.placeholder).
                        into((ImageView)findViewById(R.id.iv_activity_main_featured3));
                Glide.with(MainActivity.this).load(imageUrl.get(3)).
                        placeholder(R.drawable.placeholder).
                        into((ImageView)findViewById(R.id.iv_activity_main_featured4));
                Glide.with(MainActivity.this).load(imageUrl.get(4)).
                        placeholder(R.drawable.placeholder).
                        into((ImageView)findViewById(R.id.iv_activity_main_featured5));
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        };
        responses.enqueue(userCallback);

        findViewById(R.id.iv_activity_main_featured1).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ProductPageActivity.class).putExtra("product",productIds.get(0))));
        findViewById(R.id.iv_activity_main_featured2).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ProductPageActivity.class).putExtra("product",productIds.get(1))));
        findViewById(R.id.iv_activity_main_featured3).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ProductPageActivity.class).putExtra("product",productIds.get(2))));
        findViewById(R.id.iv_activity_main_featured4).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ProductPageActivity.class).putExtra("product",productIds.get(3))));
        findViewById(R.id.iv_activity_main_featured5).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,ProductPageActivity.class).putExtra("product",productIds.get(4))));
        SearchView sv=findViewById(R.id.sv_activity_main_search);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent=new Intent(MainActivity.this,ProductListActivity.class);
                System.out.println("execute Query" + query);
                intent.putExtra("query",query);
                intent.putExtra("mode",2);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //Intent intent = new Intent()

       findViewById(R.id.ctv_activity_main_category11).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
               intent.putExtra("mode",1);
               intent.putExtra("category",1);
               startActivity(intent);
           }
       });
        findViewById(R.id.ctv_activity_main_category12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
                intent.putExtra("mode",1);
                intent.putExtra("category",2);
                startActivity(intent);
            }
        });
        findViewById(R.id.ctv_activity_main_category13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
                intent.putExtra("mode",1);
                intent.putExtra("category",3);
                startActivity(intent);
            }
        });
        findViewById(R.id.ctv_activity_main_category14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
                intent.putExtra("mode",1);
                intent.putExtra("category",4);
                startActivity(intent);
            }
        });
        findViewById(R.id.ctv_activity_main_category15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
                intent.putExtra("mode",1);
                intent.putExtra("category",5);
                startActivity(intent);
            }
        });

        findViewById(R.id.ibt_home_page_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartRecycleActivity.class));
            }
        });

        findViewById(R.id.bt_home_page_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AccountOptions.class));
            }
        });


    }
}