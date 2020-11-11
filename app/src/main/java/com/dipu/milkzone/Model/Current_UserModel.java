package com.dipu.milkzone.Model;

public class Current_UserModel {
    private String email, token;

    public Current_UserModel() {

    }

    public Current_UserModel(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
