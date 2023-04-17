package com.example.sts_registration_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "user registered " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        tvShowServerMessage.setText(response.body().getToken());
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
}