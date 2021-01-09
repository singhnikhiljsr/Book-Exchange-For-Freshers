package com.example.bookaholic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BookPurchased extends AppCompatActivity {

    ImageView imageView;
    TextView bookTitle,bookAuthor,bookEdition;
    Button subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_purchased);

        imageView = findViewById(R.id.bookPurchasedImage);
        bookTitle = findViewById(R.id.bookPurchasedTitle);
        bookAuthor = findViewById(R.id.bookPurchasedAuthor);
        bookEdition = findViewById(R.id.bookPurchasedEdition);
        subscribe = findViewById(R.id.subscribe);
        String edition,title,author;


        Intent intent = getIntent();
        PassObject object = intent.getParcelableExtra("Object");
        String item = intent.getStringExtra("item");

        int image = object.getImageResource();
        title = object.getTitle();
        author = object.getAuthor();
        edition = object.getEdition();

        imageView.setImageResource(image);
        bookTitle.setText(title);
        bookAuthor.setText(author);
        bookEdition.setText(edition);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String userEmail = user.getEmail();
        String userDisplayName = user.getDisplayName();

        final String subject = "Congratulations!! ";

        final String textMessage = "Thank You for using Bookaholic!\n\n\n" +
                "Here are some important information's you need to know:\n\n" +
                "Below are the details of the seller of the " + item + " " + title + ", " + author + " (" + edition + ")" + "\n\n" +
                "Either of you can contact each other and decide a nominal price and a safe place to exchange " + item + "\n\n\n" +
                "Seller Name :  Rohan Parikh\n" +
                "Dept./Year  :  ISE, 4th Year\n" +
                "Contact No. :  7849555323\n" +
                "Email Id    :  rohanp.is18@bmsce.ac.in";

        final String myEmail = "Bookaholic.org@gmail.com";
        final String pass = "BooksAreLove";

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.port","587");

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail,pass);
                    }
                });


                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(myEmail));
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(userEmail));
                    message.setSubject(subject);
                    message.setText(textMessage);

                    new SendMail().execute(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class SendMail extends AsyncTask<Message,String,String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(BookPurchased.this,"Please Wait","Sending Mail....\nKindly read it properly for further enquiries!",true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Success")){

                Toast.makeText(getApplicationContext(), "Successfully Subscribed ", Toast.LENGTH_LONG).show();
            }
            else{

                Toast.makeText(getApplicationContext(), "Something went wrong ?", Toast.LENGTH_LONG).show();
            }
        }
    }
}