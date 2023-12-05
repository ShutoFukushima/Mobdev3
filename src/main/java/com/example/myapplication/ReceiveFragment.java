package com.example.myapplication;

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

public class ReceiveFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_button_rec;
    MyDatabaseHelper myDB;
    ArrayList<String> rec_id, rec_supplier, rec_contact, rec_date_added, rec_status;
    CustomAdapterRCV customAdapterRCV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receive, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        add_button_rec = view.findViewById(R.id.add_button_rec);

        add_button_rec.setOnClickListener(v -> openReceiveModal());

        myDB = new MyDatabaseHelper(requireContext());
        rec_id = new ArrayList<>();
        rec_supplier = new ArrayList<>();
        rec_contact = new ArrayList<>();
        rec_date_added = new ArrayList<>();
        rec_status = new ArrayList<>();

        storeDataInArraysRCV();

        customAdapterRCV = new CustomAdapterRCV(requireContext(), rec_id, rec_supplier, rec_date_added, rec_contact, rec_status);
        recyclerView.setAdapter(customAdapterRCV);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    void storeDataInArraysRCV(){
        Cursor cursor = myDB.readAllDataRCV();
        if(cursor.getCount() == 0){
            Toast.makeText(requireContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                rec_id.add(cursor.getString(0));
                rec_supplier.add(cursor.getString(1));
                rec_date_added.add(cursor.getString(5));
                rec_contact.add(cursor.getString(2));

                // Get the status value
                int status = Integer.parseInt(cursor.getString(8));

                // Set the status text and color
                if (status == 0) {
                    rec_status.add("Pending");
                } else if (status == 1) {
                    rec_status.add("Completed");
                }
                // Ignore other status values
            }
        }
    }

    private void openReceiveModal() {
        ReceiveModalFragment receiveModalFragment = new ReceiveModalFragment();
        receiveModalFragment.show(getChildFragmentManager(), "ReceiveModalFragment");
    }
}
