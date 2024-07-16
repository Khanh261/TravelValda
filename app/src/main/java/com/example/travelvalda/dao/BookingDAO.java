package com.example.travelvalda.dao;

import androidx.annotation.NonNull;

import com.example.travelvalda.models.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private FirebaseFirestore db;

    public BookingDAO() {
    }

    // Hàm để lấy các booking có guestId trùng với id của người dùng hiện tại
    public void getBookingsForCurrentUser(String currentUserId, final FirestoreCallback firestoreCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference.child("Booking").orderByChild("guestId").equalTo(currentUserId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Booking> bookingList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Booking booking = dataSnapshot.getValue(Booking.class);
                    if (booking != null) {
                        booking.setBookingId(dataSnapshot.getKey()); // Set bookingId from key
                        bookingList.add(booking);
                    }
                }
                firestoreCallback.onCallback(bookingList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi ở đây
            }
        });
    }

    // Interface để xử lý callback
    public interface FirestoreCallback {
        void onCallback(List<Booking> bookingList);
    }
}

