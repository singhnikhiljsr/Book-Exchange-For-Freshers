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

public class Maths extends AppCompatActivity {

    ListView listView;
    String [] bookName = {"Higher Engineering Maths","Higher Engineering Maths","Engineering Mathematics-1","Engineering Mathematics-2","Engineering Mathematics-3","Engineering Mathematics-4","Graph Theory & Combination"};
    String [] author = {"B.S. Grewal","B.V. Ramana","Dr. KSC","Dr. KSC","Dr. KSC","Dr. KSC","Dr. DSC"};
    String [] edition = {"43rd Edition, 2014","7th Edition, 2009","","","","","4th edition, 2011-12"};
    int[] images = {R.drawable.bsgrewal,R.drawable.bvramana,R.drawable.ksc_em1,R.drawable.ksc_em2,R.drawable.ksc_em3,R.drawable.ksc_em4,R.drawable.dsc_graph_theory};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);
        listView = findViewById(R.id.listView_maths);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PassObject object = new PassObject(images[i],bookName[i],author[i],edition[i]);
                Intent intent = new Intent(Maths.this, BookPurchased.class);
                intent.putExtra("Object",object);
                startActivity(intent);

            }

        });
    }

    class CustomAdapter extends BaseAdapter{

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