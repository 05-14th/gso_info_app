package com.example.gsoinfoapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ImageButton viewHome;
    private ImageButton viewArchive;
    private ImageButton viewSettings;
    private ImageButton leadExit;
    private ImageButton ReturnFromSettings;
    private ImageButton ReturnHome;
    private ConstraintLayout home_layout;
    private ConstraintLayout archive_layout;
    private ConstraintLayout settings_layout;
    private ImageButton archiveButton_;
    private ImageButton settingsButton_;
    private ImageButton exitButton_;
    private TextView home_banner;
    private LinearLayout scrollLayout;
    private TextView main_content;

    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        String[] themeList = {MyContract.Entry.columnBackground, MyContract.Entry.columnSubBg, MyContract.Entry.buttonColor};
        cursor = db.query(MyContract.Entry.TableName, themeList, null, null, null, null, null);
        try {
            db.execSQL(MyContract.Entry.CREATE_TABLE);
            setDefaultMode();
        } catch (Exception e){
            e.printStackTrace();
            initializeTheme();
        }
    }

    public void useArchive(View view){
        Intent archive_intent = new Intent(this, ArchiveActivity.class);
        startActivity(archive_intent);
        finish();
    }

    public void useSettings(View view){
        Intent settings_intent = new Intent(this, SettingsActivity.class);
        startActivity(settings_intent);
        finish();
    }

    public void setDefaultMode(){
        ContentValues values = new ContentValues();
        values.put(MyContract.Entry.columnBackground, Integer.toHexString(getColor(R.color.cnsc_maroon)));
        values.put(MyContract.Entry.columnSubBg, Integer.toHexString(getColor(R.color.white)));
        values.put(MyContract.Entry.buttonColor, Integer.toHexString(getColor(R.color.cnsc_gold)));
        db.insert(MyContract.Entry.TableName, null, values);
    }
    public void exitApp(View view){
        finish();
    }

    @SuppressLint("Range")
    public void initializeTheme(){
        home_layout = findViewById(R.id.homeLayout);
        archiveButton_ = findViewById(R.id.archiveButton);
        settingsButton_ = findViewById(R.id.settingsButton);
        exitButton_ = findViewById(R.id.exitButton);
        home_banner = findViewById(R.id.homeBanner);
        scrollLayout = findViewById(R.id.scroll);

        while(cursor.moveToNext()) {
            String bgColor = "#" + cursor.getString(cursor.getColumnIndex("main_background"));
            int bg_color = Color.parseColor(bgColor);
            String subBgColor = "#" + cursor.getString(cursor.getColumnIndex("sub_background"));
            int sub_bg_color = Color.parseColor(subBgColor);
            String buttonColor = "#" + cursor.getString (cursor.getColumnIndex("button_color"));
            int button_color_ = Color.parseColor(buttonColor);

            home_layout.setBackgroundColor(bg_color);
            scrollLayout.setBackgroundColor(sub_bg_color);
            exitButton_.setBackgroundTintList(ColorStateList.valueOf(button_color_));
            settingsButton_.setBackgroundTintList(ColorStateList.valueOf(button_color_));
            archiveButton_.setBackgroundTintList(ColorStateList.valueOf(button_color_));
            home_banner.setBackgroundColor(button_color_);
        }
    }
}