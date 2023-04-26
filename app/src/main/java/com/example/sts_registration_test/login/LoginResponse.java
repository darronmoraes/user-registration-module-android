package com.example.sts_registration_test.login;

import com.example.sts_registration_test.model.User;

public class LoginResponse {
    private String message;
    private int status;
    private  User user;

    public LoginResponse(String message, int status, User user) {
        this.message = message;
        this.status = status;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
