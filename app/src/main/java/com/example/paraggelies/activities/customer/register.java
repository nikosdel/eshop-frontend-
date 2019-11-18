package com.example.paraggelies.activities.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity implements View.OnClickListener {
    private EditText username,password,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        location=findViewById(R.id.location);


        SharedPreferences productItems =getSharedPreferences("SaveShop", Context.MODE_PRIVATE);
        final String lat=productItems.getString("latitude","");
        Toast.makeText(register.this,lat,Toast.LENGTH_LONG).show();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void userSignUp(){
        String usernameText=username.getText().toString().trim();
        String passwordText=password.getText().toString().trim();
        String locationText=location.getText().toString().trim();

        if (usernameText.isEmpty()) {
            username.setError("username is required");
            username.requestFocus();
            return;
        }

        if (passwordText.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        if (locationText.isEmpty()) {
            location.setError("location is required");
            location.requestFocus();
            return;
        }


        Call<ResponseBody> call= RetrofitClient
                .getInstance()
                .getApi()
                .createUser(usernameText,passwordText,locationText);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String s=response.body().string();
                    Toast.makeText(register.this,s,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(register.this,"Register failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;
            case R.id.textViewLogin:
                openLogin();
                break;
        }
    }
    public void openLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
