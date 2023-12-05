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

public class ReleaseFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_button_rel;
    MyDatabaseHelper myDB;
    ArrayList<String> rel_id, rel_customer, rel_contact, rel_date_added, rel_status;
    CustomAdapterRCV customAdapterRCV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_release, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        add_button_rel = view.findViewById(R.id.add_button_rel);

        add_button_rel.setOnClickListener(v -> openReleaseModal());

        myDB = new MyDatabaseHelper(requireContext());
        rel_id = new ArrayList<>();
        rel_customer = new ArrayList<>();
        rel_contact = new ArrayList<>();
        rel_date_added = new ArrayList<>();
        rel_status = new ArrayList<>();

        storeDataInArrays();

        customAdapterRCV = new CustomAdapterRCV(requireContext(), rel_id, rel_customer, rel_date_added, rel_contact, rel_status);
        recyclerView.setAdapter(customAdapterRCV);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllDataRCV();
        if(cursor.getCount() == 0){
            Toast.makeText(requireContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                rel_id.add(cursor.getString(0));
                rel_customer.add(cursor.getString(1));
                rel_date_added.add(cursor.getString(5));
                rel_contact.add(cursor.getString(2));

                // Get the status value
                int status = Integer.parseInt(cursor.getString(8));

                // Set the status text and color
                if (status == 0) {
                    rel_status.add("Pending");
                } else if (status == 1) {
                    rel_status.add("Completed");
                }
                // Ignore other status values
            }
        }
    }

    private void openReleaseModal() {
        ReleaseModalFragment releaseModalFragment = new ReleaseModalFragment();
        releaseModalFragment.show(getChildFragmentManager(), "ReleaseModalFragment");
    }
}
