package com.example.sts_registration_test;

public class UserResponse {

    /*
    * "message": "user already registered",
    * "status": "400"
    * */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
