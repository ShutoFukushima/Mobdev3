package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceiveUpdateActivity extends AppCompatActivity {

    EditText rec_supplier_input, rec_contact_input, rec_date_input, rec_status_input;
    Button update_button_rec;

    String rec_id, rec_supplier, rec_contact,rec_date_added, rec_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_rec);

        rec_supplier_input = findViewById(R.id.rec_supplier_input);
        rec_contact_input = findViewById(R.id.rec_contact_input);
        rec_date_input = findViewById(R.id.rec_date_input);
        rec_status_input = findViewById(R.id.rec_status_input);
        update_button_rec = findViewById(R.id.update_button_rec);
        update_button_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the update logic here
            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("supplier") &&
                getIntent().hasExtra("contact") && getIntent().hasExtra("status") &&
                getIntent().hasExtra("date")) {
            rec_id = getIntent().getStringExtra("id");
            rec_supplier = getIntent().getStringExtra("supplier");
            rec_contact = getIntent().getStringExtra("contact");
            rec_date_added = getIntent().getStringExtra("date");
            rec_status = getIntent().getStringExtra("status");

            rec_supplier_input.setText(rec_supplier);
            rec_contact_input.setText(rec_contact);
            rec_status_input.setText(rec_status);
            rec_date_input.setText(rec_date_added);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
