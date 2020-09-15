package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by .
 * www.wartatv.com
 */
public class Loket {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("hostId")
    private String hostId;

    @SerializedName("description")
    private String description;

    @SerializedName("attribute")
    private String attribute;

    @SerializedName("price")
    private String price;

    @SerializedName("discount")
    private String discount;

    @SerializedName("currency")
    private String currency;

    @SerializedName("schedule")
    private String schedule;

    @SerializedName("quota")
    private String quota;

    public Loket() {
    }

    public Loket(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Loket(String id, String hostId, String title, String description, String attribute, String price, String discount, String image) {
        this.id = id;
        this.hostId = hostId;
        this.title = title;
        this.description = description;
        this.attribute = attribute;
        this.price = price;
        this.discount = discount;
        this.image = image;
    }

    public Loket(String id, String hostId, String title, String description, String attribute, String currency, String price, String discount, String image) {
        this.id = id;
        this.hostId = hostId;
        this.title = title;
        this.description = description;
        this.attribute = attribute;
        this.currency = currency;
        this.price = price;
        this.discount = discount;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethostId() {
        return hostId;
    }

    public void sethostId(String hostId) {
        this.hostId = hostId;
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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {  this.schedule = schedule;  }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {  this.schedule = quota;  }

}
