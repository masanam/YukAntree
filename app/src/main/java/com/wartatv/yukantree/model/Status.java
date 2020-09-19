package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class Status{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("deleted_at")
	private String deletedAt;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public String getDescription(){
		return description;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
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
}