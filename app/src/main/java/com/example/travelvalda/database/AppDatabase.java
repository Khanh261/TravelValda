package com.example.travelvalda.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.travelvalda.dao.UserDao;
import com.example.travelvalda.dao.PropertyDao;
import com.example.travelvalda.dao.BookingDao;
import com.example.travelvalda.dao.ReviewDao;
import com.example.travelvalda.entity.User;
import com.example.travelvalda.entity.Property;
import com.example.travelvalda.entity.Booking;
import com.example.travelvalda.entity.Review;

@Database(entities = {User.class, Property.class, Booking.class, Review.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PropertyDao propertyDao();
    public abstract BookingDao bookingDao();
    public abstract ReviewDao reviewDao();
}
