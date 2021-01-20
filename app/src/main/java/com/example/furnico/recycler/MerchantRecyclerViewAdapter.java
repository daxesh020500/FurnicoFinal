package com.example.furnico.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnico.R;
import com.example.furnico.retrofit.model.Inventory;
import com.example.furnico.retrofit.model.Merchant;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MerchantRecyclerViewAdapter extends RecyclerView.Adapter<MerchantRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Inventory> mUserDataList;
    private final UserDataInterface mUserDataInterface;

    public MerchantRecyclerViewAdapter(ArrayList<Inventory> userDataList, UserDataInterface mUserDataInterface) {
        this.mUserDataList = userDataList;
        this.mUserDataInterface = mUserDataInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchant_layout, parent, false);
        return new ViewHolder(view,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inventory inventory = mUserDataList.get(position);
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<Merchant> responses = iPostApi.fetchMerchant(inventory.getMerchantId());
        Callback<Merchant> userCallback = new Callback<Merchant>() {
            @Override
            public void onResponse(Call<Merchant> call, Response<Merchant> response) {
                Merchant merchant = response.body();
                holder.tvmerchantName.setText(merchant.getName());
                holder.tvmerchantPrice.setText(inventory.getPrice()+"");
                holder.tvmerchantRating.setText(((double)(merchant.getRating()))+"");
            }
            @Override
            public void onFailure(Call<Merchant> call, Throwable t) {
            }
        };
        responses.enqueue(userCallback);
        holder.btn_buymerchant.setOnClickListener((view -> mUserDataInterface.onUserClick(inventory)));
    }

    public interface UserDataInterface {
        void onUserClick(Inventory inventory);
    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvmerchantName;
        private final TextView tvmerchantRating;
        private final TextView tvmerchantPrice;
        private final Button btn_buymerchant;
        private final View rootView;
        private final ViewGroup parent;

        public ViewHolder(View view,ViewGroup parent) {
            super(view);
            rootView = view;
            tvmerchantName = view.findViewById(R.id.tv_merchant_name);
            tvmerchantRating = view.findViewById(R.id.tv_merchant_rating);
            tvmerchantPrice = view.findViewById(R.id.tv_merchant_price);
            btn_buymerchant = view.findViewById(R.id.tv_merchant_button);
            this.parent = parent;
        }
    }
}


