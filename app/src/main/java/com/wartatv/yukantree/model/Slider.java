package com.wartatv.yukantree.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by .
 * www.wartatv.com
 */
public class Slider {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    public Slider() {
    }

    public Slider(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
