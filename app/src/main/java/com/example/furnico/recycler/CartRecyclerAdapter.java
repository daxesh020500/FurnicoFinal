package com.example.furnico.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furnico.ProductPageActivity;
import com.example.furnico.R;
import com.example.furnico.retrofit.model.Cart;
import com.example.furnico.retrofit.model.CartWithProduct;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {
    private final ArrayList<Cart> mUserDataList;
    private final UserDataInterface mUserDataInterface;

    public CartRecyclerAdapter(ArrayList<Cart> userDataList, UserDataInterface mUserDataInterface) {
        this.mUserDataList = userDataList;
        this.mUserDataInterface = mUserDataInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_cart_item, parent, false);
        return new ViewHolder(view,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cart cart = mUserDataList.get(position);
        holder.tvQuantity.setText(cart.getQuantity()+"");
        Retrofit retrofit = RetrofitBuilderService.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<Product> responses = iPostApi.getProductById(cart.getProductId());
        Callback<Product> userCallback = new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                System.out.println(product.getId());
                Glide.with(holder.parent.getContext()).load(product.getImage()).placeholder(R.drawable.ic_google).into(holder.ivImage);
                holder.tvProductName.setText(product.getName());
                holder.tvCategory.setText(product.getCategory()+"");
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            }
        };
        responses.enqueue(userCallback);
        holder.btn_delete.setOnClickListener((view -> mUserDataInterface.onDeleteClick(cart,position)));
        holder.btn_increase.setOnClickListener((view -> mUserDataInterface.onIncreaseClick(cart)));
        holder.btn_decrease.setOnClickListener((view -> mUserDataInterface.onDecreaseClick(cart)));
    }

    public interface UserDataInterface {
        void onIncreaseClick(Cart userData);
        void onDecreaseClick(Cart userData);
        void onDeleteClick(Cart userData,int position);
    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvProductName;
        private final TextView tvCategory;
        private final TextView tvPrice;
        private final TextView tvQuantity;
        private final Button btn_decrease;
        private final Button btn_increase;
        private final Button btn_delete;
        private final View rootView;
        private final ViewGroup parent;

        public ViewHolder(View view,ViewGroup parent) {
            super(view);
            rootView = view;
            ivImage = view.findViewById(R.id.iv_user_cart_item_product_image);
            tvProductName = view.findViewById(R.id.tv_user_cart_item_product_name);
            tvCategory = view.findViewById(R.id.tv_user_cart_item_category);
            tvQuantity = view.findViewById(R.id.tv_user_cart_item_no_of_products);
            tvPrice = view.findViewById(R.id.tv_user_cart_item_price);
            btn_increase = view.findViewById(R.id.bt_user_cart_item_add);
            btn_decrease = view.findViewById(R.id.bt_user_cart_item_decrease);
            btn_delete = view.findViewById(R.id.btn_user_cartitem_removeitem);
            this.parent = parent;
        }
    }
}


