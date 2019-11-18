package com.example.paraggelies.activities.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.models.loginRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText username,password;
    private TextView textViewRegister;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);





    }

    private void userLogin(){
        String textUsername,textPassword;

        textUsername=username.getText().toString().trim();
        textPassword=password.getText().toString().trim();

        if (textPassword.isEmpty()) {
            username.setError("username is required");
            username.requestFocus();
            return;
        }

        if (textUsername.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        Call<loginRespone> call= RetrofitClient
                .getInstance().getApi().loginUser(textUsername,textPassword);

        call.enqueue(new Callback<loginRespone>() {
            @Override
            public void onResponse(Call<loginRespone> call, Response<loginRespone> response) {
                loginRespone loginRespone=response.body();

                sharedPreferences=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("token",loginRespone.getCustomer().getToken());
                editor.putString("Customerid",loginRespone.getCustomer().getId());
                editor.putString("location",loginRespone.getCustomer().getLocation());
                editor.putString("username",loginRespone.getCustomer().getUsername());
                editor.apply();
                menu();
            }

            @Override
            public void onFailure(Call<loginRespone> call, Throwable t) {
                Toast.makeText(login.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewRegister:
                registerForm();
                break;
        }
    }

    public void menu(){
        Intent intent = new Intent(this, customerMenu.class);
        startActivity(intent);
    }
    public void registerForm(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

}
