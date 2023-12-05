package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterProd extends RecyclerView.Adapter<CustomAdapterProd.MyViewHolder> {
    private Context context;
    private ArrayList<String> prod_id, prod_name, prod_category, prod_description, prod_price, prod_qty;

    CustomAdapterProd(Context context, ArrayList<String> prod_id, ArrayList<String> prod_name, ArrayList<String> prod_category, ArrayList<String> prod_description, ArrayList<String> prod_price, ArrayList<String> prod_qty) {
        this.context = context;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_category = prod_category;
        this.prod_description = prod_description;
        this.prod_price = prod_price;
        this.prod_qty = prod_qty;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.prod_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.prod_id_txt.setText(String.valueOf(prod_id.get(position)));
        holder.prod_name_txt.setText(String.valueOf(prod_name.get(position)));
        holder.prod_category_txt.setText(String.valueOf(prod_category.get(position)));
        holder.prod_description_txt.setText(String.valueOf(prod_description.get(position)));
        holder.prod_price_txt.setText(String.valueOf(prod_price.get(position)));
        holder.prod_qty_txt.setText(String.valueOf(prod_qty.get(position)));
    }

    @Override
    public int getItemCount() {
        return prod_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prod_id_txt, prod_name_txt, prod_category_txt, prod_description_txt, prod_price_txt, prod_qty_txt;
        LinearLayout prod_row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_id_txt = itemView.findViewById(R.id.prod_id_txt);
            prod_name_txt = itemView.findViewById(R.id.prod_name_txt);
            prod_category_txt = itemView.findViewById(R.id.prod_category_txt);
            prod_description_txt = itemView.findViewById(R.id.prod_description_txt);
            prod_price_txt = itemView.findViewById(R.id.prod_price_txt);
            prod_qty_txt = itemView.findViewById(R.id.prod_qty_txt);

            prod_row = itemView.findViewById(R.id.prod_row);
        }
    }
}
