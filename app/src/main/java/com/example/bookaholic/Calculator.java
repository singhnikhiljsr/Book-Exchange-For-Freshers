package com.example.bookaholic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {

    ListView listView;
    String[] bookName = {"FX-991EX", "FX-991ES PLUS", "FX-991ES", "FX-991MS"};
    String[] author = {"Brand New", "One Year Used", "Two Year Used", "One Year Used"};
    String[] edition = {"Condition: Good", "Condition: Average", "Condition: Low Display Light ", "Condition: Good"};
    int[] images = {R.drawable.fx_991ex, R.drawable.fx_991es_plus, R.drawable.fx_991es, R.drawable.fx_991ms};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        listView = findViewById(R.id.listView_calculators);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PassObject object = new PassObject(images[i], bookName[i], author[i], edition[i]);
                Intent intent = new Intent(Calculator.this, BookPurchased.class);
                intent.putExtra("Object", object);
                intent.putExtra("item", "Calculator");
                startActivity(intent);

            }

        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.custom_list_view, null);
            ImageView bookImage = view.findViewById(R.id.bookImage);
            TextView bookTitle = view.findViewById(R.id.bookTitle);
            TextView bookAuthor = view.findViewById(R.id.bookAuthor);
            TextView bookEdition = view.findViewById(R.id.bookEdition);

            bookImage.setImageResource(images[i]);
            bookTitle.setText(bookName[i]);
            bookAuthor.setText(author[i]);
            bookEdition.setText(edition[i]);

            return view;
        }
    }
}