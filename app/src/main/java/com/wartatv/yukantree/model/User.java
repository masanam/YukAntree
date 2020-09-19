package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("address")
	private String address;

	@SerializedName("gender")
	private String gender;

	@SerializedName("city")
	private String city;

	@SerializedName("roles")
	private int roles;

	@SerializedName("photo")
	private String photo;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private String emailVerifiedAt;

	@SerializedName("dateOfBirth")
	private String dateOfBirth;

	@SerializedName("blood")
	private String blood;

	@SerializedName("login_ip")
	private String loginIp;

	@SerializedName("deleted_at")
	private String deletedAt;

	@SerializedName("password")
	private String password;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("login_at")
	private String loginAt;

	@SerializedName("idKtp")
	private String idKtp;

	@SerializedName("id")
	private int id;

	@SerializedName("remember_token")
	private String rememberToken;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	public String getAddress(){
		return address;
	}

	public String getGender(){
		return gender;
	}

	public String getCity(){
		return city;
	}

	public int getRoles(){
		return roles;
	}

	public String getPhoto(){
		return photo;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public String getDateOfBirth(){
		return dateOfBirth;
	}

	public String getBlood(){
		return blood;
	}

	public String getLoginIp(){
		return loginIp;
	}

	public String getDeletedAt(){
		return deletedAt;
	}

	public String getPassword(){
		return password;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public String getLoginAt(){
		return loginAt;
	}

	public String getIdKtp(){
		return idKtp;
	}

	public int getId(){
		return id;
	}

	public String getRememberToken(){
		return rememberToken;
	}

	public String getEmail(){
		return email;
	}

	public String getStatus(){
		return status;
	}
}