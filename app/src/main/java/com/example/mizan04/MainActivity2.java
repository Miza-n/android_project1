package com.example.mizan04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private Button detailsButton;
    private Button previousButton;
    private ImageView imageView;
    private boolean isApple15 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        detailsButton = findViewById(R.id.left_btn);
        previousButton = findViewById(R.id.previous_btn);
        imageView = findViewById(R.id.imageview2);

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("iPhone 15 Details");
                builder.setMessage("Brand: Apple\nModel: iPhone 15\nDisplay: 6.1-inch OLED\nProcessor: A16 Bionic\nRAM: 6GB\nStorage: 128GB/256GB/512GB\nPrice: $999");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isApple15) {
                    imageView.setImageResource(R.drawable.iphone);
                    isApple15 = false;
                } else {
                    imageView.setImageResource(R.drawable.iphone1);
                    isApple15 = true;
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
