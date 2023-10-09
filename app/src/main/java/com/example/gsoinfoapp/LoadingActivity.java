package com.example.gsoinfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoadingActivity extends AppCompatActivity {

    private static final long DELAY_DURATION = 5000; // Delay in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // Simulate a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the delay, start the main activity
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the loading screen activity
            }
        }, DELAY_DURATION);

        TextView loadingText = findViewById(R.id.loading_Text);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.loading_text);
        loadingText.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // After the animation is complete, start the main activity
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the loading screen activity
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
