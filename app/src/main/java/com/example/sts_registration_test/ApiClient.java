package com.example.sts_registration_test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.0.112:5000/user/";

    private static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static ApiRoutes getRegisterUserRoute() {
        ApiRoutes routeRegister = getRetrofit().create(ApiRoutes.class);
        return routeRegister;
    }

    public static ApiRoutes getSendOtpRoute() {
        ApiRoutes sendOtp = getRetrofit().create(ApiRoutes.class);
        return sendOtp;
    }

    public static ApiRoutes getVerifyOtpRoute() {
        ApiRoutes verifyOtp = getRetrofit().create(ApiRoutes.class);
        return verifyOtp;
    }
}
