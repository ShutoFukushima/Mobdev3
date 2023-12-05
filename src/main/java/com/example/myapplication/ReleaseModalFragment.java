package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;
import java.util.Locale;

public class ReleaseModalFragment extends DialogFragment {

    TextInputEditText relmField1, relmField3, relmField2;
    TextInputLayout textInputLayoutDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        // Inflate the custom layout for the dialog
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_relmodal, null);

        // Find your TextInputEditText and TextInputLayout
        relmField1 = dialogView.findViewById(R.id.relm_field1);
        relmField3 = dialogView.findViewById(R.id.relm_field3);
        relmField2 = dialogView.findViewById(R.id.relm_field2);
        textInputLayoutDate = dialogView.findViewById(R.id.textInputLayoutDate);

        // Set up the dateEditText as you did before
        relmField2.setInputType(InputType.TYPE_NULL);
        relmField2.setFocusable(false);
        relmField2.setOnClickListener(v -> showDatePickerDialog(relmField2));

        builder.setView(dialogView)
                .setTitle("Customer Details")
                .setPositiveButton(android.R.string.ok, null) // Set positive button to null initially
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    // Handle the Cancel button click here
                });

        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Set up a listener for the positive button click
        alertDialog.setOnShowListener(dialog -> {
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the values from TextInputEditText fields
                    String supplier = relmField1.getText().toString();
                    String contact = relmField3.getText().toString();
                    String date = relmField2.getText().toString();

                    // Check if any of the fields are empty
                    if (supplier.isEmpty() || contact.isEmpty() || date.isEmpty()) {
                        // Show an error message or handle the case of empty fields
                        // For example, you can display a Toast
                        Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Call the AddSupplier method to insert data into the database
                    MyDatabaseHelper dbHelper = new MyDatabaseHelper(requireContext());
                    dbHelper.AddSupplier(supplier, contact, date);

                    // Dismiss the dialog if everything is fine
                    alertDialog.dismiss();
                }
            });
        });

        return alertDialog;
    }

    public void showDatePickerDialog(final TextInputEditText dateEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireActivity(),
                (view, year1, month1, dayOfMonth) -> {
                    // Handle the selected date
                    String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, month1 + 1, dayOfMonth);
                    relmField2.setText(selectedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }
}
