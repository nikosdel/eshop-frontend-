package com.example.paraggelies.activities.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.models.sellerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sellerLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText username,password;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        username=findViewById(R.id.usernameSeller);
        password=findViewById(R.id.passwordSeller);

        findViewById(R.id.buttonLoginSeller).setOnClickListener(this);
    }

    private void loginSeller(){
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

        Call<sellerResponse> call= RetrofitClient.getInstance().getApi().loginSeller(textUsername,textPassword);
        call.enqueue(new Callback<sellerResponse>() {
            @Override
            public void onResponse(Call<sellerResponse> call, Response<sellerResponse> response) {

                sellerResponse sellerResponse=response.body();
                sharedPreferences=getSharedPreferences("SellerData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("token",sellerResponse.getSeller().getToken());
                editor.putString("Customerid",sellerResponse.getSeller().getId());
                editor.putString("username",sellerResponse.getSeller().getUsername());
                editor.apply();
                menu();
            }

            @Override
            public void onFailure(Call<sellerResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonLoginSeller:
                loginSeller();
                break;}

    }
    public void menu(){
        Intent intent = new Intent(this, sellerMenu.class);
        startActivity(intent);
    }
}
