package com.example.sts_registration_test;

import com.example.sts_registration_test.login.LoginRequest;
import com.example.sts_registration_test.login.LoginResponse;
import com.example.sts_registration_test.logout.LogoutRequest;
import com.example.sts_registration_test.logout.LogoutResponse;
import com.example.sts_registration_test.register.UserRequest;
import com.example.sts_registration_test.register.UserResponse;
import com.example.sts_registration_test.send_otp.RequestOtp;
import com.example.sts_registration_test.send_otp.ResponseOtp;
import com.example.sts_registration_test.ticketbooking.ResponseBusStrand;
import com.example.sts_registration_test.verify_otp.RequestVerifyOtp;
import com.example.sts_registration_test.verify_otp.ResponseVerifyOtp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @POST(Consts.ENDPOINT_REGISTRATION)
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    @POST(Consts.ENDPOINT_REQUEST_OTP)
    Call<ResponseOtp> sendOtp(@Body RequestOtp requestOtp);

    @POST(Consts.ENDPOINT_VERIFY_OTP)
    Call<ResponseVerifyOtp> verifyOtp(@Body RequestVerifyOtp requestVerifyOtp);

    @POST(Consts.ENDPOINT_LOGIN)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @HTTP(method = "DELETE", path = Consts.ENDPOINT_LOGOUT, hasBody = true)
    Call<LogoutResponse> logout(@Body LogoutRequest logoutRequest);

    @POST("add-passenger-details")
    Call<UserResponse> addDetails(@Header ("Authorization") String token, @Body UserRequest userRequest);

    @GET(Consts.ENDPOINT_BUS_STOPS)
    Call<ResponseBusStrand> getBusStands();

}
