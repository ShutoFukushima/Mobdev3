package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    EditText prod_name_field, prod_category_field, prod_description_field, prod_price_field, prod_qty_field;
    Button add_button_prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product_add);

        prod_name_field = findViewById(R.id.prod_name_field);
        prod_category_field = findViewById(R.id.prod_category_field);
        prod_description_field = findViewById(R.id.prod_description_field);
        prod_price_field = findViewById(R.id.prod_price_field);
        prod_qty_field = findViewById(R.id.prod_qty_field);
        add_button_prod = findViewById(R.id.add_button_prod);

        // Set the onClickListener for the button
        add_button_prod.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Get the values from TextInputEditText fields
        String name = prod_name_field.getText().toString();
        String category = prod_category_field.getText().toString();
        String description = prod_description_field.getText().toString();
        String price = prod_price_field.getText().toString();
        String qty = prod_qty_field.getText().toString();

        // Check if any of the fields are empty
        if (name.isEmpty() || category.isEmpty() || description.isEmpty() || price.isEmpty() || qty.isEmpty()){
            // Show an error message or handle the case of empty fields
            // For example, you can display a Toast
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Call the AddSupplier method to insert data into the database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        dbHelper.AddProduct(name, category, description, price, qty);
    }
}
