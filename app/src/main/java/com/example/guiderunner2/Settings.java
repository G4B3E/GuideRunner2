package com.example.guiderunner2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    private Button ButtonBack;

    private RadioButton RadioButtonEnglish;
    private RadioButton RadioButtonHungarian;
    private RadioGroup RadioGroupLanguages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,BottomNav.class);
                startActivity(intent);
                finish();
            }
        });

    }





    public void init(){
        ButtonBack = findViewById(R.id.ButtonBack);
        RadioButtonEnglish = findViewById(R.id.RadioButtonEnglish);
        RadioButtonHungarian = findViewById(R.id.RadioButtonHungarian);
        RadioGroupLanguages = findViewById(R.id.RadioGroupLanguages);
    }
}