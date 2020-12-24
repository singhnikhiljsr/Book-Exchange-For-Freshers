package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class C extends AppCompatActivity {

    ListView listView;
    String [] bookName = {"Programming in C","Programming with C","C Programming Language"};
    String [] author = {"Reema Thareja","B S Gottfried","Brian W. Kernighan and Dennis M R"};
    String [] edition = {"Second Edition","","Second Edition"};
    int[] images = {R.drawable.reema_thareja,R.drawable.bs_gottfried,R.drawable.pearson};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        listView = findViewById(R.id.listView_c);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
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

            view = getLayoutInflater().inflate(R.layout.custom_list_view,null);
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