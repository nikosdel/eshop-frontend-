package com.example.paraggelies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paraggelies.R;
import com.example.paraggelies.models.makeOrderModel;

import java.text.DecimalFormat;
import java.util.List;

public class makeOrderAdapter extends RecyclerView.Adapter<makeOrderAdapter.ViewHolder> {
    private List<makeOrderModel> makeOrderModels;
    private Context context;
    private float sum,price;
    private int quantity;

    public makeOrderAdapter(List<makeOrderModel> makeOrderModels, Context context) {
        this.makeOrderModels = makeOrderModels;
        this.context = context;
    }

    @NonNull
    @Override
    public makeOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_make_order,parent,false);
        return new makeOrderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull makeOrderAdapter.ViewHolder holder, int position) {

        holder.textViewQuantity.setText(makeOrderModels.get(position).getQuantity());
        holder.textViewproductName.setText(makeOrderModels.get(position).getProductId());
        holder.textViewProductPrice.setText(makeOrderModels.get(position).getCustomerId()+" /per Item");
        try{
            price=Float.parseFloat(makeOrderModels.get(position).getCustomerId());
            quantity=Integer.parseInt(makeOrderModels.get(position).getQuantity());
           sum=price*quantity;
            holder.textViewSumPrice.setText(new DecimalFormat("##.##").format(sum)+" €");


        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return makeOrderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewproductName;
        public TextView textViewQuantity;
        public TextView textViewProductPrice;
        public TextView textViewSumPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewproductName=itemView.findViewById(R.id.OrderProductName);
            textViewQuantity=itemView.findViewById(R.id.OrderQuantity);
            textViewProductPrice=itemView.findViewById(R.id.OrderPriceName);
            textViewSumPrice=itemView.findViewById(R.id.OrderSumPrice);

        }
    }
}
