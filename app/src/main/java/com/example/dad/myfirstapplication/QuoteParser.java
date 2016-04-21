package com.example.dad.myfirstapplication;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dad on 4/12/2016.
 */
public class QuoteParser{

       public static String TAG = "TG";

        public  List<Quote>  parse(InputStream in) throws XmlPullParserException,IOException {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parser.nextTag();
                return readQuotes(parser);
            } finally {
                in.close();
            }
        }


    /*
    <quotes>
       <quote>
          <author>tom</author>
          <body>To Err is Devine</body>
        </quote>
     </quotes>
    */
        private List<Quote> readQuotes(XmlPullParser parser) throws XmlPullParserException, IOException {
            String ns = "";

            parser.require(XmlPullParser.START_TAG, ns, "quotes");
            List<Quote> quotes = new ArrayList<>();

            String tagName="";
            String tagTextBody="";
            String tagTextAuthor="";

            boolean done = false;

            while ( !done )
            {
                parser.next();
                switch ( parser.getEventType() )
                {
                    case  XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        if ( ! ( tagName.equals( "author") || tagName.equals("body") ) )
                        {
                            Log.d(TAG, "skipping tag: " + tagName );
                            continue;  // NOT author or body tag
                        }

                        // process author tag
                        if (tagName.equals ("author")) {
                            Log.d(TAG, "found author tag");
                            if (parser.next() == XmlPullParser.TEXT) {
                                tagTextAuthor = parser.getText();
                                Log.d(TAG, "text:" + tagTextAuthor);
                            }
                        } else if (tagName.equals ("body")) {
                            Log.d(TAG, "found body tag");
                            if (parser.next() == XmlPullParser.TEXT) {
                                tagTextBody = parser.getText();
                                Log.d(TAG, "text:" + tagTextBody);

                                // add Quote object to list
                                Quote quote = new Quote ( tagTextAuthor, tagTextBody );
                                quotes.add ( quote );
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if ( tagName.equals("quotes") ){
                            Log.d(TAG, "ending quotes tag, exitting parser");
                            done=true;
                        }

                }

            }
            Log.d(TAG,"Returning Quotes, count: " +  quotes.size() );
            return quotes;
        }

    }
