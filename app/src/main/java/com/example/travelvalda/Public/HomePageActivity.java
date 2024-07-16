package com.example.travelvalda.Public;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelvalda.Account.LoginActivity;
import com.example.travelvalda.R;
import com.example.travelvalda.adapters.PropertyAdapter;
import com.example.travelvalda.dao.PropertiesDAO;
import com.example.travelvalda.models.Property;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    private List<Property> propertyList;
    private RecyclerView rvProperty;
    private PropertyAdapter propertyAdapter;
    private EditText editText;
    private Button btnSearch;

    FirebaseAuth mAuth;
    FirebaseUser userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen); // Replace with your actual layout file name
        mAuth = FirebaseAuth.getInstance();
        userDetails = mAuth.getCurrentUser();
        propertyList = new ArrayList<>();

        initView();
        initAction();
        if (userDetails == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            initRecyclerView();
        }

        // Add similar click listeners for other hotel images as needed
    }

    private void initAction() {
        btnSearch.setOnClickListener(this::onClickSearch);
    }

    private void onClickSearch(View view) {
        String keyword = editText.getText().toString().trim();
        if (keyword.isEmpty()) {
            propertyAdapter.setPropertyList(propertyList);
        } else {
            List<Property> filteredList = new ArrayList<>();
            for (Property property : propertyList) {
                if (property.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(property);
                }
            }
            propertyAdapter.setPropertyList(filteredList);
        }
        propertyAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        rvProperty.setLayoutManager(new LinearLayoutManager(this));

        propertyAdapter = new PropertyAdapter(propertyList);
        rvProperty.setAdapter(propertyAdapter);
        PropertiesDAO propertyDAO = new PropertiesDAO();
        propertyDAO.getAllProperties(new PropertiesDAO.FirestoreCallback() {
            @Override
            public void onCallback(List<Property> properties) {
                propertyList = properties;
                propertyAdapter.setPropertyList(propertyList);
                propertyAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initView() {
        rvProperty = findViewById(R.id.rvProperty);
        editText = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
    }
}
