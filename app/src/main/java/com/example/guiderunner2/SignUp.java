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

public class SignUp extends AppCompatActivity {

    private Button ToMenu;
    private Button ToLogIn;
    private Button SignUp;

    private EditText UsernameEditText;
    private EditText EmailEditText;
    private EditText PasswordEditText;

    boolean isAllFieldsChecked = false;

    public String URL = "http://10.0.2.2:3000/newaccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    String username = UsernameEditText.getText().toString();
                    String email = EmailEditText.getText().toString();
                    String password = PasswordEditText.getText().toString();


                    Users users = new Users(username, email, password);
                    Gson json = new Gson();
                    RequestTask task = new RequestTask(URL, "POST", json.toJson(users));
                    task.execute();
                }

            }
        });

        ToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });
        ToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,BottomNav.class);
                startActivity(intent);
                finish();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
            }
        });
    }
    private  void init(){
        ToMenu = findViewById(R.id.ToMenu);
        ToLogIn = findViewById(R.id.ToLogIn);
        SignUp = findViewById(R.id.SignUp);
        UsernameEditText = findViewById(R.id.UsernameEditText);
        EmailEditText = findViewById(R.id.EmailEditText);
        PasswordEditText = findViewById(R.id.PasswordEditText);

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
            PasswordEditText.setError("Password must be minimum 8 characters");
            return false;
        } else if (PasswordEditText.length() > 20) {
            PasswordEditText.setError("Password must be max 20 characters");

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
                Toast.makeText(SignUp.this,
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
                Toast.makeText(SignUp.this,
                        "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUp.this,
                        ""+response.getContent(), Toast.LENGTH_LONG).show();
                return;
            } else {
                Toast.makeText(SignUp.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
            }
            switch (requestType) {
                case "GET":
                    break;
                case "POST":
                    Intent intent = new Intent(SignUp.this, LogIn.class);
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