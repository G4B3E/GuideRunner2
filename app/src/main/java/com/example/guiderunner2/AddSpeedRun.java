package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class AddSpeedRun extends AppCompatActivity {

    public EditText EditTextGame;
    public EditText EditTextTime;
    public EditText EditTextPlatform;
    public EditText EditTextDifficulty;
    public EditText EditTextLink;
    public Button ButtonSendSpeedRun;
    private Button ButtonCancel;

    boolean isAllFieldsChecked = false;

    public String URL = "http://10.0.2.2:3000/newrecord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_speed_run);
        init();
        ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSpeedRun.this,BottomNav.class);
                startActivity(intent);
                finish();
            }
        });
        ButtonSendSpeedRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( isAllFieldsChecked = CheckAllFields()){
                String game = EditTextGame.getText().toString();
                String time = EditTextTime.getText().toString();
                String platform = EditTextPlatform.getText().toString();
                String difficulty = EditTextDifficulty.getText().toString();
                String youtubelink = EditTextLink.getText().toString();

                Records records = new Records(game, time, platform, difficulty, youtubelink);
                Gson json = new Gson();
                AddSpeedRun.RequestTask task = new AddSpeedRun.RequestTask(URL, "POST", json.toJson(records));
                task.execute();
            }
            }
        });
    }
    private boolean CheckAllFields() {
        if (EditTextGame.length() == 0) {
            EditTextGame.setError("Game name is required");
            return false;
        }
        if (EditTextTime.length() == 0) {
            EditTextTime.setError("Time is required");
            return false;
        }if (EditTextPlatform.length() == 0) {
            EditTextPlatform.setError("Platform is required");
            return false;
        }
        if (EditTextDifficulty.length() == 0) {
            EditTextDifficulty.setError("Difficulty is required");

        }if (EditTextLink.length() == 0) {
            EditTextLink.setError("Youtube link is required");}
        return true;
    }
    private void init(){
        EditTextGame = findViewById(R.id.EditTextGame);
        EditTextTime = findViewById(R.id.EditTextTime);
        EditTextPlatform = findViewById(R.id.EditTextPlatform);
        EditTextDifficulty = findViewById(R.id.EditTextDifficulty);
        EditTextLink = findViewById(R.id.EditTextLink);
        ButtonSendSpeedRun = findViewById(R.id.ButtonSendSpeedRun);
        ButtonCancel = findViewById(R.id.ButtonCancel);
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
                        response = RequestHandler.get(requestUrl);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(AddSpeedRun.this,
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

            if (response.getResponseCode() >= 400) {
                Toast.makeText(AddSpeedRun.this,
                        "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Toast.makeText(AddSpeedRun.this,
                        ""+response.getContent(), Toast.LENGTH_LONG).show();
                return;
            } else {
                Toast.makeText(AddSpeedRun.this, "Sikeres feltöltés, köszönjük!", Toast.LENGTH_SHORT).show();
            }
            switch (requestType) {
                case "GET":
                    break;
                case "POST":
                    Intent intent = new Intent(AddSpeedRun.this, BottomNav.class);
                    startActivity(intent);
                    finish();
                    break;
                case "PUT":
                    break;
                case "DELETE":
                    break;
            }
        }
    }
}