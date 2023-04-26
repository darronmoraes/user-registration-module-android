package com.example.sts_registration_test;

import com.example.sts_registration_test.login.LoginRequest;
import com.example.sts_registration_test.login.LoginResponse;
import com.example.sts_registration_test.logout.LogoutRequest;
import com.example.sts_registration_test.logout.LogoutResponse;
import com.example.sts_registration_test.register.UserRequest;
import com.example.sts_registration_test.register.UserResponse;
import com.example.sts_registration_test.send_otp.RequestOtp;
import com.example.sts_registration_test.send_otp.ResponseOtp;
import com.example.sts_registration_test.verify_otp.RequestVerifyOtp;
import com.example.sts_registration_test.verify_otp.ResponseVerifyOtp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface UserService {

    @POST("register")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    @POST("send_otp")
    Call<ResponseOtp> sendOtp(@Body RequestOtp requestOtp);

    @POST("verify_otp")
    Call<ResponseVerifyOtp> verifyOtp(@Body RequestVerifyOtp requestVerifyOtp);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @HTTP(method = "DELETE", path = "logout", hasBody = true)
    Call<LogoutResponse> logout(@Body LogoutRequest logoutRequest);

}
