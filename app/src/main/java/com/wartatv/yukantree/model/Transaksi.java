package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by .
 * www.wartatv.com
 */
public class Transaksi {
    @SerializedName("id")
    private String id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("loketId")
    private String loketId;
    @SerializedName("number")
    private String number;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("type")
    private String type;
    @SerializedName("status")
    private String status;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoketId() {
        return loketId;
    }
    public void setLoketId(String loketId) {
        this.loketId = loketId;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getType() {
        return type;
    }
    public void setType(String status) { this.type = type; }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) { this.status = status; }
}
