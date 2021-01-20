package com.example.furnico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnico.recycler.MerchantRecyclerViewAdapter;
import com.example.furnico.retrofit.model.BuyOptions;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.EncryptTransaction;
import com.example.furnico.retrofit.model.Inventory;
import com.example.furnico.retrofit.model.Transaction;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MerchantSelectRecycle extends AppCompatActivity implements MerchantRecyclerViewAdapter.UserDataInterface {
    RecyclerView recyclerView;
    ArrayList<BuyOptions> buyOptionsArrayList;
    ArrayList<Inventory> inventories = new ArrayList<Inventory>();
    MerchantRecyclerViewAdapter merchantRecyclerViewAdapter;
    int loadCursor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_select_recycle);
        recyclerView = findViewById(R.id.rv_merchant_select);


        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.furnicon", Context.MODE_PRIVATE);
        //String token = sharedPreferences.getString("userid","default");

        EncryptCustomer encryptCustomer = new EncryptCustomer();
        encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");

        Call<ArrayList<BuyOptions>> responses = iPostApi.getBuyOptions(encryptCustomer);
        Callback<ArrayList<BuyOptions>> userCallback = new Callback<ArrayList<BuyOptions>>() {
            @Override
            public void onResponse(Call<ArrayList<BuyOptions>> call, Response<ArrayList<BuyOptions>> response) {
                buyOptionsArrayList = response.body();
                if(buyOptionsArrayList.size()>0) loadMerchants();
            }
            @Override
            public void onFailure(Call<ArrayList<BuyOptions>> call, Throwable t){ }
        };
        responses.enqueue(userCallback);
    }

    void loadMerchants(){
        if(loadCursor < buyOptionsArrayList.size()){
            merchantRecyclerViewAdapter = new MerchantRecyclerViewAdapter(buyOptionsArrayList.get(loadCursor).getInventories(), MerchantSelectRecycle.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(MerchantSelectRecycle.this));
            recyclerView.setAdapter(merchantRecyclerViewAdapter);
        }else{
            Intent intent = new Intent(MerchantSelectRecycle.this, TransactionActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onUserClick(Inventory inventory) {
        int stock = inventory.getStock();
        int price  = inventory.getPrice();
        int merchantId = inventory.getMerchantId();
        int inventoryId = inventory.getId();
        Intent intent = new Intent(MerchantSelectRecycle.this,CartQuantitySelectActivity.class);
        intent.putExtra("stock",stock);
        intent.putExtra("price",price);
        intent.putExtra("merchantId",merchantId);
        intent.putExtra("inventoryId",inventoryId);

//        Transaction t = new Transaction(1, buyOptionsArrayList.get(loadCursor).getQuantity(),0, inventory.getId(),buyOptionsArrayList.get(loadCursor).getProductId(),inventory.getPrice());
//        EncryptTransaction encryptTransaction = new EncryptTransaction();
//        encryptTransaction.setToken("7SRqaqfqR+2Zi3VW8TudwA==");
//        encryptTransaction.setTransaction(t);
//
//        Retrofit retrofit = RetrofitBuilderService.getInstance();
//        IPostApi iPostApi = retrofit.create(IPostApi.class);
//        Call<String> responses = iPostApi.addTransaction(encryptTransaction);
//        Callback<String> userCallback = new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) { }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) { }
//        };
//        responses.enqueue(userCallback);
//        loadCursor++;
//        loadMerchants();

    }
}