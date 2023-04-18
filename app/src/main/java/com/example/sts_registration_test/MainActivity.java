package com.example.sts_registration_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_registration_test.otp.requester.OtpRequest;
import com.example.sts_registration_test.otp.requester.OtpResponse;
import com.example.sts_registration_test.user.UserRequest;
import com.example.sts_registration_test.user.UserResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonRegister;

    TextView tvShowServerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        tvShowServerMessage = findViewById(R.id.tvShowMessage);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(createRequest());
            }
        });
    }

    public UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(editTextEmail.getText().toString());
        userRequest.setPassword(editTextPassword.getText().toString());

        return userRequest;
    }

    public void register(UserRequest userRequest) {
        Call<UserResponse> userResponseCall = ApiClient.getRegisterUserRoute().registerUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        // send otp if user is not registered with the email already
                        sendOtp(createOtp(response.body().getEmail()));
                        // redirect to verification of otp
                        Intent verifyOtpIntent = new Intent(MainActivity.this, OtpVerificationActivity.class);
                        // pass email to fragment for verification request
                        verifyOtpIntent.putExtra("email", response.body().getEmail());
                        startActivity(verifyOtpIntent);
                        finish();
                    }
                } else {
                    // shows toast if user already registered
                    String errorMessage = "";
                    try {
                        if (response.errorBody() != null) {
                            JSONObject error = new JSONObject(response.errorBody().string());
                            errorMessage = error.getString("message");
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "request failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public OtpRequest createOtp(String email) {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail(email);
        return otpRequest;
    }

    public void sendOtp(OtpRequest otpRequest) {
        Call<OtpResponse> otpResponseCall = ApiClient.getSendOtpRoute().sendRegistrationOtp(otpRequest);
        otpResponseCall.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "otp sent", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

            }
        });
    }
}