package com.example.gsoinfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ArchiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.archive);
    }

    public void returnHome_(View view){
        Intent archiveIntent = new Intent(this, MainActivity.class);
        startActivity(archiveIntent);
        finish();
    }
}
