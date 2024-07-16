package com.example.travelvalda.Public;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.R;
import com.example.travelvalda.client.DatabaseClient;
import com.example.travelvalda.models.Property;
import com.example.travelvalda.models.PropertyAdapter;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private PropertyAdapter propertyAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        propertyAdapter = new PropertyAdapter();
        recyclerView.setAdapter(propertyAdapter);

        DatabaseClient.getInstance(getApplicationContext()).addInitialData();

        loadProperties();
    }

    private void loadProperties() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Property> properties = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().propertyDao().getAllProperties();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        propertyAdapter.setProperties(properties);
                    }
                });
            }
        }).start();
    }
}
