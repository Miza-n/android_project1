package com.example.mizan04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button detailsButton;
    private Button nextButton;
    private ImageView laptopImageView;
    private boolean isNewLaptop = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsButton = findViewById(R.id.left_btn);
        nextButton = findViewById(R.id.next_btn);
        laptopImageView = findViewById(R.id.imageview);

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Apple Laptop Details");
                builder.setMessage("Brand: Apple\nModel: MacBook Pro\nDisplay: 13.3-inch Retina\nProcessor: M1 Chip\nRAM: 16GB\nStorage: 512GB SSD\nPrice: $1,299");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        laptopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewLaptop) {

                    laptopImageView.setImageResource(R.drawable.laptop);
                    isNewLaptop = false;
                } else {

                    laptopImageView.setImageResource(R.drawable.laptop2);
                    isNewLaptop = true;
                }
            }
        });
    }
}
