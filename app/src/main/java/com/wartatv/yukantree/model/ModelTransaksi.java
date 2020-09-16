package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelTransaksi {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Transaksi> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transaksi> getResult() {
        return data;
    }

    public void setResult(List<Transaksi> data) {
        this.data = data;
    }


}
