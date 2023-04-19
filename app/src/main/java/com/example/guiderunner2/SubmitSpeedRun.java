package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitSpeedRun extends AppCompatActivity {

    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;


    private EditText EditTextUserName;
    private EditText EditTextGame;
    private EditText EditTextTime;
    private EditText EditTextPlatform;
    private EditText EditTextDifficulty;
    private EditText EditTextLink;
    private Button Cancel;
    private Button Submit;

    boolean isAllFieldsChecked = false;
    public String URL = "http://10.0.2.2:3000/records";

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
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    String username = EditTextUserName.getText().toString();
                    String gamename = EditTextGame.getText().toString();
                    String time = EditTextTime.getText().toString();
                    String platform = EditTextPlatform.getText().toString();
                    String difficulty = EditTextDifficulty.getText().toString();
                    String youtubelink = EditTextLink.getText().toString();

                    Records records = new Records(username, gamename, time,platform,difficulty,youtubelink);
                    Gson json = new Gson();
                    RequestTask task = new RequestTask(URL, "POST", json.toJson(records));
                    task.execute();
                }

            }
        });

    }



    private void init(){
        EditTextUserName = findViewById(R.id.EditTextUserName);
        EditTextGame = findViewById(R.id.EditTextGame);
        EditTextTime = findViewById(R.id.EditTextTime);
        EditTextPlatform = findViewById(R.id.EditTextPlatform);
        EditTextDifficulty = findViewById(R.id.EditTextDifficulty);
        EditTextLink = findViewById(R.id.EditTextLink);
        Cancel = findViewById(R.id.Cancel);
        Submit = findViewById(R.id.Submit);


    }
    private boolean CheckAllFields() {
        if (EditTextUserName.length() == 0) {
            EditTextUserName.setError("Username is required");
            return false;
        }
        if (EditTextGame.length() == 0) {
            EditTextGame.setError("Game is required");
            return false;
        }
        if (EditTextPlatform.length() == 0) {
            EditTextPlatform.setError("Platform is required");
            return false;
        }
        if (EditTextTime.length() == 0) {
            EditTextTime.setError("Time is required");
            return false;
        }
        if (EditTextDifficulty.length() == 0) {
            EditTextDifficulty.setError("Difficulty is required");
            return false;
        }
        if (EditTextLink.length() == 0) {
            EditTextLink.setError("Link is required");
            return false;
        }

        return true;
    }
    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }


        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl,null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams,null);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(SubmitSpeedRun.this,
                        e.toString(), Toast.LENGTH_SHORT).show();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);

            if (response.getResponseCode() >= 402) {
                Toast.makeText(SubmitSpeedRun.this,
                        "An error occurred while processing the request!", Toast.LENGTH_SHORT).show();
                Toast.makeText(SubmitSpeedRun.this,
                        ""+response.getContent(), Toast.LENGTH_LONG).show();
                return;
            }
            else {
                switch (requestType) {
                    case "GET":
                        break;
                    case "POST":
                        Intent intent = new Intent(SubmitSpeedRun.this, BottomNav.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.slide_in_left,
                                R.anim.slide_out_right);
                        Toast.makeText(SubmitSpeedRun.this, "Successful upload!", Toast.LENGTH_SHORT).show();
                        break;
                    case "PUT":
                        break;
                    case "DELETE":
                        break;
                }

            }

        }
    }


}