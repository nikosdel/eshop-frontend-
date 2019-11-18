package com.example.paraggelies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.paraggelies.R;
import com.example.paraggelies.activities.customer.login;
import com.example.paraggelies.activities.seller.sellerLogin;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
   private Button sellerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sellerBtn=findViewById(R.id.buttonSeller);
        sellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSellerLogin();
            }
        });

        loginBtn =  findViewById(R.id.buttonToLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });


    }


    public void openLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void openSellerLogin() {
        Intent intent = new Intent(this, sellerLogin.class);
        startActivity(intent);
    }
}
