package com.wartatv.yukantree.model;

public class ResponseLogin{
	private String accessToken;
	private String expiresAt;
	private String name;
	private String tokenType;
	private int userid;
	private String email;

	public String getAccessToken(){
		return accessToken;
	}

	public String getExpiresAt(){
		return expiresAt;
	}

	public String getName(){
		return name;
	}

	public String getTokenType(){
		return tokenType;
	}

	public int getUserid(){
		return userid;
	}

	public String getEmail(){
		return email;
	}
}
