package com.example.furnico.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class EncryptCart{

	@SerializedName("token")
	private String token;

	@SerializedName("cart")
	private Cart cart;

	public EncryptCart() {
	}

	public EncryptCart(String token, Cart cart) {
		this.token = token;
		this.cart = cart;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public Cart getCart(){
		return cart;
	}
}