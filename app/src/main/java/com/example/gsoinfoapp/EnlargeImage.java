package com.example.gsoinfoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class EnlargeImage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.custom_image_dialog);
        ImageView previewImageView = findViewById(R.id.previewImageView);
        SharedPreferences sharedPreferences = getSharedPreferences("ImageClicked", Context.MODE_PRIVATE);
        int drawable = sharedPreferences.getInt("image", R.drawable.cnsc_logo___white_border);
        previewImageView.setImageResource(drawable);
    }
}
