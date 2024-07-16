package com.example.travelvalda.dao;

import androidx.annotation.NonNull;

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

public class PropertiesDAO {
    private FirebaseFirestore db;

    public PropertiesDAO() {
    }

    // Hàm để lấy tất cả các property từ Firestore
    public void getAllProperties(final FirestoreCallback firestoreCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query= databaseReference.child("Property");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Property> propertyList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Property property = new Property();
                    property.setDescription(dataSnapshot.child("description").getValue(String.class));
                    property.setTitle(dataSnapshot.child("title").getValue(String.class));
                    property.setPricePerNight(dataSnapshot.child("pricePerNight").getValue(Double.class));
                    property.setLocation(dataSnapshot.child("location").getValue(String.class));
                    property.setOwnerId(dataSnapshot.child("ownerId").getValue(String.class)) ;
                    property.setPropertyId(dataSnapshot.getKey());
                }
                firestoreCallback.onCallback(propertyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /*public void getPropertiesByName(String name,final FirestoreCallback firestoreCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query= databaseReference.child("Property");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Property> propertyList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if(dataSnapshot.child("title").getValue(String.class).contains(name)){
                        Property property = new Property();
                        property.setDescription(dataSnapshot.child("description").getValue(String.class));
                        property.setTitle(dataSnapshot.child("title").getValue(String.class));
                        property.setPricePerNight(dataSnapshot.child("pricePerNight").getValue(Double.class));
                        property.setLocation(dataSnapshot.child("location").getValue(String.class));
                        property.setOwnerId(dataSnapshot.child("ownerId").getValue(String.class)) ;
                        if (property != null) {
                            String key = dataSnapshot.getKey();
                            if (key != null && key.contains("_")) {
                                String[] parts = key.split("_");
                                if (parts.length > 1) {
                                    property.propertyId = Integer.parseInt(parts[1]);
                                    propertyList.add(property);
                                }
                            }
                        }
                    }

                }
                firestoreCallback.onCallback(propertyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    // Interface để xử lý callback
    public interface FirestoreCallback {
        void onCallback(List<Property> propertyList);
    }
}
