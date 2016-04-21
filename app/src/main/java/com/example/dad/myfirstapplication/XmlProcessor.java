package com.example.dad.myfirstapplication;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created  4/11/2016.
 */
public class XmlProcessor {

   public static String TAG = "TG";

    public InputStream loadXmlFromLocal (Main2Activity activity){

        InputStream inputStream = activity.getResources().openRawResource(R.raw.quotes);

        return inputStream;

    }

    public void loadXmlFromURL (){
       try {
           String urlString = "http://www.gave.us/quotes.xml";
           URL url = new URL(urlString);
           URLConnection connection = url.openConnection();
           HttpURLConnection httpConn = (HttpURLConnection) connection;
           httpConn.setDoInput(true);
           httpConn.setRequestProperty("charset", "utf-8");
           int responseCode = httpConn.getResponseCode();
           //if (responseCode != HttpStatus.SC_OK) {
           if (responseCode != 200 ) {
               InputStream xmlStream = httpConn.getInputStream();
               Log.d(TAG , "found xml resource");
               // Pass the xmlStream object to a XPP, SAX or DOM parser.
           }
       }catch(Exception e){
           Log.d( TAG, "error getting xml from URL");
       }

    }























}
