package com.example.paraggelies.activities.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paraggelies.R;

public class productItem extends AppCompatActivity {
    TextView textProductName;
    TextView textProductPrice;
    ImageView textProductImage;
    EditText editTextQuantity;
    Button addButton;
    private SharedPreferences sharedPreferences;

    String idproducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item);
        textProductName=findViewById(R.id.singleProductName);
        textProductPrice=findViewById(R.id.singleProductPrice);
        textProductImage=findViewById(R.id.singleProductImage);
        editTextQuantity=findViewById(R.id.singleProductQuantity);
        addButton=findViewById(R.id.addProductButton);
        final String productName=getIntent().getStringExtra("productName");
        final String productPrice=getIntent().getStringExtra("productPrice");
        String productImage=getIntent().getStringExtra("productImage");
        final String productId=getIntent().getStringExtra("productId");




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences=getSharedPreferences("productsForOrder", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("productId",productId);
                editor.putString("productName",productName);
                editor.putString("productPrice",productPrice);
                editor.putString("productQuantity",editTextQuantity.getText().toString());
                editor.apply();
                makeOrder();

            }
        });

        textProductPrice.setText(productPrice);
        textProductName.setText(productName);
        Glide.with(this)
                .asBitmap()
                .load(productImage)
                .into(textProductImage);

    }

    public void makeOrder(){
        Intent intent = new Intent(this, makeOrder.class);
        startActivity(intent);
    }


}
