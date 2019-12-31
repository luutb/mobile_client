package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    public int id;

    @SerializedName("user_name")
    public String username;

    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
