package com.example.bookaholic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Physics extends AppCompatActivity {

    ListView listView;
    String [] bookName = {"Solid State Physics","Engineering Physics","Concepts of Modern Physics","Engineering Physics"};
    String [] author = {"S.O Pillai","V Rajendran","Arthur Beiser","R K Gaur and S L Gupta"};
    String [] edition = {"Sixth Edition","","Fifth Edition",""};
    int[] images = {R.drawable.so_pillai,R.drawable.v_rajendran,R.drawable.arthur_beiser,R.drawable.rk_gaur};
//    TextView bookTitle,bookAuthor,bookEdition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);
        listView = findViewById(R.id.listView_physics);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


//        bookTitle = findViewById(R.id.bookTitle);
//        bookAuthor = findViewById(R.id.bookAuthor);
//        bookEdition = findViewById(R.id.bookEdition);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i==0){
//                    Intent intent = new Intent(Physics.this,PurchasePhysics.class);
//                    intent.putExtra("image",R.drawable.so_pillai);
//                    intent.putExtra("bookTitle",bookTitle.getText().toString());
//                    intent.putExtra("bookAuthor",bookAuthor.getText().toString());
//                    intent.putExtra("bookEdition",bookEdition.getText().toString());
//                    startActivity(intent);
//
//                }
//            }
//        });
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