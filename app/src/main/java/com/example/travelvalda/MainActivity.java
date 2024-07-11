package com.example.travelvalda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button button;

    public void bindingViews() {
        button = findViewById(R.id.btnGo);
    }

    public void bindingAction(){
        button.setOnClickListener(this::onclickGo);
    }

    private void onclickGo(View view) {
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
    startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_drawable);
        EdgeToEdge.enable(this);

        bindingViews();
        bindingAction();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.imageView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

    }


}
