package com.example.gsoinfoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button resetButton;
    private Button saveButton;
    private ImageButton return_home;
    private ConstraintLayout settingsLayout_;
    private LinearLayout settingsHolder_;
    private RadioGroup themeStyles;
    private RadioButton defaultMode;
    private RadioButton classicMode;
    private RadioButton darkMode;
    private RadioButton arialFont;
    private RadioButton helveticaFont;
    private RadioButton timesFont;
    private RadioGroup fontStyles;
    private TextView headerView;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.settings);
        defaultMode  = findViewById(R.id.default_mode);
        classicMode = findViewById(R.id.classic_mode);
        darkMode = findViewById(R.id.dark_mode);
        resetButton = findViewById(R.id.resetDefault);
        saveButton = findViewById(R.id.saveSettings);
        return_home = findViewById(R.id.returnHome_);
        settingsLayout_ = findViewById(R.id.settingsLayout);
        themeStyles = findViewById(R.id.themes_mode);
        fontStyles = findViewById(R.id.font_group);
        arialFont = findViewById(R.id.arial);
        helveticaFont = findViewById(R.id.helvetica);
        timesFont = findViewById(R.id.times_new_roman);
        headerView = findViewById(R.id.settingsTitle);
        settingsHolder_ = findViewById(R.id.settingsHolder);
        preferences = getSharedPreferences("savedPreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        fontControl();
        themesControl();
    }

    public void returnHome(View view){
        Intent settingsIntent = new Intent(this, MainActivity.class);
        startActivity(settingsIntent);
        finish();
    }

    public void fontControl(){
        arialFont.setChecked(true);
    }

    public void themesControl(){
        defaultMode.setChecked(true);
    }

    public void defaultSettings(View view){
        defaultMode.setChecked(true);
        settingsLayout_.setBackgroundColor(getColor(R.color.cnsc_maroon));
        saveButton.setBackgroundColor(getColor(R.color.cnsc_gold));
        resetButton.setBackgroundColor(getColor(R.color.cnsc_gold));
        return_home.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cnsc_gold)));
        headerView.setBackgroundColor(getColor(R.color.cnsc_gold));
        settingsHolder_.setBackgroundColor(getColor(R.color.white));
        editor.putInt("background_color", getColor(R.color.cnsc_maroon));
        editor.apply();
    }

    public void setDefaultMode(){
        defaultMode.setChecked(true);
        settingsLayout_.setBackgroundColor(getColor(R.color.cnsc_maroon));
        saveButton.setBackgroundColor(getColor(R.color.cnsc_gold));
        resetButton.setBackgroundColor(getColor(R.color.cnsc_gold));
        return_home.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cnsc_gold)));
        headerView.setBackgroundColor(getColor(R.color.cnsc_gold));
        settingsHolder_.setBackgroundColor(getColor(R.color.white));
        settingsHolder_.setBackgroundColor(getColor(R.color.white));
    }

    public void setDarkMode(){
        darkMode.setChecked(true);
        settingsLayout_.setBackgroundColor(getColor(R.color.black));
        saveButton.setBackgroundColor(getColor(R.color.neon_blue));
        resetButton.setBackgroundColor(getColor(R.color.neon_blue));
        return_home.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.neon_blue)));
        headerView.setBackgroundColor(getColor(R.color.neon_blue));
        settingsHolder_.setBackgroundColor(getColor(R.color.light_grey));
    }

    public void setClassicMode(){
        classicMode.setChecked(true);
        settingsLayout_.setBackgroundColor(getColor(R.color.classic_brown));
        saveButton.setBackgroundColor(getColor(R.color.light_brown));
        resetButton.setBackgroundColor(getColor(R.color.light_brown));
        return_home.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.light_brown)));
        headerView.setBackgroundColor(getColor(R.color.light_brown));
        settingsHolder_.setBackgroundColor(getColor(R.color.beige));
    }

    public void changeTheme(View view){
        if (darkMode.isChecked()){
            setDarkMode();
        } else if (classicMode.isChecked()) {
            setClassicMode();
        } else if (defaultMode.isChecked()){
            setDefaultMode();
        }
    }

}
