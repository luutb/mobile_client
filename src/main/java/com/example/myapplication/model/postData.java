package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class postData {
    @SerializedName("id_word")
    int idword;

    @SerializedName("id_user")
    int iduser;

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
}
