package com.example.paraggelies.activities.seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.paraggelies.adapters.OrderAdapter;
import com.example.paraggelies.R;
import com.example.paraggelies.adapters.allOrdersAdapter;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.models.order;
import com.example.paraggelies.models.orderResponse;
import com.example.paraggelies.models.productModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allOrders extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        SharedPreferences result=getSharedPreferences("SellerData", Context.MODE_PRIVATE);
        String token=result.getString("token","");

        recyclerView=(RecyclerView) findViewById(R.id.allOrdersRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadAllOrders("Bearer "+token);
    }

    private void loadAllOrders(String token){
        Call<orderResponse> call= RetrofitClient.getInstance().getApi().getAllorders(token);
        call.enqueue(new Callback<orderResponse>() {
            @Override
            public void onResponse(Call<orderResponse> call, Response<orderResponse> response) {
                List<order> orders=new ArrayList<>();
                List<productModel> productModels=new ArrayList<>();
                orderResponse orderResponse= response.body();

                for(int i=0;i<response.body().getOrder().size();i++){
                    orders.add(orderResponse.getOrder().get(i));
                }

                adapter=new allOrdersAdapter(orders,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<orderResponse> call, Throwable t) {
                Toast.makeText(allOrders.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
