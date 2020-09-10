package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by .
 * www.wartatv.com
 */
public class ModelSlider {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Slider> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Slider> getResult() {
        return data;
    }

    public void setResult(List<Slider> data) {
        this.data = data;
    }
}
