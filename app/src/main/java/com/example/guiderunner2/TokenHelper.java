package com.example.guiderunner2;

public class TokenHelper {
    public String token;

    public TokenHelper(String token,int accountId) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
