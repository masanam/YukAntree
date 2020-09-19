package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class DataTrx{

	@SerializedName("number")
	private String number;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("id")
	private int id;

	@SerializedName("loketId")
	private int loketId;

	@SerializedName("type")
	private int type;

	@SerializedName("userId")
	private int userId;

	@SerializedName("status")
	private int status;

	public String getNumber(){
		return number;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getTanggal(){
		return tanggal;
	}

	public int getId(){
		return id;
	}

	public int getLoketId(){
		return loketId;
	}

	public int getType(){
		return type;
	}

	public int getUserId(){
		return userId;
	}

	public int getStatus(){
		return status;
	}
}