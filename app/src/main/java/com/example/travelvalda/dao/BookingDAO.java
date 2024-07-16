package com.example.travelvalda.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.Property;
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

    public void getBookingsForOwner(String currentUserId, final FirestoreCallback firestoreCallback) {
        // Lấy danh sách các property của owner
        getPropertiesForOwner(currentUserId, new PropertyCallback() {
            @Override
            public void onCallback(List<Property> propertyList) {
                if (propertyList != null && !propertyList.isEmpty()) {
                    // Lấy danh sách các booking có propertyId nằm trong danh sách propertyList
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    Query query = databaseReference.child("Booking").orderByChild("propertyId");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<Booking> bookingList = new ArrayList<>();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Booking booking = dataSnapshot.getValue(Booking.class);
                                if (booking != null) {
                                    // Kiểm tra xem propertyId của booking có trong danh sách propertyList hay không
                                    for (Property property : propertyList) {
                                        if (booking.getPropertyId().equals(property.getPropertyId())) {
                                            booking.setBookingId(dataSnapshot.getKey()); // Set bookingId from key
                                            bookingList.add(booking);
                                            break; // Thoát khỏi vòng lặp khi tìm thấy propertyId tương ứng
                                        }
                                    }
                                }
                            }
                            firestoreCallback.onCallback(bookingList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý lỗi ở đây
                        }
                    });
                } else {
                    firestoreCallback.onCallback(new ArrayList<>());
                }
            }
        });
    }

    public void getPropertiesForOwner(String ownerId, final PropertyCallback propertyCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference.child("Property").orderByChild("ownerId").equalTo(ownerId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Property> propertyList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Property property = dataSnapshot.getValue(Property.class);
                    if (property != null) {
                        property.setPropertyId(dataSnapshot.getKey()); // Set propertyId from key
                        propertyList.add(property);
                    }
                }
                propertyCallback.onCallback(propertyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi ở đây
            }
        });
    }

    public void acceptBooking(String bookingId, FirestoreCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Booking")
                .child(bookingId);

        databaseReference.child("status").setValue("accept")
                .addOnSuccessListener(aVoid -> {
                    // Log khi cập nhật thành công
                    Log.d("BookingDAO", "Booking " + bookingId + " accepted successfully");
                    // Thực hiện callback nếu cần
                    callback.onCallback(true); // Gọi lại callback với kết quả thành công
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi
                    callback.onCallback(false); // Gọi lại callback với kết quả thất bại
                });
    }

    public void denyBooking(String bookingId, FirestoreCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Booking")
                .child(bookingId);

        databaseReference.child("status").setValue("deny")
                .addOnSuccessListener(aVoid -> {
                    // Log khi cập nhật thành công
                    Log.d("BookingDAO", "Booking " + bookingId + " denied successfully");
                    // Thực hiện callback nếu cần
                    callback.onCallback(true); // Gọi lại callback với kết quả thành công
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi
                    callback.onCallback(false); // Gọi lại callback với kết quả thất bại
                });
    }


    // Interface để xử lý callback
    public interface FirestoreCallback {
        void onCallback(List<Booking> bookingList);

        void onCallback(boolean b);
    }

    public interface PropertyCallback {
        void onCallback(List<Property> propertyList);
    }

}

