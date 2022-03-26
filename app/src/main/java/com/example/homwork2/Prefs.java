package com.example.homwork2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.homwork2.ui.ProfileFragment.ProfileFragment;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
        preferences.edit().putBoolean("isBoardShown", true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("isBoardShown", false);
    }

    public void saveEditText(String name){
        preferences.edit().putString("text", name).apply();
    }
    public String isEditText(){
        return preferences.getString("text", "");
    }

    public void saveImageViev(String image){
        preferences.edit().putString("image", image).apply();
    }

    public String isImageView(){
        return preferences.getString("image", "");
    }

    public void clearPreferences(Context context){
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }



}
