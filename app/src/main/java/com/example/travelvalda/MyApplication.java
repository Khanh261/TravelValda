package com.example.travelvalda;

import android.app.Application;
import androidx.room.Room;
import com.example.travelvalda.database.AppDatabase;

public class MyApplication extends Application {
    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "homestay-database").build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
