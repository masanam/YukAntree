package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class Loket{

	@SerializedName("image")
	private String image;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private String discount;

	@SerializedName("hostId")
	private int hostId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("title")
	private String title;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("schedule")
	private String schedule;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private int price;

	@SerializedName("quota")
	private int quota;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("host")
	private Host host;

	@SerializedName("currency")
	private String currency;

	@SerializedName("id")
	private String id;

	@SerializedName("attribute")
	private String attribute;

	@SerializedName("status")
	private int status;

	public Loket(String id, String title, String image) {
	}

	public String getImage(){
		return image;
	}

	public String getDescription(){
		return description;
	}

	public String getDiscount(){
		return discount;
	}

	public int getHostId(){
		return hostId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getTitle(){
		return title;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getSchedule(){
		return schedule;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getPrice(){
		return price;
	}

	public int getQuota(){
		return quota;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public Host getHost(){
		return host;
	}

	public String getCurrency(){
		return currency;
	}

	public String getId(){
		return id;
	}

	public String getAttribute(){
		return attribute;
	}

	public int getStatus(){
		return status;
	}
}