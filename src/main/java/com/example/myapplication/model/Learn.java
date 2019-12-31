package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Learn {
    @SerializedName("id_word")
    public  int idword;
    @SerializedName("word1")
    public String word;

    public String picture;

    public String vietnamese;

    public int getIdword() {
        return idword;
    }

    public void setIdword(int idword) {
        this.idword = idword;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }
}
