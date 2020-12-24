package com.example.bookaholic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Pdf extends AppCompatActivity {

    EditText pdfName;
    Button upload,viewPdf;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfName = findViewById(R.id.pdfName);
        upload = findViewById(R.id.upload);
        viewPdf = findViewById(R.id.viewPdf);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);
            }
        });

        viewPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pdf.this,ViewPdf.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 &&  resultCode == RESULT_OK && data!=null && data.getData()!=null){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Uploading File...");
            progressDialog.setProgress(0);
            progressDialog.show();

            StorageReference reference = storageReference.child("Uploads/"+System.currentTimeMillis()+".pdf");
            reference.putFile(data.getData())
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url = uri.getResult();

                            UploadPdf uploadPdf = new UploadPdf(pdfName.getText().toString(),url.toString());

                            databaseReference.child(databaseReference.push().getKey()).setValue(uploadPdf);
                            Toast.makeText(Pdf.this, "File Successfully Uploaded", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Pdf.this, "Uploading Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    int currentProgress = (int) (100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setProgress(currentProgress);

                }
            });
        }
    }
}