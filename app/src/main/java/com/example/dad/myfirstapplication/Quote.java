package com.example.dad.myfirstapplication;

/**
 * Created by Dad on 4/12/2016.
 */
public class Quote {

    String author;
    String body;

    public Quote ( String author, String  body ){
        this.author=author;
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
