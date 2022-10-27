package com.example.myapplication;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@androidx.room.Database(entities = {Model.class}, version =1)
public  abstract class Database extends RoomDatabase {
    private static final String db_name = "College_details";
    public static Database instance;

    public static synchronized  Database getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, db_name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract Dao dao();

}