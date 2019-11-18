package com.example.paraggelies.activities.seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.paraggelies.R;
import com.example.paraggelies.activities.MainActivity;

public class sellerMenu extends AppCompatActivity implements View.OnClickListener {

    private CardView allordersCardview;
    private CardView logoutCardview;
    private CardView createProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_menu);

        allordersCardview=findViewById(R.id.allOrderCardview);
        logoutCardview=findViewById(R.id.logoutSeller);
        logoutCardview.setOnClickListener(this);
        allordersCardview.setOnClickListener(this);
        createProduct =findViewById(R.id.CreateProduct);
        createProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.allOrderCardview: i=new Intent(this, allOrders.class);
            startActivity(i);
            break;
            case R.id.logoutSeller:i=new Intent(this, MainActivity.class);
                startActivity(i);
            break;
            case R.id.CreateProduct:i=new Intent(this,addProduct.class);
            startActivity(i);
            break;
        }
    }
}
