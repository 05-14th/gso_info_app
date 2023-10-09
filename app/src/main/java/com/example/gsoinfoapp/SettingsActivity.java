package com.example.gsoinfoapp;

import static android.graphics.Color.parseColor;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
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

public class SettingsActivity extends AppCompatActivity {

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
    public TextView headerView;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.settings);
        dbHelper = new MyDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        String[] themeList = {MyContract.Entry.columnBackground, MyContract.Entry.columnSubBg, MyContract.Entry.buttonColor};
        cursor = db.query(MyContract.Entry.TableName, themeList, null, null, null, null, null);
        initializeTheme();
        defaultMode  = findViewById(R.id.default_mode);
        classicMode = findViewById(R.id.classic_mode);
        darkMode = findViewById(R.id.dark_mode);
        themeStyles = findViewById(R.id.themes_mode);
        fontStyles = findViewById(R.id.font_group);
        arialFont = findViewById(R.id.arial);
        helveticaFont = findViewById(R.id.helvetica);
        timesFont = findViewById(R.id.times_new_roman);

        // Changable Parts
        settingsLayout_ = findViewById(R.id.settingsLayout);
        resetButton = findViewById(R.id.resetDefault);
        saveButton = findViewById(R.id.saveSettings);
        return_home = findViewById(R.id.returnHome_);
        headerView = findViewById(R.id.settingsTitle);
        settingsHolder_ = findViewById(R.id.settingsHolder);
        fontControl();
        themesControl();

        fontStyles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sharedPreferences = getSharedPreferences("PrefFont", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selected_option", checkedId);
                editor.apply();
            }
        });
        themeStyles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selected_option_id", checkedId);
                editor.apply();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultMode();
            }
        });

    }

    public void returnHome(View view){
        Intent settingsIntent = new Intent(this, MainActivity.class);
        startActivity(settingsIntent);
        finish();
    }

    public void fontControl(){
        fontStyles = findViewById(R.id.font_group);

        SharedPreferences sharedPreferences = getSharedPreferences("PrefFont", Context.MODE_PRIVATE);
        int lastSelectedFont = sharedPreferences.getInt("selected_option", R.id.arial); // Default to the first option

        fontStyles.check(lastSelectedFont);

    }

   public void themesControl() {
        themeStyles = findViewById(R.id.themes_mode);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int lastSelectedId = sharedPreferences.getInt("selected_option_id", R.id.default_mode); // Default to the first option

        themeStyles.check(lastSelectedId);
    }

    @SuppressLint("Range")
    public void initializeTheme(){
        settingsLayout_ = findViewById(R.id.settingsLayout);
        resetButton = findViewById(R.id.resetDefault);
        saveButton = findViewById(R.id.saveSettings);
        return_home = findViewById(R.id.returnHome_);
        headerView = findViewById(R.id.settingsTitle);
        settingsHolder_ = findViewById(R.id.settingsHolder);

        while(cursor.moveToNext()) {
            String bgColor = "#" + cursor.getString(cursor.getColumnIndex("main_background"));
            int bg_color = Color.parseColor(bgColor);
            String subBgColor = "#" + cursor.getString(cursor.getColumnIndex("sub_background"));
            int sub_bg_color = Color.parseColor(subBgColor);
            String buttonColor = "#" + cursor.getString (cursor.getColumnIndex("button_color"));
            int button_color_ = Color.parseColor(buttonColor);

            settingsLayout_.setBackgroundColor(bg_color);
            settingsHolder_.setBackgroundColor(sub_bg_color);
            headerView.setBackgroundColor(button_color_);
            return_home.setBackgroundTintList(ColorStateList.valueOf(button_color_));
            saveButton.setBackgroundColor(button_color_);
            resetButton.setBackgroundColor(button_color_);
        }
    }

    public void setDefaultMode(){
        defaultMode.setChecked(true);
        int bg_color = getColor(R.color.cnsc_maroon);
        int sub_bg_color = getColor(R.color.white);
        int button_color_ = getColor(R.color.cnsc_gold);
        ContentValues values = new ContentValues();
        db.execSQL(MyContract.Entry.DELETE_TABLE);
        values.put(MyContract.Entry.columnBackground,  Integer.toHexString(getColor(R.color.cnsc_maroon)).substring(2));
        values.put(MyContract.Entry.columnSubBg, Integer.toHexString(getColor(R.color.white)).substring(2));
        values.put(MyContract.Entry.buttonColor,  Integer.toHexString(getColor(R.color.cnsc_gold)).substring(2));
        db.insert(MyContract.Entry.TableName, null, values);
        initializeTheme();
        settingsLayout_.setBackgroundColor(bg_color);
        settingsHolder_.setBackgroundColor(sub_bg_color);
        headerView.setBackgroundColor(button_color_);
        return_home.setBackgroundTintList(ColorStateList.valueOf(button_color_));
        saveButton.setBackgroundColor(button_color_);
        resetButton.setBackgroundColor(button_color_);
    }


    public void setDarkMode(){
        int bg_color = getColor(R.color.black);
        int sub_bg_color = getColor(R.color.light_grey);
        int button_color_ = getColor(R.color.neon_blue);
        darkMode.setChecked(true);
        ContentValues _values = new ContentValues();
        db.execSQL(MyContract.Entry.DELETE_TABLE);
        _values.put(MyContract.Entry.columnBackground, Integer.toHexString(getColor(R.color.black)).substring(2));
        _values.put(MyContract.Entry.columnSubBg,  Integer.toHexString(getColor(R.color.light_grey)).substring(2) );
        _values.put(MyContract.Entry.buttonColor, Integer.toHexString(getColor(R.color.neon_blue)).substring(2));
        db.insert(MyContract.Entry.TableName, null, _values);
        initializeTheme();
        settingsLayout_.setBackgroundColor(bg_color);
        settingsHolder_.setBackgroundColor(sub_bg_color);
        headerView.setBackgroundColor(button_color_);
        return_home.setBackgroundTintList(ColorStateList.valueOf(button_color_));
        saveButton.setBackgroundColor(button_color_);
        resetButton.setBackgroundColor(button_color_);
    }

    public void setClassicMode(){
        int bg_color = getColor(R.color.classic_brown);
        int sub_bg_color = getColor(R.color.beige);
        int button_color_ = getColor(R.color.light_brown);
        classicMode.setChecked(true);
        ContentValues values_ = new ContentValues();
        db.execSQL(MyContract.Entry.DELETE_TABLE);
        values_.put(MyContract.Entry.columnBackground, Integer.toHexString(getColor(R.color.classic_brown)).substring(2));
        values_.put(MyContract.Entry.columnSubBg, Integer.toHexString(getColor(R.color.beige)).substring(2));
        values_.put(MyContract.Entry.buttonColor,  Integer.toHexString(getColor(R.color.light_brown)).substring(2));
        db.insert(MyContract.Entry.TableName, null, values_);
        initializeTheme();
        settingsLayout_.setBackgroundColor(bg_color);
        settingsHolder_.setBackgroundColor(sub_bg_color);
        headerView.setBackgroundColor(button_color_);
        return_home.setBackgroundTintList(ColorStateList.valueOf(button_color_));
        saveButton.setBackgroundColor(button_color_);
        resetButton.setBackgroundColor(button_color_);
    }

    public void changeTheme(View view){
        changeFont();
        if (darkMode.isChecked()){
            setDarkMode();
        } else if (classicMode.isChecked()) {
            setClassicMode();
        } else if (defaultMode.isChecked()){
            setDefaultMode();
        }
    }
    
    public void changeFont(){
        SharedPreferences fontPref = getSharedPreferences("pref_font", Context.MODE_PRIVATE);
        SharedPreferences.Editor fontEditor = fontPref.edit();
        if(arialFont.isChecked()){
            fontEditor.putInt("font_key", R.font.arial);
        } else if (helveticaFont.isChecked()) {
            fontEditor.putInt("font_key", R.font.freesans);
        } else if (timesFont.isChecked()) {
            fontEditor.putInt("font_key", R.font.times);
        }
        fontEditor.apply();
    }

}
