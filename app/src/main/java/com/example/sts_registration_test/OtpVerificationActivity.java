package com.example.sts_registration_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sts_registration_test.otp.verify.OtpVerifyRequest;
import com.example.sts_registration_test.otp.verify.OtpVerifyResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerificationActivity extends AppCompatActivity {

    AppCompatEditText otpCode;
    AppCompatButton btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        otpCode = findViewById(R.id.et_otp);
        btnVerify = findViewById(R.id.btn_verify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp(createVerificationRequest());
            }
        });
    }

    public OtpVerifyRequest createVerificationRequest() {
        OtpVerifyRequest otpVerifyRequest = new OtpVerifyRequest();
        otpVerifyRequest.setEmail(getEmailFromIntent());
        otpVerifyRequest.setOtp(Objects.requireNonNull(otpCode.getText()).toString());
        return otpVerifyRequest;
    }

    public void verifyOtp(OtpVerifyRequest otpVerifyRequest) {
        Call<OtpVerifyResponse> otpVerifyResponseCall = ApiClient.getVerifyOtpRoute().verifyRegistrationOtp(otpVerifyRequest);
        otpVerifyResponseCall.enqueue(new Callback<OtpVerifyResponse>() {
            @Override
            public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {

            }
        });
    }

    public String getEmailFromIntent() {
        Intent intent = getIntent();
        return intent.getStringExtra("email");
    }
}