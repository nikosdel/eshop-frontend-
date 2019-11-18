package com.example.paraggelies.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paraggelies.R;
import com.example.paraggelies.activities.customer.productItem;
import com.example.paraggelies.models.productModel;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<productModel> productModelList;
    private Context context;
    private List<String> productsId=new ArrayList<>();

    public myAdapter(List<productModel> productModelList, Context context) {
        this.productModelList = productModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_product_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final productModel productModel=productModelList.get(position);
        holder.textViewname.setText("Προιόν: "+productModel.getName());
        holder.textViewprice.setText("Τιμή: "+productModel.getPrice()+" €");
        Glide.with(context).load(productModel.getProductImage()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, productItem.class);
                intent.putExtra("productName",productModel.getName());
                intent.putExtra("productPrice",productModel.getPrice());
                intent.putExtra("productImage",productModel.getProductImage());
                intent.putExtra("productId",productModel.getId());
                context.startActivity(intent);



            }

        });
//
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewname;
        public TextView textViewprice;
        public ImageView imageView;
        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname=(TextView)itemView.findViewById(R.id.nameProduct);
            textViewprice=(TextView) itemView.findViewById(R.id.priceProduct);
            imageView=(ImageView) itemView.findViewById(R.id.img);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);

        }
    }

}
