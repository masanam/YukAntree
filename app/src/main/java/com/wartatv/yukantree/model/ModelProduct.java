package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by .
 * www.wartatv.com
 */
public class ModelProduct {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Product> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getResult() {
        return data;
    }

    public void setResult(List<Product> data) {
        this.data = data;
    }
}
