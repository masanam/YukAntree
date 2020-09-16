package com.wartatv.yukantree.model;

public class DataTrx{
	private String number;
	private String updatedAt;
	private String createdAt;
	private String tanggal;
	private int id;
	private int loketId;
	private int type;
	private int userId;
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
