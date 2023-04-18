package com.example.sts_registration_test;

import com.example.sts_registration_test.otp.OtpRequest;
import com.example.sts_registration_test.otp.OtpResponse;
import com.example.sts_registration_test.user.UserRequest;
import com.example.sts_registration_test.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRoutes {

    @POST("register")
    Call<UserResponse> registerUser(@Body UserRequest userRequest);

    @POST("send_otp")
    Call<OtpResponse> sendRegistrationOtp(@Body OtpRequest otpRequest);

}
