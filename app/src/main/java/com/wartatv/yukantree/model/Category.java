package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("image")
	private String image;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("deleted_at")
	private String deletedAt;

	public Category(String id, String title, String image) {
	}

	public String getImage(){
		return image;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getDeletedAt(){
		return deletedAt;
	}
}