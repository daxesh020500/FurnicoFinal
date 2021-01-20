package com.example.furnico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class InitiationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiation);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.furnico",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Logged",0);
        editor.apply();editor.commit();
        findViewById(R.id.guestlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InitiationActivity.this,MainActivity.class));
            }
        });
        findViewById(R.id.userlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("Logged",1);
                editor.apply();editor.commit();
                startActivity(new Intent(InitiationActivity.this,LoginActivity.class));
            }
        });
    }
}