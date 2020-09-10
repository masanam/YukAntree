package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by .
 * www.wartatv.com
 */
public class Product {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("description")
    private String description;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("contact")
    private String contact;

    @SerializedName("phone")
    private String phone;

    @SerializedName("discount")
    private String discount;

    @SerializedName("currency")
    private String currency;

    public Product() {
    }

    public Product(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Product(String id, String categoryId, String title, String description, String address, String phone, String discount, String image) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.discount = discount;
        this.image = image;
    }

    public Product(String id, String categoryId, String title, String description, String address, String currency, String city, String contact,String phone, String discount, String image) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.address = address;
        this.currency = currency;
        this.phone = phone;
        this.discount = discount;
        this.city = city;
        this.contact = contact;
        this.image = image;
   }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
