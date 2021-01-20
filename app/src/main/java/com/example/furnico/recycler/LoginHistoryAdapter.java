package com.example.furnico.recycler;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnico.R;
import com.example.furnico.retrofit.model.Cart;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.LoginHistory;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.network.IPostApi;
import com.example.furnico.retrofit.networkmanager.RetrofitBuilderService;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginHistoryAdapter extends RecyclerView.Adapter<LoginHistoryAdapter.ViewHolder> {
    private final ArrayList<LoginHistory> mUserDataList;

    public LoginHistoryAdapter(ArrayList<LoginHistory> userDataList) {
        this.mUserDataList = userDataList;
    }

    @NonNull
    @Override
    public LoginHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_login_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginHistoryAdapter.ViewHolder holder, int position) {
        LoginHistory loginHistory = mUserDataList.get(position);
        holder.tvLoginTime.setText(loginHistory.getLoginDate().toString());
        holder.tvStatus.setText(String.valueOf(loginHistory.isLoginSuccessful()));
    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvLoginTime;
        private final TextView tvStatus;
        private final View rootView;

        public ViewHolder(View view) {
            super(view);
            tvLoginTime = view.findViewById(R.id.tv_user_login_history_login_time);
            tvStatus = view.findViewById(R.id.tv_user_login_history_status);
            rootView = view;
        }
    }
}

