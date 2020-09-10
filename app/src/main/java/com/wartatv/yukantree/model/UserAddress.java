package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by .
 * www.wartatv.com
 */
public class UserAddress {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("password")
    private String password;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("gender")
    private String gender;
    @SerializedName("idKtp")
    private String idKtp;
    @SerializedName("blood")
    private String blood;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;


    public UserAddress(String name, String email, String phone, String address, String state, String city, String zip) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return blood;
    }

    public void setState(String state) {
        this.blood = blood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return gender;
    }

    public void setZip(String zip) {
        this.gender = gender;
    }
}
