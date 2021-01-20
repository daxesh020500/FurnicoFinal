package com.example.furnico.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Inventory{

	@SerializedName("rated")
	private int rated;

	@SerializedName("sold")
	private int sold;

	@SerializedName("productId")
	private int productId;

	@SerializedName("merchantId")
	private int merchantId;

	@SerializedName("price")
	private int price;

	@SerializedName("rating")
	private int rating;

	@SerializedName("id")
	private int id;

	@SerializedName("stock")
	private int stock;

	public void setRated(int rated){
		this.rated = rated;
	}

	public int getRated(){
		return rated;
	}

	public void setSold(int sold){
		this.sold = sold;
	}

	public int getSold(){
		return sold;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setMerchantId(int merchantId){
		this.merchantId = merchantId;
	}

	public int getMerchantId(){
		return merchantId;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setRating(int rating){
		this.rating = rating;
	}

	public int getRating(){
		return rating;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}
}