package com.example.paraggelies.activities.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.adapters.makeOrderAdapter;
import com.example.paraggelies.models.makeOrderModel;
import com.example.paraggelies.models.makeOrderResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class makeOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Button makeOrderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        SharedPreferences productItems =getSharedPreferences("productsForOrder", Context.MODE_PRIVATE);
        final String productId=productItems.getString("productId","");
        String productName=productItems.getString("productName","");
        String productPrice=productItems.getString("productPrice","");
        final String productQuantity=productItems.getString("productQuantity","");


        SharedPreferences userData=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        final String token=userData.getString("token","");
         final String id=userData.getString("Customerid","");


        ArrayList<makeOrderModel> makeOrders=new ArrayList<>();
        makeOrders.add(new makeOrderModel(productQuantity,productName,productPrice));


        recyclerView=(RecyclerView) findViewById(R.id.makeOrderRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new makeOrderAdapter(makeOrders,getApplicationContext());
        recyclerView.setAdapter(adapter);

        makeOrderBtn=findViewById(R.id.makeOrderBtn);
        makeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dToast.makeText(makeOrder.this, id, Toast.LENGTH_SHORT).show();
               makeOrderComplete("Bearer "+token,productQuantity,productId,id);
            }
        });


    }
    private void makeOrderComplete(String token,String quantity,String productsId,String customerId){
        Call<makeOrderResponse> call= RetrofitClient.getInstance().getApi().makeOrder(token,quantity,productsId,customerId);
        call.enqueue(new Callback<makeOrderResponse>() {
            @Override
            public void onResponse(Call<makeOrderResponse> call, Response<makeOrderResponse> response) {
                Toast.makeText(makeOrder.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                products();
            }

            @Override
            public void onFailure(Call<makeOrderResponse> call, Throwable t) {

            }
        });

    }

    public void products(){
        Intent intent = new Intent(this, products.class);
        startActivity(intent);
    }

}
