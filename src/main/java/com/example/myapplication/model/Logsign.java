package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Logsign {
    @SerializedName("user_name")
    public String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
