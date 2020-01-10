package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Favourites {
    public int id;
    @SerializedName("id_word")
    public int idword;

    @SerializedName("id_user")
    public int iduser;

    public int getIdword() {
        return idword;
    }

    public void setIdword(int idword) {
        this.idword = idword;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
