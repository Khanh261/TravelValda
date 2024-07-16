package com.example.travelvalda.adapters;

import android.util.Log;

import com.example.travelvalda.models.Booking;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeDatabaseBookingAdapter {
    private DatabaseReference databaseReference;

    public RealtimeDatabaseBookingAdapter() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Booking");
    }

    public void createBooking(Booking booking, Callback callback) {
        String bookingId = databaseReference.push().getKey();
        if (bookingId != null) {
            databaseReference.child(bookingId).setValue(booking)
                    .addOnSuccessListener(aVoid -> {
                        Log.d("RealtimeDatabaseAdapter", "Booking created with ID: " + bookingId);
                        callback.onSuccess(bookingId);
                    })
                    .addOnFailureListener(e -> {
                        Log.e("RealtimeDatabaseAdapter", "Error adding document", e);
                        callback.onError(e.toString());
                    });
        } else {
            callback.onError("Failed to generate booking ID");
        }
    }

    public void notifyDataSetChanged() {

    }

    public interface Callback {
        void onSuccess(String bookingId);
        void onError(String message);
    }
}
