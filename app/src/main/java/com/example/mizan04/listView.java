package com.example.mizan04;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class listView extends AppCompatActivity {

    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        myListView = findViewById(R.id.listView);

        // List of different kinds of mangoes
        ArrayList<String> mangoList = new ArrayList<>();
        mangoList.add("Himsagar Mango");
        mangoList.add("Deshi Mango");
        mangoList.add("Dasheri Mango");
        mangoList.add("Langra Mango");
        mangoList.add("Totapuri Mango");
        mangoList.add("Kesar Mango");
        mangoList.add("Amrapali Mango");
        mangoList.add("Neelum Mango");
        mangoList.add("Raspuri Mango");
        mangoList.add("Banganapalli Mango");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mangoList);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedMango = ((TextView) view).getText().toString();
                Toast.makeText(listView.this, "You selected: " + selectedMango, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
