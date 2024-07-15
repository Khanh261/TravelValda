package com.example.travelvalda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.travelvalda.animation.AnimationHandle;
import com.example.travelvalda.database.AppDatabase;
import com.example.travelvalda.models.Booking;
import com.example.travelvalda.models.Property;
import com.example.travelvalda.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button button;
    private static MainActivity instance;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, AnimationHandle.class);
        startActivity(intent);
        finish();
        new Thread(() -> {
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "hotel_rental").build();
            runDbOperations();
        }).start();
    }

    private void runDbOperations() {
        User newUser = new User();
        newUser.username = "demo";
        newUser.password = "demo";
        newUser.email = "demo@example.com";
        newUser.userType = "guest";
        db.userDao().insertUser(newUser);

        insertSampleProperties();
        List<Property> properties = db.propertyDao().getAllProperties();
        Log.d("DB_CHECK", "Properties: " + properties.size());
    }

    private void insertSampleProperties() {
        String[] titles = {"Beach House", "Mountain Cabin", "City Apartment"};
        String[] descriptions = {"A nice house by the beach.", "A cozy cabin in the mountains.", "A luxury apartment in the city center."};
        double[] prices = {200.0, 150.0, 300.0};
        String[] locations = {"Beachside", "Mountain Range", "Downtown"};

        for (int i = 0; i < titles.length; i++) {
            Property property = new Property();
            property.setOwnerId(1);
            property.setTitle(titles[i]);
            property.setDescription(descriptions[i]);
            property.setPricePerNight(prices[i]);
            property.setLocation(locations[i]);
            db.propertyDao().insertProperty(property);
        }
    }

}



