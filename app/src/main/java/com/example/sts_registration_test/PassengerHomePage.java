package com.example.sts_registration_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sts_registration_test.logout.LogoutRequest;
import com.example.sts_registration_test.logout.LogoutResponse;
import com.example.sts_registration_test.sharedpref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerHomePage extends AppCompatActivity {


    Button logoutBtn;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home_page);

        logoutBtn = findViewById(R.id.logout);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(logoutRequest());
//                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(i);
            }
        });


    }

    // ------------------------- Logout --------------------------------------//

    public LogoutRequest logoutRequest(){
        LogoutRequest logoutRequest=new LogoutRequest();
        logoutRequest.setToken(getSessionToken());
        return logoutRequest;
    }



    public void logout(LogoutRequest logoutRequest){
        Call<LogoutResponse> logoutResponseCall= ApiClient.getUserService().logout(logoutRequest);
        logoutResponseCall.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                LogoutResponse logoutResponse=response.body();
                if (response.isSuccessful()){
//                    Toast.makeText(AdminDashboard.this, "Logout successful", Toast.LENGTH_SHORT).show();
                    if(logoutResponse != null && logoutResponse.getStatus() == 200){
                        sharedPrefManager.logout();
                        Toast.makeText(PassengerHomePage.this, "Logout successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(PassengerHomePage.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(PassengerHomePage.this, "onResponse: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(PassengerHomePage.this, "onFailure: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLoggedInUserDetails() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        String username = "Welcome back! "
                + sharedPrefManager.getUser().getUserId()
                + " "
                + sharedPrefManager.getUser().getEmail();

//        tvUsername.setText(username);
//        tvEmail.setText(sharedPrefManager.getUser().getEmail());
    }

    public String getSessionToken() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        return sharedPrefManager.getUser().getToken();
    }

}