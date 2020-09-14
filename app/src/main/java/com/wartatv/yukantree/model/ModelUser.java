package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelUser {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<User> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return data;
    }

    public void setResult(List<User> data) {
        this.data = data;
    }


}
