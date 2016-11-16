package com.swinburne.timur.assignment7task1;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MovieHandler extends DefaultHandler {
    private boolean inTitle = false;
    private boolean inGenre = false;
    private boolean inRatings = false;
    private boolean inSynopsis = false;
    private boolean inIcon = false;
    private boolean inImage = false;
    private boolean inCast = false;

    // Movie items
    private ArrayList<Movie> movies = null;
    private Movie movie;

    // Get data
    public ArrayList<Movie> getMovies () {
        return movies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("movie")) {
            movie = new Movie();
            if (movies == null)
                movies = new ArrayList<>();
        } else if (qName.equals("title")) {
            inTitle = true;
        } else if (qName.equals("genre")) {
            inGenre = true;
        } else if (qName.equals("rating")) {
            inRatings = true;
        } else if (qName.equals("image_icon")) {
            inIcon = true;
        } else if (qName.equals("image")) {
            inImage = true;
        } else if (qName.equals("cast")) {
            inCast = true;
        } else if (qName.equals("synopsis")) {
            inSynopsis = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("movie")) {
            movies.add(movie);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (inTitle) {
            movie.setTitle(new String(ch, start, length));
            inTitle = false;
        } else if (inGenre) {
            movie.setGenre(new String(ch, start, length));
            inGenre = false;
        } else if (inRatings) {
            movie.setRatings(Double.parseDouble(new String(ch, start, length)));
            inRatings = false;
        } else if (inIcon) {
            movie.setIcon(new String(ch, start, length));
            inIcon = false;
        } else if (inImage) {
            movie.setImage(new String(ch, start, length));
            inImage = false;
        } else if (inCast) {
            movie.setCast(new String(ch, start, length));
            inCast = false;
        } else if (inSynopsis) {
            movie.setSynopsis(new String(ch, start, length));
            inSynopsis = false;
        }
    }
}
