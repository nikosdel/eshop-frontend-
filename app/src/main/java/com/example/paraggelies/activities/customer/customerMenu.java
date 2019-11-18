package com.example.paraggelies.activities.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.paraggelies.R;
import com.example.paraggelies.activities.MainActivity;
import com.example.paraggelies.activities.contact;
import com.example.paraggelies.activities.customerSettings;
import com.example.paraggelies.map;

public class customerMenu extends AppCompatActivity implements View.OnClickListener {
    private CardView productCardview;
    private CardView orderCardview;
    private CardView logoutCardview;
    private CardView makeOrderCardview;
    private CardView customerSettings;
    private CardView contactCardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        productCardview=findViewById(R.id.products);
        productCardview.setOnClickListener(this);
        makeOrderCardview=findViewById(R.id.makeOrder);
        makeOrderCardview.setOnClickListener(this);
        orderCardview=findViewById(R.id.orders);
        orderCardview.setOnClickListener(this);
        logoutCardview=findViewById(R.id.logout);
        logoutCardview.setOnClickListener(this);
        customerSettings=findViewById(R.id.customerSettings);
        customerSettings.setOnClickListener(this);
        contactCardview=findViewById(R.id.contact);
        contactCardview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.products:i =new Intent(this,products.class);
            startActivity(i);
            break;
            case R.id.orders:i=new Intent(this,orderHistoryOrder.class);
                startActivity(i);
                break;
            case R.id.logout:i=new Intent(this, MainActivity.class);
            startActivity(i);
            break;
            case R.id.makeOrder:i=new Intent(this,makeOrder.class);
            startActivity(i);
            break;
            case R.id.customerSettings:i=new Intent(this, com.example.paraggelies.activities.customerSettings.class);
            startActivity(i);
            break;
            case R.id.contact:i=new Intent(this, contact.class);
            startActivity(i);
        }
    }
}
