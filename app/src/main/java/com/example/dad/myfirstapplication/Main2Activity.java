package com.example.dad.myfirstapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.List;


public class Main2Activity extends AppCompatActivity {
    public static String TAG="TG";

    List<Quote> quotes=null;
    TextView tvAuthor;
    TextView tvBody;
    int currentQuoteIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Enter OnCreate");
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quote quote = getRandomQuote();
                //tvAuthor = (TextView)findViewById(R.id.textViewAuthor);
                tvAuthor.setText(quote.getAuthor());
                //tvBody   = (TextView)findViewById(R.id.textViewBody);
                tvBody.setText(quote.getBody());
                Snackbar.make(view, "Quote " + currentQuoteIndex + " of " + quotes.size(), Snackbar.LENGTH_LONG )
                        .setAction("Action", null).show();
            }
        });

        XmlProcessor processor = new XmlProcessor();
        InputStream is = processor.loadXmlFromLocal(this);

        try {
            QuoteParser parser = new QuoteParser();
            quotes = parser.parse(is);
        }
        catch (XmlPullParserException e){
            Log.d(TAG, "XmlPullParser exception caught while parsing");
        }
        catch (Exception e ){
            Log.d(TAG, "General Exception while parsing");
        }
        Log.d(TAG, "Quotes:" + quotes);

        Quote quote = getRandomQuote();

        tvAuthor = (TextView)findViewById(R.id.textViewAuthor);
        tvAuthor.setText(quote.getAuthor());

        tvBody   = (TextView)findViewById(R.id.textViewBody);
        tvBody.setText(quote.getBody());

    }

    Quote getRandomQuote(){
        int lower=0;
        int upper= quotes.size();
        int r = (int) (Math.random() * (upper - lower)) + lower;
        currentQuoteIndex=r;
        Log.d(TAG, "random index:" + r);
        return quotes.get(r);
    }

}
