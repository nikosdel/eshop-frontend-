package com.example.paraggelies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customerSettings extends AppCompatActivity {
    EditText textViewUsername,textViewLocation;
    TextView textViewId;
    Button  upadateCustomerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        SharedPreferences userData=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        final String token=userData.getString("token","");
        final String location=userData.getString("location","");
        final String id=userData.getString("Customerid","");
        final String username=userData.getString("username","");

        textViewId=findViewById(R.id.customerId);
        textViewLocation=findViewById(R.id.customerLocation);
        textViewUsername=findViewById(R.id.customerName);
        textViewId.setText(id);
        textViewUsername.setText(username);
        textViewLocation.setText(location);
        upadateCustomerBtn=findViewById(R.id.SettingcustomerBtn);

        upadateCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(customerSettings.this,textViewUsername.getText().toString(),Toast.LENGTH_LONG).show();
                Call<ResponseBody> call= RetrofitClient.getInstance().getApi().updateCustomer("Bearer "+token,id,textViewUsername.getText().toString(),textViewLocation.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(customerSettings.this,response.code(),Toast.LENGTH_LONG).show();
                            }
                        try {
                            String s=response.body().string();
                            Toast.makeText(customerSettings.this,s,Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(customerSettings.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        return;
                    }
                });
            }
        });




    }


}
