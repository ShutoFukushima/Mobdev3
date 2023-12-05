package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class ProductFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_button_prod;
    MyDatabaseHelper myDB;
    ArrayList<String> prod_id, prod_name, prod_category, prod_description, prod_price, prod_qty;
    CustomAdapterProd customAdapterProd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_prod);
        add_button_prod = view.findViewById(R.id.add_button_prod);
        add_button_prod.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), AddProductActivity.class);
            startActivity(intent);
        });

        myDB = new MyDatabaseHelper(requireContext());
        prod_id = new ArrayList<>();
        prod_category = new ArrayList<>();
        prod_description = new ArrayList<>();
        prod_price = new ArrayList<>();
        prod_qty = new ArrayList<>();

        storeDataInArraysProd();

        customAdapterProd = new CustomAdapterProd(requireContext(), prod_id, prod_name, prod_category, prod_description, prod_price, prod_qty);
        recyclerView.setAdapter(customAdapterProd);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    void storeDataInArraysProd() {
        Cursor cursor = myDB.readAllDataPROD();
        if (cursor.getCount() == 0) {
            Toast.makeText(requireContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                prod_id.add(cursor.getString(0));
                prod_name.add(cursor.getString(1));
                prod_category.add(cursor.getString(2));
                prod_description.add(cursor.getString(3));
                prod_price.add(cursor.getString(4));
                prod_qty.add(cursor.getString(6));
            }
        }
    }
}


