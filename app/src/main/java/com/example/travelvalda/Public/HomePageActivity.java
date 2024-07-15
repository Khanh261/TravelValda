package com.example.travelvalda.Public;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelvalda.R;


public class HomePageActivity extends AppCompatActivity {


    private ImageView ivHotelImage;
    private ImageView ivHotelImage4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen); // Replace with your actual layout file name


        // Set click listener for the first hotel image
        ivHotelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start HotelDetailsActivity
                Intent intent = new Intent(HomePageActivity.this, HotelDetailsActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for the second hotel image (if needed)
        ivHotelImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start HotelDetailsActivity
                Intent intent = new Intent(HomePageActivity.this, HotelDetailsActivity.class);
                startActivity(intent);
            }
        });

        // Add similar click listeners for other hotel images as needed
    }
}
