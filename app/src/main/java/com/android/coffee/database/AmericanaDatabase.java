package com.android.coffee.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = AmericanaEntity.class, version = 1)
public abstract class AmericanaDatabase extends RoomDatabase {

    private static final String DB_NAME = "coffee_db";
    private static AmericanaDatabase instance;

    public static synchronized AmericanaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AmericanaDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        }
        return instance;
    }

    public abstract AmericanoDAO americanoDAO();
}
