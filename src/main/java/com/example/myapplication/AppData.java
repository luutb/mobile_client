package com.example.myapplication;

import com.example.myapplication.model.User;

public class AppData {
    public static User currentUser = null;

    public static boolean isLogin(){
        return currentUser != null;
    }



}
