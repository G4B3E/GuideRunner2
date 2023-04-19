package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class DeleteAccountConfirmation extends AppCompatActivity {

    private Button BackToProfile2;
    private Button DeleteAccount;
    public String URL = "http://10.0.2.2:3000/accounts/delete";

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
        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestTask requestTask = new RequestTask(URL, "DELETE");
                requestTask.execute();
            }
        });


    }

    public void init(){
        BackToProfile2 = findViewById(R.id.BackToProfile2);
        DeleteAccount = findViewById(R.id.DeleteAccount);
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
                        response = RequestHandler.get(requestUrl,null);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams,null);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams,null);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl,sharedPreferences.getString("token",null));
                        break;
                }
            } catch (IOException e) {}
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
                Toast.makeText(DeleteAccountConfirmation.this,
                        "Please chech your network or create an account!", Toast.LENGTH_SHORT).show();
                Toast.makeText(DeleteAccountConfirmation.this,
                        "You are not logged in or not connected to the server!", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                Toast.makeText(DeleteAccountConfirmation.this, "Delete successful!", Toast.LENGTH_SHORT).show();
            }
            switch (requestType) {
                case "GET":
                    break;
                case "POST":
                    break;
                case "PUT":
                    break;
                case "DELETE":
                    SharedPreferences sharedPreferences = getSharedPreferences("Important", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("token");
                    editor.commit();
                    Intent intent = new Intent(DeleteAccountConfirmation.this, OpenScreenMenu.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}