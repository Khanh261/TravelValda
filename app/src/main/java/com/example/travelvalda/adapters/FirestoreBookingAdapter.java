package com.example.travelvalda.adapters;

import android.util.Log;

import com.example.travelvalda.models.Booking;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreBookingAdapter {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void createBooking(Booking booking, Callback callback) {
        db.collection("bookings").add(booking)
                .addOnSuccessListener(documentReference -> {
                    Log.d("FirestoreBookingAdapter", "Booking created with ID: " + documentReference.getId());
                    callback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreBookingAdapter", "Error adding document", e);
                    callback.onError(e.toString());
                });
    }

    public interface Callback {
        void onSuccess();
        void onError(String message);
    }
}
