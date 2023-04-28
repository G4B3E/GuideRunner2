package com.example.guiderunner2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class LogOutConfirmation extends AppCompatActivity {
    private Button LogOut;
    private Button BackToProfile;
    public String URL = "http://10.0.2.2:3000/auth/logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_confirmation);
        init();
        BackToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogOutConfirmation.this, BottomNav.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestTask requestTask = new RequestTask(URL, "DELETE");
                requestTask.execute();
            }
        });

    }

    private void init() {
        LogOut = findViewById(R.id.LogOut);
        BackToProfile = findViewById(R.id.BackToProfile);
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
            SharedPreferences sharedPreferences = getSharedPreferences("Important", Context.MODE_PRIVATE);
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl, null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams, null);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams, null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl, sharedPreferences.getString("token", null));
                        break;
                }
            } catch (IOException e) {
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
                Toast.makeText(LogOutConfirmation.this,
                        "An error occurred while processing the request!", Toast.LENGTH_SHORT).show();
                Toast.makeText(LogOutConfirmation.this,
                        "Please login or restart the app!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LogOutConfirmation.this, OpenScreenMenu.class);
                startActivity(intent);
                finish();
                return;

            } else {
                Toast.makeText(LogOutConfirmation.this, "Checkout successful!", Toast.LENGTH_SHORT).show();
            }
            switch (requestType) {
                case "GET":
                    break;
                case "POST":
                    break;
                case "PUT":
                    break;
                case "DELETE":
                    SharedPreferences sharedPreferences = getSharedPreferences("Important",Context.MODE_PRIVATE);
                    SharedPreferences.Editor acceditor = sharedPreferences.edit();
                    acceditor.remove("accountId");
                    acceditor.commit();

                    SharedPreferences sharedPreferences2 = getSharedPreferences("Important", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.remove("token");
                    editor.commit();
                    Intent intent = new Intent(LogOutConfirmation.this, OpenScreenMenu.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}