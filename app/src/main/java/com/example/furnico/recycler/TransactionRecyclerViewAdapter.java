package com.example.furnico.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnico.R;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.model.Transaction;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Transaction> mUserDataList;

    public TransactionRecyclerViewAdapter(ArrayList<Transaction> mUserDataList) {
        this.mUserDataList = mUserDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction userData = mUserDataList.get(position);

        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        EncryptCustomer encryptCustomer = new EncryptCustomer();

        encryptCustomer.setToken("7SRqaqfqR+2Zi3VW8TudwA==");

        Call<Product> responses = iPostApi.getProductById(userData.getProductId());
        Callback<Product> userCallback = new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                holder.tv_merchantName.setText(response.body().getName()+"");
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) { }
        };
        responses.enqueue(userCallback);





        holder.tv_productName.setText(userData.getQuantity()+"");
        System.out.println(userData.getPrice());
        holder.tv_price.setText(userData.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tv_productName;
        private final TextView tv_merchantName;
        private final TextView tv_price;

        public ViewHolder(View view) {
            super(view);
            tv_merchantName = view.findViewById(R.id.tv_transaction_merchant_name);
            tv_productName = view.findViewById(R.id.tv_transaction_product_name);
            tv_price = view.findViewById(R.id.tv_transaction_price);
            rootView = view;
        }
    }
}

