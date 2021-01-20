package com.example.furnico.retrofit.network;
import com.example.furnico.retrofit.model.BuyOptions;
import com.example.furnico.retrofit.model.Cart;
import com.example.furnico.retrofit.model.Customer;
import com.example.furnico.retrofit.model.EncryptCart;
import com.example.furnico.retrofit.model.EncryptCustomer;
import com.example.furnico.retrofit.model.EncryptTransaction;
import com.example.furnico.retrofit.model.LoginHistory;
import com.example.furnico.retrofit.model.Merchant;
import com.example.furnico.retrofit.model.Orders;
import com.example.furnico.retrofit.model.PageVars;
import com.example.furnico.retrofit.model.Product;
import com.example.furnico.retrofit.model.ResponseString;
import com.example.furnico.retrofit.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPostApi {
    @Headers("Content-Type: application/json")
    @POST("user/loginCustomer")
    Call<ResponseString> loginPost(@Body Customer customer);

    @Headers("Content-Type: application/json")
    @POST("user/registerCustomer")
    Call<ResponseString> registerPost(@Body Customer customer);

    @Headers("Content-Type: application/json")
    @GET("prin/findTopProducts")
    Call<ArrayList<Product>> fetchTopProducts();

    @Headers("Content-Type: application/json")
    @GET("prin/fetchAll")
    Call<ArrayList<Product>> fetchAll();

    @Headers("Content-Type: application/json")
    @POST("prin/fetchAllPaged")
    Call<ArrayList<Product>> fetchAllPaged(@Body PageVars pageVars);

    @Headers("Content-Type: application/json")
    @POST("prin/findProductByCategoryPaged/")
    Call<ArrayList<Product>> fetchAllByCategory(@Body PageVars pageVars);

    @GET("prin/findProductById/{prodid}")
    Call<Product> getProductById(@Path("prodid") int prodid);

    @Headers("Content-Type: application/json")
    @POST("caor/cartQuantityEdit")
    Call<String> changeQuantityInCart(@Body EncryptCart encryptCart);

    @Headers("Content-Type: application/json")
    @POST("caor/fetchCart")
    Call<ArrayList<Cart>> fetchCart(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @POST("user/getCustomerToEdit")
    Call<Customer> getUserDetails(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @POST("user/updateDetails")
    Call<String> setUserDetails(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @GET("user/getMerchant/{id}")
    Call<Merchant> fetchMerchant(@Path("id") int id);

    @Headers("Content-Type: application/json")
    @POST("caor/getBuyOptions")
    Call<ArrayList<BuyOptions>> getBuyOptions(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @POST("caor/cart")
    Call<String> addToCart(@Body EncryptCart encryptCart);

    @Headers("Content-Type: application/json")
    @POST("caor/addTransaction")
    Call<String> addTransaction(@Body EncryptTransaction encryptTransaction);

    @Headers("Content-Type: application/json")
    @POST("caor/fetchTransactions")
    Call<ArrayList<Transaction>> getTransactions(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @POST("caor/buy")
    Call<ResponseString> buy(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @GET("user/search/{text}")
    Call<ArrayList<Product>> searchQuery(@Path("text") String text);

    @Headers("Content-Type: application/json")
    @POST("user/loginHistory")
    Call<ArrayList<LoginHistory>> loginHistory(@Body EncryptCustomer encryptCustomer);

    @Headers("Content-Type: application/json")
    @POST("caor/getOrders")
    Call<ArrayList<Orders>> getOrders(@Body EncryptCustomer encryptCustomer);
}
