package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furnico.retrofit.model.Customer;
import com.example.furnico.retrofit.model.ResponseString;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Button btnRegister = findViewById(R.id.bt_activity_registeration_register);
        btnRegister.setOnClickListener(v -> {
            Pattern pattern = Pattern.compile("^\\d{10}$");
            String name = ((EditText) findViewById(R.id.et_activity_register_fullname)).getText().toString();
            String pass = ((EditText) findViewById(R.id.et_activity_registration_password)).getText().toString();
            String email = ((EditText) findViewById(R.id.et_activity_registration_email)).getText().toString();
            String address = ((EditText) findViewById(R.id.et_activity_registration_address)).getText().toString();
            String phone = ((EditText) findViewById(R.id.et_activity_registration_number)).getText().toString();
            if (name.length() == 0 || pass.length() == 0 || address.length() == 0 || phone.length() == 0 || email.length() == 0) {
                Toast.makeText(RegistrationActivity.this, "Length should be greater than 0", Toast.LENGTH_LONG).show();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(RegistrationActivity.this, "Invalid Email Format", Toast.LENGTH_LONG).show();
            }else if(!pattern.matcher(phone).matches()){
                Toast.makeText(RegistrationActivity.this, "Invalid Phone Format", Toast.LENGTH_LONG).show();

            }
            else {
                Log.d("EditText", "No Error");
                Customer customer = new Customer();
                customer.setEmail(email);customer.setPassword(pass);customer.setPhone(phone);customer.setAddress(address);customer.setName(name);
                Retrofit retrofit = RetrofitBuilderService.getInstance();
                IPostApi iPostApi = retrofit.create(IPostApi.class);
                Call<ResponseString> responses = iPostApi.registerPost(customer);
                Callback<ResponseString> userCallback = new Callback<ResponseString>() {
                    @Override
                    public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                        Log.d("Register", "Reponse" + response.body());
                        if (response.body().getValue().equals("failure"))
                            Toast.makeText(RegistrationActivity.this, "Account Already Exists", Toast.LENGTH_SHORT).show();
                        else {

                            int isCart = getIntent().getIntExtra("cartLogin", 0);
                            if (isCart == 1) {
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                intent.putExtra("cartLogin", 1);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseString> call, Throwable t) {
                        t.printStackTrace();
                    }

                };
                responses.enqueue(userCallback);
            }
        });
    }
}