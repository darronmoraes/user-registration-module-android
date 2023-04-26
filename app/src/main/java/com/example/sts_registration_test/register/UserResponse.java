package com.example.sts_registration_test.register;

import com.example.sts_registration_test.model.User;

public class UserResponse {

    /*
    * "message": "user already registered",
    * "status": "400"
    * */

    private String message;
    private int status;

    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}