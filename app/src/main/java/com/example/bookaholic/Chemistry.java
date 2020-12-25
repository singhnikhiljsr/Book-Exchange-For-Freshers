package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Chemistry extends AppCompatActivity {

    ListView listView;
    String [] bookName = {"Engineering Chemistry","Engineering Chemistry","Wiley’s Engineering\nChemistry","Engineering Chemistry:\nFundamentals & Applications"};
    String [] author = {"Chandra Shekara and Basavaraju B C","P.C. Jain and Monica Jain","Wiley India","Shikha Agarwal"};
    String [] edition = {"","16th Edition","2nd Edition",""};
    int[] images = {R.drawable.basuchandra,R.drawable.jain,R.drawable.wileys,R.drawable.shikha_agarwal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry);

        listView = findViewById(R.id.listView_chemistry);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PassObject object = new PassObject(images[i],bookName[i],author[i],edition[i]);
                Intent intent = new Intent(Chemistry.this, BookPurchased.class);
                intent.putExtra("Object",object);
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