package com.example.homwork2;

import android.app.Application;

import androidx.room.Room;

import com.example.homwork2.room.AppDataBase;

public class App extends Application {

    private static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(this, AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
    }


    public static AppDataBase getDataBase() {
        return dataBase;
    }
}
