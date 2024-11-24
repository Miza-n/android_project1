package com.example.mizan04;

import android.graphics.Color;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpandableListViewActivity extends AppCompatActivity {

    ListView listView;
    String[] mangoNames = {"Himsagar", "Langra", "Fazli", "Alphonso", "Amrapali", "Totapuri", "Kesar", "Sindhri", "Dasheri", "Chaunsa"};
    String[] mangoDescriptions = {
            "Himsagar: Known for its sweetness and pulp.",
            "Langra: A popular variety from Northern India.",
            "Fazli: Large-sized mango with a unique flavor.",
            "Alphonso: King of mangoes with rich flavor.",
            "Amrapali: A hybrid mango with a tangy taste.",
            "Totapuri: Famous for its distinctive shape.",
            "Kesar: Known for its bright orange color.",
            "Sindhri: A juicy mango from Sindh, Pakistan.",
            "Dasheri: A traditional favorite with a sweet taste.",
            "Chaunsa: Renowned for its aromatic flavor."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        listView = findViewById(R.id.listView);
        MangoAdapter adapter = new MangoAdapter();
        listView.setAdapter(adapter);
    }

    class MangoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mangoNames.length;
        }

        @Override
        public Object getItem(int position) {
            return mangoNames[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(ExpandableListViewActivity.this);
                convertView = inflater.inflate(R.layout.activity_expendable_item, parent, false);
            }

            LinearLayout motherLayout = convertView.findViewById(R.id.motherLayout);
            TextView mangoName = convertView.findViewById(R.id.mangoName);
            TextView mangoDescription = convertView.findViewById(R.id.mangoDescription);
            ImageView arrowImg = convertView.findViewById(R.id.arrowImg);

            mangoName.setText(mangoNames[position]);
            mangoDescription.setText(mangoDescriptions[position]);

            // Handle expansion/collapse
            convertView.setOnClickListener(v -> {
                if (mangoDescription.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(motherLayout, new AutoTransition());
                    mangoDescription.setVisibility(View.VISIBLE);
                    arrowImg.setImageResource(R.drawable.ic_up_arrow);
                    motherLayout.setBackgroundColor(Color.parseColor("#E0F7FA"));
                } else {
                    TransitionManager.beginDelayedTransition(motherLayout, new AutoTransition());
                    mangoDescription.setVisibility(View.GONE);
                    arrowImg.setImageResource(R.drawable.ic_down_arrow);
                    motherLayout.setBackgroundColor(Color.WHITE);
                }
            });

            return convertView;
        }
    }
}
