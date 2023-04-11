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



public class LogIn extends AppCompatActivity {


    private Button LogIn;
    private Button Back;


    private EditText EmailEditText;

    private EditText PasswordEditText;


    boolean isAllFieldsChecked = false;

    public String URL = "http://10.0.2.2:3000/auth/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    String email = EmailEditText.getText().toString();
                    String password = PasswordEditText.getText().toString();

                    LoginUsers users = new LoginUsers(email,password);
                    Gson json = new Gson();
                    LogIn.RequestTask task = new LogIn.RequestTask(URL,"POST",json.toJson(users));
                    task.execute();
                }

            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, OpenScreenMenu.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
    }

    private  void init(){
        LogIn = findViewById(R.id.LogIn);
        EmailEditText = findViewById(R.id.EmailEditText);
        PasswordEditText = findViewById(R.id.PasswordEditText);
        Back = findViewById(R.id.Back);
    }
    private boolean CheckAllFields() {
        if (EmailEditText.length() == 0) {
            EmailEditText.setError("Email is required");
            return false;
        }
        if (PasswordEditText.length() == 0) {
            PasswordEditText.setError("Password is required");
            return false;
        } else if (PasswordEditText.length() < 8) {
            PasswordEditText.setError("Password cannnot be less than 8 characters");
            return false;
        } else if (PasswordEditText.length() > 20) {
            PasswordEditText.setError("Password cannnot be more than 20 characters");

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
                Toast.makeText(LogIn.this,
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
                Toast.makeText(LogIn.this,
                        "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Toast.makeText(LogIn.this,
                        ""+response.getContent(), Toast.LENGTH_LONG).show();
                return;
            } else if (response.getResponseCode() == 403){
                Toast.makeText(LogIn.this,
                        "Kérem csatlakozzon az internetkez!", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(LogIn.this, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
            }
            switch (requestType) {
                case "GET":
                    break;
                case "POST":
                    Intent intent = new Intent(LogIn.this, BottomNav.class);
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