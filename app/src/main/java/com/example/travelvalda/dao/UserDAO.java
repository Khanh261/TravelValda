package com.example.travelvalda.dao;

import androidx.annotation.NonNull;

import com.example.travelvalda.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserDAO() {
        // Constructor
    }

    public void getUserProfile(String userId, final FirestoreCallbackUser firestoreCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                if (user != null) {
                    user.setUserId(snapshot.getKey());
                    List<Users> userProfileList = new ArrayList<>();
                    userProfileList.add(user);
                    firestoreCallback.onCallback(userProfileList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    public void updateUserProfile(String userId, String newFullName, String newEmail, String newPhone, final FirestoreCallbackUpdate callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        databaseReference.child("userName").setValue(newFullName);
        databaseReference.child("email").setValue(newEmail);
        databaseReference.child("phone").setValue(newPhone)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onUpdateSuccess();
                    } else {
                        callback.onUpdateFailure(task.getException().getMessage());
                    }
                });
    }

    public interface FirestoreCallbackUser {
        void onCallback(List<Users> userProfileList);
    }

    public interface FirestoreCallbackUpdate {
        void onUpdateSuccess();
        void onUpdateFailure(String error);
    }
}
