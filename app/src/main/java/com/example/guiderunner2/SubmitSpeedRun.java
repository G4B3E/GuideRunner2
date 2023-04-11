package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitSpeedRun extends AppCompatActivity {

    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;

    private EditText EditTextGame;
    private EditText EditTextPlatform;
    private EditText EditTextTime;
    private EditText EditTextDifficulty;
    private EditText EditTextLink;
    private Button Cancel;
    private Button Submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_speed_run);
        init();
        expandableListViewExample = (ExpandableListView) findViewById(R.id.expandableListViewSample);
        expandableDetailList = ExpandableListDataItems.getData();
        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        expandableListAdapter = new CustomizedExpandableListAdapter(this, expandableTitleList, expandableDetailList);
        expandableListViewExample.setAdapter(expandableListAdapter);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmitSpeedRun.this,BottomNav.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void init(){
        EditTextGame = findViewById(R.id.EditTextGame);
        EditTextPlatform = findViewById(R.id.EditTextPlatform);
        EditTextTime = findViewById(R.id.EditTextTime);
        EditTextDifficulty = findViewById(R.id.EditTextDifficulty);
        EditTextLink = findViewById(R.id.EditTextLink);
        Cancel = findViewById(R.id.Cancel);
        Submit = findViewById(R.id.Submit);



    }
}