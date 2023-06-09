package com.example.sts_registration_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sts_registration_test.register.UserRequest;
import com.example.sts_registration_test.register.UserResponse;
import com.example.sts_registration_test.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_register_details extends AppCompatActivity {

    EditText fname, lname,address,gender,contact,dob,category;
    Button buttonRegister;

    TextView tvShowServerMessage;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg_details);

        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        gender = findViewById(R.id.gender);
        contact = findViewById(R.id.contact);
        category = findViewById(R.id.category);
        dob = findViewById(R.id.dob);

        buttonRegister = findViewById(R.id.adddetailsbtn);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

//        tvShowServerMessage = findViewById(R.id.tvShowMessage);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserDetails(createRequest());
            }
        });
    }

    public UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstname(fname.getText().toString());
        userRequest.setLastname(lname.getText().toString());
        userRequest.setAddress(address.getText().toString());
        userRequest.setGender(gender.getText().toString());
        userRequest.setContact(contact.getText().toString());
        userRequest.setCategory(category.getText().toString());
        userRequest.setDob(dob.getText().toString());
        userRequest.setUserid(sharedPrefManager.getUser().getUserId());
        return userRequest;
    }

    public void addUserDetails(UserRequest userRequest) {
        Call<UserResponse> userResponseCall = ApiClient.getUserService().addDetails(sharedPrefManager.getUser().getToken(),userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (response.isSuccessful()) {
                    if (userResponse != null && userResponse.getStatus() == 200) {
                        sharedPrefManager.addUserDetails(userResponse.getUser());
                        Toast.makeText(User_register_details.this, "user registered " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        tvShowServerMessage.setText(response.body().getMessage());
                        Intent i = new Intent(getApplicationContext(), PassengerHomePage.class);

                        startActivity(i);
                    } else {
                        Toast.makeText(User_register_details.this, "request failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(User_register_details.this, "failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}