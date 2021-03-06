package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class ResponseTransaksi{

	@SerializedName("data")
	private DataTrx data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public DataTrx getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}