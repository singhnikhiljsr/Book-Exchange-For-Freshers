package com.example.bookaholic;

import android.os.Parcel;
import android.os.Parcelable;

class PassObject implements Parcelable{

    int image;
    String title;
    String author;
    String edition;



    public PassObject(int image, String title, String author, String edition) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.edition = edition;
    }

    protected PassObject(Parcel in) {
        image = in.readInt();
        title = in.readString();
        author = in.readString();
        edition = in.readString();
    }

    public static final Creator<PassObject> CREATOR = new Creator<PassObject>() {
        @Override
        public PassObject createFromParcel(Parcel in) {
            return new PassObject(in);
        }

        @Override
        public PassObject[] newArray(int size) {
            return new PassObject[size];
        }
    };

    public int getImageResource() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(edition);
    }
}
