package com.example.furnico.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Cart{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	@SerializedName("customerId")
	private int customerId;

	@SerializedName("id")
	private int id;

	public Cart() { }

	public Cart(int quantity, int productId, int customerId, int id) {
		this.quantity = quantity;
		this.productId = productId;
		this.customerId = customerId;
		this.id = id;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}