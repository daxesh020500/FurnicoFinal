package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.furnico.retrofit.model.Customer;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserDetails extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView address;
    TextView contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        EncryptCustomer encryptCustomer = new EncryptCustomer();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.furnico",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        Intent intent = new Intent(UserDetails.this,EditUserDetailsActivity.class);
        Call<Customer> responses = iPostApi.getUserDetails(encryptCustomer);
        Callback<Customer> callback = new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if(response == null){
                    Toast.makeText(UserDetails.this,"No Details ",Toast.LENGTH_LONG).show();
                }else{
                    Customer customer = response.body();
                    name = findViewById(R.id.tv_details_name);
                    name.setText(customer.getName());
                    email = findViewById(R.id.tv_details_email);
                    email.setText(customer.getEmail());
                    contact = findViewById(R.id.tv_details_contact);
                    contact.setText(customer.getPhone());
                    address = findViewById(R.id.tv_details_address);
                    address.setText(customer.getAddress());
                    intent.putExtra("name",customer.getName());
                    intent.putExtra("contact",customer.getPhone());
                    intent.putExtra("address",customer.getAddress());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
            }
        };
        responses.enqueue(callback);

        findViewById(R.id.btn_update_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(intent);
            }
        });



    }

}