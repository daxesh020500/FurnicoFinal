package com.example.furnico.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.furnico.MyOrderActivity;
import com.example.furnico.R;
import com.example.furnico.retrofit.model.Orders;
import com.example.furnico.retrofit.model.Product;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMyOrderAdapter extends RecyclerView.Adapter<RecyclerViewMyOrderAdapter.ViewHolder> {
    private final ArrayList<Orders> mUserDataList;
    private final MyOrderActivity myOrderActivity;

    public RecyclerViewMyOrderAdapter(ArrayList<Orders> mUserDataList, MyOrderActivity myOrderActivity) {
        this.mUserDataList = mUserDataList;
        this.myOrderActivity= myOrderActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_placed_orders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Orders userData = mUserDataList.get(position);
        holder.tvProductName.setText(userData.getProductId() + " ");
        holder.tvCategory.setText(userData.getQuantity() + " ");
        //Glide.with(holder.ivImage.getContext()).load(userData.getImage()).into(holder.ivImage);
        //holder.rootView.setOnClickListener((view ->myOrderActivity.onUserClick(userData)));
    }


    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }


    public interface UserDataInterface {
        void onUserClick(Product productInfo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProductName;
        private final TextView tvCategory;
        private final View rootView;
        private final ImageView ivImage;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            tvProductName = view.findViewById(R.id.tv_user_placed_orders_productName);
            tvCategory= view.findViewById(R.id.tv_user_placed_ordersuser_cartItemCategory);
            ivImage = view.findViewById(R.id.iv_user_placed_orders);
        }
    }
}

