package com.example.gsoinfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void exitApp(View view){
        finish();
    }
}