package com.example.bookaholic;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPdf extends AppCompatActivity {

    ListView pdfViewer;
    DatabaseReference databaseReference;
    List<UploadPdf> uploadPdfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfViewer = findViewById(R.id.pdfViewer);
        uploadPdfs = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()){

                    UploadPdf uploadPdf = postSnapshot.getValue(UploadPdf.class);
                    uploadPdfs.add(uploadPdf);
                }

                String[] pdfName = new String[uploadPdfs.size()];

                for (int i = 0; i < pdfName.length; i++) {

                    pdfName[i] = uploadPdfs.get(i).getName();
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, pdfName) {


                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView textview = view.findViewById(android.R.id.text1);
                        textview.setTextColor(Color.WHITE);
                        textview.setBackgroundColor(R.drawable.gradient_bg);

                        return view;
                    }
                };
                pdfViewer.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pdfViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UploadPdf uploadPdf = uploadPdfs.get(i);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uploadPdf.getUrl()));
                startActivity(intent);
            }
        });


    }
}