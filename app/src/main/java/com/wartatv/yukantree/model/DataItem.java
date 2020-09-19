package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("address")
	private String address;

	@SerializedName("city")
	private String city;

	@SerializedName("description")
	private Object description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("loketId")
	private int loketId;

	@SerializedName("type")
	private int type;

	@SerializedName("userId")
	private int userId;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("number")
	private String number;

	@SerializedName("hostname")
	private String hostname;

	@SerializedName("loket")
	private Loket loket;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("categoryname")
	private String categoryname;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private Status status;

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public Object getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
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

	public Object getDeletedAt(){
		return deletedAt;
	}

	public String getNumber(){
		return number;
	}

	public String getHostname(){
		return hostname;
	}

	public Loket getLoket(){
		return loket;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPhone(){
		return phone;
	}

	public String getCategoryname(){
		return categoryname;
	}

	public int getId(){
		return id;
	}

	public String getTanggal(){
		return tanggal;
	}

	public User getUser(){
		return user;
	}

	public Status getStatus(){
		return status;
	}
}