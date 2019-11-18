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
import com.example.paraggelies.models.order;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<order> orders;
    private Context context;
    private float sum,price;
    private int quantity;

    public OrderAdapter(List<order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_order_history_user,parent,false);
        return new OrderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final order order=orders.get(position);
        holder.textViewname.setText(order.getProduct().getName());
        holder.textViewPrice.setText(order.getProduct().getPrice());
        holder.textViewQuantity.setText(order.getQuantity());
        try{
            price=Float.parseFloat(order.getProduct().getPrice());
            quantity=Integer.parseInt(order.getQuantity());
            sum=price*quantity;

            holder.textViewSum.setText(new DecimalFormat("##.##").format(sum)+ " €");


        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewname;
        public TextView textViewQuantity;
        public TextView textViewPrice;
        public TextView textViewSum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname=itemView.findViewById(R.id.OrderProductName);
            textViewPrice= itemView.findViewById(R.id.OrderPriceName);
            textViewQuantity=itemView.findViewById(R.id.OrderQuantity);
            textViewSum=itemView.findViewById(R.id.OrderUserSumPrice);
        }
    }
}
