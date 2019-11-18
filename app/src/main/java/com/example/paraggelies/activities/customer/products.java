package com.example.paraggelies.activities.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.models.productModel;
import com.example.paraggelies.models.productsResponse;
import com.example.paraggelies.adapters.myAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class products extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        SharedPreferences result=getSharedPreferences("SaveData",Context.MODE_PRIVATE);
        String token=result.getString("token","");
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));



        loadProducts("Bearer "+token);

    }
    private void loadProducts(String token){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data");
        progressDialog.show();
        Call<productsResponse> call=RetrofitClient.getInstance().getApi().getProducts(token);
       call.enqueue(new Callback<productsResponse>() {
           @Override
           public void onResponse(Call<productsResponse> call, Response<productsResponse> response) {
               List<productModel> productModels = new ArrayList<>();
                progressDialog.dismiss();
               productsResponse productModel=response.body();
               for(int i=0;i<response.body().getProductModel().size();i++){
                   productModels.add(productModel.getProductModel().get(i));
               }


                adapter=new myAdapter(productModels,getApplicationContext());
                recyclerView.setAdapter(adapter);


           }

           @Override
           public void onFailure(Call<productsResponse> call, Throwable t) {

           }
       });
    }

    @Override
    public void onBackPressed() {
        menu();
    }
    public void menu(){
        Intent intent = new Intent(this, customerMenu.class);
        startActivity(intent);
    }
}

