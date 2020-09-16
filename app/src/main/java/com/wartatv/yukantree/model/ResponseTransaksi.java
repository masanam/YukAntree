package com.wartatv.yukantree.model;

public class ResponseTransaksi{
	private DataTrx data;
	private boolean success;
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
