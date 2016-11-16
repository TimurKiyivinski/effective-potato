package com.swinburne.timur.assignment7task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MovieHandler movieHandler = new MovieHandler();

            saxParser.parse(getAssets().open("movie_list.xml"), movieHandler);
            ArrayList<Movie> movies = movieHandler.getMovies();

            for (Iterator<Movie> i = movies.iterator(); i.hasNext();) {
               Movie movie = i.next();
                Log.i("Movie", movie.toString());
            }

        } catch (ParserConfigurationException e) {
            Log.e("SAX", "ParserConfigurationException caught");
        } catch (SAXException e) {
            Log.e("SAX", "SAXException caught");
        } catch (IOException e) {
            Log.e("SAX", "IOException caught");
        }
    }
}
