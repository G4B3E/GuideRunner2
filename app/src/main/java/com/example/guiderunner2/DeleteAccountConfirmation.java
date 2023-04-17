package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteAccountConfirmation extends AppCompatActivity {

    private Button BackToProfile2;
    private Button DeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account_confirmation);
        init();
        BackToProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteAccountConfirmation.this,BottomNav.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void init(){
        BackToProfile2 = findViewById(R.id.BackToProfile2);
        DeleteAccount = findViewById(R.id.DeleteAccount);
    }
}