package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Topic{

    public int id;

    @SerializedName("name_topic")
    public String name;
    public String picture;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
