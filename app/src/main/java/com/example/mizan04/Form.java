package com.example.mizan04;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.regex.Pattern;

public class Form extends AppCompatActivity {

    // UI Elements
    private EditText etName, etId, etEmail, etMobile, etPassword;
    private Button btnSubmit, btnGoBack;
    private Spinner deptSpinner;
    private LinearLayout inputLayout, outputLayout;
    private TextView tvOutputText;

    // Data Variables
    private String name, id, email, mobile, dept, password;
    private final String[] departments = {"Select Department", "CSE", "EEE", "ARCH", "CE", "BuA", "ENG", "LAW", "IS", "BNG", "THM", "PH"};

    // Validation Patterns
    private final Pattern namePattern = Pattern.compile("[a-zA-Z. ]+");
    private final Pattern idPattern = Pattern.compile("\\d{16}");
    private final Pattern emailPattern = Pattern.compile("(cse|eee|arch|ce|bua|eng|law|is|bng|thm|ph)_[0-9]{15}@lus.ac.bd");
    private final Pattern mobilePattern = Pattern.compile("01[3-9]\\d{8}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Initialize Views
        inputLayout = findViewById(R.id.inputlayout);
        outputLayout = findViewById(R.id.outputlayout);

        etName = findViewById(R.id.name);
        etId = findViewById(R.id.id);
        etEmail = findViewById(R.id.email);
        etMobile = findViewById(R.id.mobile);
        etPassword = findViewById(R.id.password);

        tvOutputText = findViewById(R.id.outputText);

        btnSubmit = findViewById(R.id.submit_button);
        btnGoBack = findViewById(R.id.go_back_button);

        deptSpinner = findViewById(R.id.spinner);

        // Configure Spinner
        deptSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = departments[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dept = departments[0];
            }
        });

        // Handle Submit Button Click
        btnSubmit.setOnClickListener(v -> validateAndSubmitForm());

        // Handle Go Back Button Click
        btnGoBack.setOnClickListener(v -> resetForm());
    }

    // Method to validate and process form inputs
    private void validateAndSubmitForm() {
        // Retrieve Input Values
        name = etName.getText().toString().trim();
        id = etId.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        mobile = etMobile.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        // Validation Checks
        if (name.isEmpty()) {
            etName.setError("Name is required!");
            etName.requestFocus();
        } else if (!namePattern.matcher(name).matches()) {
            etName.setError("Name can only contain letters and spaces!");
            etName.requestFocus();
        } else if (id.isEmpty()) {
            etId.setError("Student ID is required!");
            etId.requestFocus();
        } else if (!idPattern.matcher(id).matches()) {
            etId.setError("Student ID must be a 16-digit number!");
            etId.requestFocus();
        } else if (email.isEmpty()) {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
        } else if (!emailPattern.matcher(email).matches()) {
            etEmail.setError("Invalid email! Use your LUS student email.");
            etEmail.requestFocus();
        } else if (mobile.isEmpty()) {
            etMobile.setError("Mobile number is required!");
            etMobile.requestFocus();
        } else if (!mobilePattern.matcher(mobile).matches()) {
            etMobile.setError("Invalid mobile number! Must start with 013-019.");
            etMobile.requestFocus();
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
        } else if (Objects.equals(dept, "Select Department")) {
            Toast.makeText(this, "Please select a department!", Toast.LENGTH_SHORT).show();
        } else {
            // Display Output
            inputLayout.setVisibility(View.GONE);
            outputLayout.setVisibility(View.VISIBLE);

            String output = String.format(
                    "Name: %s\nStudent ID: %s\nEmail: %s\nMobile: %s\nDepartment: %s",
                    name, id, email, mobile, dept
            );
            tvOutputText.setText(output);
        }
    }

    // Method to reset the form
    private void resetForm() {
        inputLayout.setVisibility(View.VISIBLE);
        outputLayout.setVisibility(View.GONE);

        etName.setText("");
        etId.setText("");
        etEmail.setText("");
        etMobile.setText("");
        etPassword.setText("");
        deptSpinner.setSelection(0);
    }
}
