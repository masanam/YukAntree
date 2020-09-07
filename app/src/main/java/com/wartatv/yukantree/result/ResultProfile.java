package com.wartatv.yukantree.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultProfile {
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Age")
    @Expose
    private String Age;
    @SerializedName("Phone")
    @Expose
    private String Phone;
    @SerializedName("Email")
    @Expose
    private String Email;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
