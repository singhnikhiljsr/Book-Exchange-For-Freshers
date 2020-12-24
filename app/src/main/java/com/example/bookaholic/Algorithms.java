package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Algorithms extends AppCompatActivity {

    ListView listView;
    String [] bookName = {"Introduction to Algorithms","Data Structures using C","Data Structure using C & C++","Data Structures and\nAlgorithms Analysis in C++"};
    String [] author = {"T.H Cormen, C.E. Leiserson & R.L R","Reema Thareja","Yedidyah, Augenstein, Tannenbaum","Mark Allen Weiss"};
    String [] edition = {"Third Edition","2nd Edition","2nd Edition","4th Edition"};
    int[] images = {R.drawable.cormen,R.drawable.reema_thareja_ds,R.drawable.tannenbaum,R.drawable.mark_allen_weiss};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);
        listView = findViewById(R.id.listView_algorithms);
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