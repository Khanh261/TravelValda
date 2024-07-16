package com.example.travelvalda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.travelvalda.dao.BookingDAO;
import com.example.travelvalda.dao.PropertyDAO;
import com.example.travelvalda.dao.ReviewDAO;
import com.example.travelvalda.dao.UserDAO;
import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.Property;
import com.example.travelvalda.models.User;
import com.example.travelvalda.models.Review;

@Database(entities = {User.class, Property.class, Booking.class, Review.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDao();
    public abstract PropertyDAO propertyDao();
    public abstract BookingDAO bookingDao();
    public abstract ReviewDAO reviewDao();

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Add 'image_url' column to 'properties' table if not exists
            database.execSQL("ALTER TABLE properties ADD COLUMN image_url TEXT");
        }
    };

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "TravelValdaDB")
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return instance;
    }
}
