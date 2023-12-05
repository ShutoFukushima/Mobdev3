package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterRCV extends RecyclerView.Adapter<CustomAdapterRCV.MyViewHolder> {
    private Context context;
    private ArrayList<String> rec_id, rec_supplier, rec_date_added, rec_contact, rec_status;

    CustomAdapterRCV(Context context, ArrayList<String> rec_id, ArrayList<String> rec_supplier, ArrayList<String> rec_date_added, ArrayList<String> rec_contact, ArrayList<String> rec_status) {
        this.context = context;
        this.rec_id = rec_id;
        this.rec_supplier = rec_supplier;
        this.rec_date_added = rec_date_added;
        this.rec_contact = rec_contact;
        this.rec_status = rec_status;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rec_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.rec_id_txt.setText(String.valueOf(rec_id.get(position)));
        holder.rec_supplier_txt.setText(String.valueOf(rec_supplier.get(position)));
        holder.rec_date_txt.setText(String.valueOf(rec_date_added.get(position)));
        holder.rec_status_txt.setText(String.valueOf(rec_status.get(position)));
        holder.rec_contact_txt.setText(String.valueOf(rec_contact.get(position)));
        holder.rec_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReceiveUpdateActivity.class);
                intent.putExtra("id", String.valueOf(rec_id.get(position)));
                intent.putExtra("supplier", String.valueOf(rec_supplier.get(position)));
                intent.putExtra("contact", String.valueOf(rec_contact.get(position)));
                intent.putExtra("date", String.valueOf(rec_date_added.get(position)));
                intent.putExtra("status", String.valueOf(rec_status.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rec_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rec_id_txt, rec_supplier_txt, rec_date_txt, rec_status_txt, rec_contact_txt;
        LinearLayout rec_row;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_id_txt = itemView.findViewById(R.id.rec_id_txt);
            rec_supplier_txt = itemView.findViewById(R.id.rec_supplier_txt);
            rec_date_txt = itemView.findViewById(R.id.rec_date_txt);
            rec_status_txt = itemView.findViewById(R.id.rec_status_txt);
            rec_contact_txt = itemView.findViewById(R.id.rec_contact_txt);
            rec_row = itemView.findViewById(R.id.rec_row);
        }
    }
}
