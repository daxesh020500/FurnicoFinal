package com.example.furnico.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furnico.ProductListActivity;
import com.example.furnico.R;
import com.example.furnico.retrofit.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerActivityAdapter extends RecyclerView.Adapter<ProductRecyclerActivityAdapter.ViewHolder> {

    private final ArrayList<Product> mUserDataList;
    private final ProductRecyclerInterface productRecyclerInterface;

    public ProductRecyclerActivityAdapter(ArrayList<Product> mUserDataList, ProductRecyclerInterface productRecyclerInterface) {
        this.mUserDataList = mUserDataList;
        this.productRecyclerInterface = productRecyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display, parent, false);
        return new ViewHolder(view,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product userData = mUserDataList.get(position);
        Glide.with(holder.parent.getContext()).load(userData.getImage()).placeholder(R.drawable.cell).into(holder.ivImage);
        holder.tvCategory.setText(userData.getCategory()+"");
        holder.tvProductName.setText(userData.getName());
        holder.tvPrice.setText(userData.getBestPrice()+"");
        holder.rootView.setOnClickListener((view -> productRecyclerInterface.onUserClick(userData)));
    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public interface ProductRecyclerInterface{
        void onUserClick(Product Product);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvProductName;
        private final TextView tvCategory;
        private final TextView tvPrice;
        private final View rootView;
        private final ViewGroup parent;

        public ViewHolder(View view,ViewGroup parent) {
            super(view);
            rootView = view;
            ivImage = view.findViewById(R.id.im_productImage_item);
            tvProductName = view.findViewById(R.id.tv_productName_item);
            tvCategory = view.findViewById(R.id.tv_productCategory_item);
            tvPrice = view.findViewById(R.id.tv_productPrice_item);
            this.parent = parent;
        }
    }
}
