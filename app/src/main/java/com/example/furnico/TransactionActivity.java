package com.example.furnico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnico.recycler.TransactionRecyclerViewAdapter;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.ResponseString;
import com.example.furnico.retrofit.model.Transaction;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TransactionActivity extends AppCompatActivity {
    ArrayList<Transaction> transactions;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        recyclerView = findViewById(R.id.transaction_recycleview);
        progressBar = findViewById(R.id.pb_loading);
        progressBar.bringToFront();

        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);

        EncryptCustomer encryptCustomer = new EncryptCustomer();
        encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        //encryptCustomer.setToken(getSharedPreferences("com.example.furnico", Context.MODE_PRIVATE).getString("userid",""));

        Call<ArrayList<Transaction>> responses = iPostApi.getTransactions(encryptCustomer);
        Callback<ArrayList<Transaction>> userCallback = new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                transactions = response.body();
                Log.d("Transaction Size",transactions.size()+"");
                int total = 0;
                for(Transaction transaction:transactions) total+= transaction.getPrice()*transaction.getQuantity();
                TransactionRecyclerViewAdapter transactionRecyclerViewAdapter = new TransactionRecyclerViewAdapter(transactions);
                recyclerView.setLayoutManager(new LinearLayoutManager(TransactionActivity.this));
                recyclerView.setAdapter(transactionRecyclerViewAdapter);
                ((TextView)findViewById(R.id.tv_transaction_totalamount)).setText(total+"");
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {
            }
        };
        responses.enqueue(userCallback);

        findViewById(R.id.btn_transaction_pay).setOnClickListener(v -> {
            buy();
        });

    }

    void buy() {
        progressBar.bringToFront();
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        EncryptCustomer encryptCustomer = new EncryptCustomer();
        //encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
        encryptCustomer.setToken(getSharedPreferences("com.example.furnico", Context.MODE_PRIVATE).getString("userid",""));

        Call<ResponseString> responses = iPostApi.buy(encryptCustomer);
        Callback<ResponseString> userCallback = new Callback<ResponseString>() {
            @Override
            public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(TransactionActivity.this, "Successfully paid", Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(TransactionActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }

            @Override
            public void onFailure(Call<ResponseString> call, Throwable t) {
                Toast.makeText(TransactionActivity.this, "Error", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        };
        responses.enqueue(userCallback);
    }
}