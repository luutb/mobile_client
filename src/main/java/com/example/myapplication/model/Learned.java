package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Learned implements RecyclerBaseItem{
    @SerializedName("id_word")
    public int idword;

    public String picture;
    public String vietnamese;

    @SerializedName("word1")
    public String word;

    public int getIdword() {
        return idword;
    }

    public void setIdword(int idword) {
        this.idword = idword;
    }

    public String getWord() {
        return word;
    }

    public void setWord1(String word) {
        this.word = word;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
