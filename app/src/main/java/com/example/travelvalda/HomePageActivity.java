package com.example.travelvalda;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView popularHotelsList;
    private RecyclerView recommendedHotelsList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        popularHotelsList = findViewById(R.id.popular_hotels_list);
        recommendedHotelsList = findViewById(R.id.recommended_hotels_list);

        List<Hotel> popularHotels = new ArrayList<>();
        List<Hotel> recommendedHotels = new ArrayList<>();

        // Thêm dữ liệu mẫu
        popularHotels.add(new Hotel("Khách sạn 1", R.drawable.hotel));
        popularHotels.add(new Hotel("Khách sạn 2", R.drawable.hotel));
        recommendedHotels.add(new Hotel("Khách sạn 3", R.drawable.hotel));
        recommendedHotels.add(new Hotel("Khách sạn 4", R.drawable.hotel));

        HotelAdapter popularAdapter = new HotelAdapter(popularHotels);
        HotelAdapter recommendedAdapter = new HotelAdapter(recommendedHotels);

        popularHotelsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        popularHotelsList.setAdapter(popularAdapter);

        recommendedHotelsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recommendedHotelsList.setAdapter(recommendedAdapter);
    }
}
