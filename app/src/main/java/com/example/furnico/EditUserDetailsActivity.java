package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furnico.retrofit.model.Customer;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditUserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);

        TextView name = findViewById(R.id.et_edit_user_details_name);
        TextView address = findViewById(R.id.et_edit_user_details_address);
        TextView contact = findViewById(R.id.et_edit_user_details_number);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        address.setText(intent.getStringExtra("address"));
        contact.setText(intent.getStringExtra("contact"));
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.furnico",MODE_PRIVATE);
        findViewById(R.id.bt_edit_details_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = RetrofitBuilderService.getInstance();
                IPostApi iPostApi = retrofit.create(IPostApi.class);

                EncryptCustomer encryptCustomer = new EncryptCustomer();
                encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
                Customer customer = new Customer();
                customer.setName(name.getText().toString());
                customer.setAddress(address.getText().toString());
                customer.setPhone(contact.getText().toString());
                encryptCustomer.setCustomer(customer);

                Call<String> responses = iPostApi.setUserDetails(encryptCustomer);
                Callback<String> callback  = new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                                    //Log.d("Chech respommse",)
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {//Toast.makeText(EditUserDetailsActivity.this,"Error Check your Internet",Toast.LENGTH_LONG).show();
                    }
                };
                responses.enqueue(callback);
                Toast.makeText(EditUserDetailsActivity.this,"succesfully updated",Toast.LENGTH_LONG).show();

            }
        });
    }
}