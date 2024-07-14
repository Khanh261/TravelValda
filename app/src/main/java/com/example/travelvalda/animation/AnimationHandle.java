package com.example.travelvalda.animation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.travelvalda.DashBoard;
import com.example.travelvalda.MainActivity;
import com.example.travelvalda.R;

public class AnimationHandle extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_view);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Apply system bar insets to the view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.startView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Load animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Hooks
        image = findViewById(R.id.startView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        // Apply animations to views
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        // Delay for splash screen
        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(AnimationHandle.this, DashBoard.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }
}
