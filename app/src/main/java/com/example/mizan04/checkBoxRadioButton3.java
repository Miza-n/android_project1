package com.example.mizan04;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import com.example.mizan04.R;

public class checkBoxRadioButton3 extends AppCompatActivity {
    private CheckBox iPhone15, iPhone14, iPhone13, iPhone12;

    ArrayList<String> arr  = new ArrayList<>();

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button increment, decrement, submitOrder, rateUs;
    private int quantity=0;
    private int price = 0;
    private TextView quantityTextView, priceTextView, devices;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_box_radio_button3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //for checkbox
        iPhone15 = findViewById(R.id.iPhone15);
        iPhone14 = findViewById(R.id.iPhone14);
        iPhone13 = findViewById(R.id.iPhone13);
        iPhone12 = findViewById(R.id.iPhone12);
        devices = findViewById(R.id.devices);

        //for RadioGroup
        radioGroup = findViewById(R.id.radioGroup);

        //for increment and decrement
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);

        submitOrder = findViewById(R.id.submitOrder);
        rateUs = findViewById(R.id.rateUs);
        builder = new AlertDialog.Builder(this);

        iPhone15.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        iPhone14.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        iPhone13.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        iPhone12.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });

        increment.setOnClickListener(v -> {
            quantity++;
            price = quantity * 1000;
            quantityTextView.setText("" + quantity);
            priceTextView.setText("BDT: " + price);
        });
        decrement.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                price = quantity * 1000;
                quantityTextView.setText("" + quantity);
                priceTextView.setText("BDT: " + price);
            } else{
                Toast.makeText(this, "Quantity cannot be negative", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
            }
        });

        submitOrder.setOnClickListener(v -> {
            try{
                if(arr.isEmpty()){
                    Toast.makeText(this, "Please select at least one device", Toast.LENGTH_SHORT).show();
                }
                String radioValue = radioButton.getText().toString();
                if(quantity==0){
                    Toast.makeText(this, "Please select quantity", Toast.LENGTH_SHORT).show();
                }
                else{
                    builder.setTitle("Order Placed!!")
                            .setMessage("Order Summary:\n" + "Device: "+ arr + "\nDevice Color: " + radioValue + "\nQuantity: " + quantity + "\nTotal Price: BDT " + price + "\nThank You!!")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Order Placed!", Toast.LENGTH_SHORT).show();
                                    quantity = 0;
                                    price = 0;
                                    quantityTextView.setText("0");
                                    priceTextView.setText("BDT 0");
                                    devices.setText("");
                                    iPhone15.setChecked(false);
                                    iPhone14.setChecked(false);
                                    iPhone13.setChecked(false);
                                    iPhone12.setChecked(false);
                                    radioGroup.clearCheck();

                                }
                            }).show();
                }
            } catch (Exception e){
                Toast.makeText(this, "Please select T-shirt color", Toast.LENGTH_SHORT).show();
            }
        });

        rateUs.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Rating.class);
            startActivity(intent);
        });
    }

    void check(CompoundButton buttonView, boolean isChecked){
        if(isChecked){
            arr.add(buttonView.getText().toString());
            Log.d("array",String.valueOf(arr));
        } else{
            arr.remove(buttonView.getText().toString());
        }
        devices.setText(String.valueOf(arr));
    }
}