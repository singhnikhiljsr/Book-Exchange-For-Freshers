package com.example.bookaholic;

public class UploadPdf {

    public String name;
    public String url;

    public UploadPdf() {
    }

    public UploadPdf(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
