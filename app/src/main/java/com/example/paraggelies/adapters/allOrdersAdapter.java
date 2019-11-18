package com.example.paraggelies.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paraggelies.R;
import com.example.paraggelies.api.RetrofitClient;
import com.example.paraggelies.models.order;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allOrdersAdapter extends RecyclerView.Adapter<allOrdersAdapter.ViewHolder> {

    private List<order> orders;
    private Context context;
    private float sum,price;
    private int quantity;

    public allOrdersAdapter(List<order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_all_orders,parent,false);
        return new allOrdersAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final order order=orders.get(position);
        holder.textViewname.setText(order.getProduct().getName());
       // holder.textViewPrice.setText(order.getProduct().getPrice());
        holder.textViewQuantity.setText(order.getQuantity());
        holder.textViewCustomerId.setText(order.getCustomerId());
        try{
            price=Float.parseFloat(order.getProduct().getPrice());
            quantity=Integer.parseInt(order.getQuantity());
            sum=price*quantity;
            holder.textViewPrice.setText(new DecimalFormat("##.##").format(sum)+" €");


        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences result=context.getSharedPreferences("SellerData", Context.MODE_PRIVATE);
                String token=result.getString("token","");

                Call call= RetrofitClient.getInstance().getApi().deleteOrder("Bearer "+token,order.getId());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                        Toast.makeText(context, "Order Succesfully Deleted.", Toast.LENGTH_SHORT).show();
                        notifyItemChanged(position);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {

        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname;
        public TextView textViewPrice;
        public TextView textViewQuantity;
        public Button deleteButton;
        public TextView textViewCustomerId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname=itemView.findViewById(R.id.allOrderProductName);
            textViewPrice= itemView.findViewById(R.id.allOrderPriceName);
            textViewQuantity=itemView.findViewById(R.id.allOrderQuantity);
            textViewCustomerId=itemView.findViewById(R.id.allOrderCustomerId);
            deleteButton=itemView.findViewById(R.id.allOrderDelete);
        }
    }
}
