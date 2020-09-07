package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by .
 * www.wartatv.com
 */
public class ModelCategory {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Category> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Category> getResult() {
        return data;
    }

    public void setResult(List<Category> data) {
        this.data = data;
    }
}
