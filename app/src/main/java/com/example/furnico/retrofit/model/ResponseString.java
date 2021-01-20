package com.example.furnico.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class ResponseString{

	@SerializedName("value")
	private String value;

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
}