package com.example.bookaholic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Courses extends AppCompatActivity {

    ListView listView;
    String[] bookName = {"CS50's Introduction\nto Computer Science", "Android Dev.\nfrom Scratch", "IT Automation\nwith Python", "IBM Data Science", "Machine Learning", "Python for\nEverybody", "Complete iOS 10\nDeveloper", "Full-Stack Web\nDev. with React", "Programming Using\nPython", "C++ Programming", "RESPONSIVE WEB\nDESIGN", "Deep Learning", "Cloud Computing", "AWS Concepts"};
    String[] author = {"Harvard University", "Udemy", "Google", "IBM", "Stanford University", "University Of MICHIGAN", "Udemy", "HKUST", "edX", "saylor.org", "GYMNASIUM", "COURSERA", "ILLINOIS", "Udemy"};
    String[] edition = {"Free Course + Certification [Paid]", "Free Course + Certification [Paid]", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course + Certification [Paid]", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course + Certification [Paid]", "Free Course + Certification [Free]", "Free Course", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course (Financial Aid) +\nCertification [Free]", "Free Course + Certification [Paid]"};
    int[] images = {R.drawable.harvard, R.drawable.udemy, R.drawable.google, R.drawable.ibm, R.drawable.stanford, R.drawable.michigan, R.drawable.udemy, R.drawable.hkust, R.drawable.edx, R.drawable.saylor, R.drawable.web_design, R.drawable.deeplearning, R.drawable.illinois, R.drawable.udemy};
    String[] urls = {"https://www.awin1.com/cread.php?awinmid=6798&awinaffid=453697&clickref=&p=%5B%5Bhttps%253A%252F%252Fwww.edx.org%252Fcourse%252Fcs50s-introduction-to-computer-science%5D%5D", "https://www.udemy.com/course/become-an-android-developer-from-scratch/?LSNPUBID=bt30QTxEyjA&ranEAID=bt30QTxEyjA&ranMID=39197&ranSiteID=bt30QTxEyjA-g4LtqTDHDhSsk0nXlt9Wuw&utm_medium=udemyads&utm_source=aff-campaign", "https://click.linksynergy.com/deeplink?id=bt30QTxEyjA&mid=40328&murl=https%3A%2F%2Fwww.coursera.org%2Fprofessional-certificates%2Fgoogle-it-automation", "https://click.linksynergy.com/deeplink?id=bt30QTxEyjA&mid=40328&murl=https%3A%2F%2Fwww.coursera.org%2Fprofessional-certificates%2Fibm-data-science", "https://click.linksynergy.com/deeplink?id=bt30QTxEyjA&mid=40328&murl=https%3A%2F%2Fwww.coursera.org%2Flearn%2Fmachine-learning", "https://click.linksynergy.com/deeplink?id=bt30QTxEyjA&mid=40328&murl=https%3A%2F%2Fwww.coursera.org%2Fspecializations%2Fpython", "https://click.linksynergy.com/deeplink?id=bt30QTxEyjA&mid=39197&murl=https%3A%2F%2Fwww.udemy.com%2Fcourse%2Fthe-complete-ios-10-developer-course%2F", "https://www.coursera.org/specializations/full-stack-react?ranMID=40328&ranEAID=bt30QTxEyjA&ranSiteID=bt30QTxEyjA-KH.VKxmaM_5NxeLHNBv96w&siteID=bt30QTxEyjA-KH.VKxmaM_5NxeLHNBv96w&utm_content=10&utm_medium=partners&utm_source=linkshare&utm_campaign=bt30QTxEyjA", "https://www.edx.org/course/introduction-to-computer-science-and-programming-7?source=aw&awc=6798_1610207765_79e55028f703aba46cc475ad1df762b3&utm_source=aw&utm_medium=affiliate_partner&utm_content=text-link&utm_term=453697_Guru99+Tech+Pvt+Ltd", "https://learn.saylor.org/course/cs107", "https://www.thegymnasium.com/courses/course-v1:GYM+109+0/about", "https://www.coursera.org/specializations/deep-learning?ranMID=40328&ranEAID=bt30QTxEyjA&ranSiteID=bt30QTxEyjA-d7LYPmttuI79wN9AqavypQ&siteID=bt30QTxEyjA-d7LYPmttuI79wN9AqavypQ&utm_content=10&utm_medium=partners&utm_source=linkshare&utm_campaign=bt30QTxEyjA", "https://www.coursera.org/specializations/cloud-computing?ranMID=40328&ranEAID=bt30QTxEyjA&ranSiteID=bt30QTxEyjA-CQVnelX1LO454FheAosi0A&siteID=bt30QTxEyjA-CQVnelX1LO454FheAosi0A&utm_content=10&utm_medium=partners&utm_source=linkshare&utm_campaign=bt30QTxEyjA", "https://www.udemy.com/course/aws-concepts/?LSNPUBID=bt30QTxEyjA&ranEAID=bt30QTxEyjA&ranMID=39197&ranSiteID=bt30QTxEyjA-d5pueziP8NtZ6Tt.QncR0A&utm_medium=udemyads&utm_source=aff-campaign"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        listView = findViewById(R.id.courses);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[i]));
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