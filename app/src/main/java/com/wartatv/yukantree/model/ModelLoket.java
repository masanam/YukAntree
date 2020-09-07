package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by .
 * www.wartatv.com
 */
public class ModelLoket {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Loket> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Loket> getResult() {
        return data;
    }

    public void setResult(List<Loket> data) {
        this.data = data;
    }
}
