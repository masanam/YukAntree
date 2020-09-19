package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class Host{

	@SerializedName("image")
	private String image;

	@SerializedName("address")
	private String address;

	@SerializedName("city")
	private String city;

	@SerializedName("photo")
	private String photo;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("title")
	private String title;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("contact")
	private String contact;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("email")
	private String email;

	@SerializedName("categoryId")
	private int categoryId;

	@SerializedName("status")
	private int status;

	public String getImage(){
		return image;
	}

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public String getPhoto(){
		return photo;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getTitle(){
		return title;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPhone(){
		return phone;
	}

	public String getContact(){
		return contact;
	}

	public int getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public String getEmail(){
		return email;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public int getStatus(){
		return status;
	}
}