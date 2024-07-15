package com.example.travelvalda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.travelvalda.models.Booking;

import java.util.List;

@Dao
public interface BookingDAO {
    @Insert
    void insertBooking(Booking booking);

    @Query("SELECT * FROM bookings WHERE guestId = :guestId")
    List<Booking> findBookingsByGuestId(int guestId);
}
