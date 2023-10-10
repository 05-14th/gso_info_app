package com.example.gsoinfoapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

public class ArchiveActivity extends AppCompatActivity {
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ImageButton return_button;
    private TextView gallery_banner;
    private ConstraintLayout archiveBg;

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.archive);
        dbHelper = new MyDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        String[] themeList = {MyContract.Entry.columnBackground, MyContract.Entry.columnSubBg, MyContract.Entry.buttonColor};
        cursor = db.query(MyContract.Entry.TableName, themeList, null, null, null, null, null);
        initializeTheme();

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this);
        viewPager.setAdapter(adapter);
    }

    public void returnHome_(View view) {
        Intent archiveIntent = new Intent(this, MainActivity.class);
        startActivity(archiveIntent);
        finish();
    }

    @SuppressLint("Range")
    public void initializeTheme() {
        return_button = findViewById(R.id.returnHome);
        gallery_banner = findViewById(R.id.archiveTitle);
        archiveBg = findViewById(R.id.archiveLayout);

        while (cursor.moveToNext()) {
            String bgColor = "#" + cursor.getString(cursor.getColumnIndex("main_background"));
            int bg_color = Color.parseColor(bgColor);
            String subBgColor = "#" + cursor.getString(cursor.getColumnIndex("sub_background"));
            int sub_bg_color = Color.parseColor(subBgColor);
            String buttonColor = "#" + cursor.getString(cursor.getColumnIndex("button_color"));
            int button_color_ = Color.parseColor(buttonColor);

            archiveBg.setBackgroundColor(bg_color);
            return_button.setBackgroundTintList(ColorStateList.valueOf(button_color_));
            gallery_banner.setBackgroundTintList(ColorStateList.valueOf(button_color_));
        }

    }


}