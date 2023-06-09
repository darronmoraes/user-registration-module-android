package com.example.sts_registration_test;

public class Consts {

    // Base URLs
    public static final String BASE_URL_PASSENGER_AUTH = "http://192.168.0.109:5000/user/";
    public static final String BASE_URL_BOOKING = "http://192.168.226.107:5000/booking/";

    // Endpoints
    public static final String ENDPOINT_LOGIN = "login";
    public static final String ENDPOINT_LOGOUT = "logout";
    public static final String ENDPOINT_REGISTRATION = "registration";
    public static final String ENDPOINT_REQUEST_OTP = "create-otp";
    public static final String ENDPOINT_VERIFY_OTP = "verify-otp";
    public static final String ENDPOINT_BUS_STOPS = "bus-stops";

    // Request parameters
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_FIRST_NAME = "first_name";
    public static final String PARAM_LAST_NAME = "last_name";
    public static final String PARAM_ADDRESS = "address";
    public static final String PARAM_PHONE_NUMBER = "phone_number";



    // location
    public static final Integer LOCATION_THRESHOLD = 100;



    // request codes
    public static final Integer REQUEST_CODE = 100;
}
