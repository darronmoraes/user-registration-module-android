package com.example.sts_registration_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_register_details extends AppCompatActivity {

    EditText fname, lname,address,gender,contact;
    Button buttonRegister;

    TextView tvShowServerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg_details);

        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        gender = findViewById(R.id.gender);
        contact = findViewById(R.id.contact);


//        tvShowServerMessage = findViewById(R.id.tvShowMessage);

//        buttonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                register(createRequest());
//            }
//        });
//    }

//    public UserRequest createRequest() {
//        UserRequest userRequest = new UserRequest();
//        userRequest.setEmail(editTextEmail.getText().toString());
//        userRequest.setPassword(editTextPassword.getText().toString());
//
//        return userRequest;
//    }
//
//    public void register(UserRequest userRequest) {
//        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
//        userResponseCall.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//
//                if (response.body() != null) {
//                    if (response.isSuccessful()) {
//                        Toast.makeText(User_register_details.this, "user registered " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        tvShowServerMessage.setText(response.body().getMessage());
//                        Intent i = new Intent(getApplicationContext(), OtpSend.class);
//                        startActivity(i);
//                    } else {
//                        Toast.makeText(User_register_details.this, "request failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(User_register_details.this, "failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}