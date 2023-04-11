package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class OpenScreenMenu extends AppCompatActivity {

    private Button SignUp;
    private Button LogIn;
    private Button Skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen_menu);
        init();
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenScreenMenu.this,SignUp.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenScreenMenu.this,LogIn.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenScreenMenu.this,BottomNav.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in,
                        R.anim.fade_out);
            }
        });
    }
    private void init(){
        SignUp = findViewById(R.id.SignUp);
        LogIn = findViewById(R.id.LogIn);
        Skip = findViewById(R.id.Skip);
    }
}