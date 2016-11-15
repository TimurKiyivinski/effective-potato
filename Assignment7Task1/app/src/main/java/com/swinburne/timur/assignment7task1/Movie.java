package com.swinburne.timur.assignment7task1;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    private String title;
    private Double ratings;
    private String synopsis;
    private String icon;
    private String image;
    private ArrayList<String> tags;
    private ArrayList<String> casts;

    /**
     * Movie class to encapsulate movie data.
     * Class implements Serializable to enable easy bundling.
     */
    Movie () {
        this.tags = new ArrayList<>();
        this.casts = new ArrayList<>();
    }

    /**
     *
     * @param title
     */
    public void setTitle (String title) {
        this.title = title;
    }

    /**
     *
     * @param ratings
     */
    public void setRatings (Double ratings) {
        this.ratings = ratings;
    }

    /**
     *
     * @param synopsis
     */
    public void setSynopsis (String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     *
     * @param icon
     */
    public void setIcon (String icon) {
        this.icon = icon;
    }

    /**
     *
     * @param image
     */
    public void setImage (String image) {
        this.image = image;
    }

    /**
     *
     * @param tag
     */
    public void addTag (String tag) {
        this.tags.add(tag);
    }

    /**
     *
     * @param cast
     */
    public void addCast (String cast) {
        this.casts.add(cast);
    }

    /**
     *
     * @return
     */
    public String getTitle () {
        return this.title;
    }

    /**
     *
     * @return
     */
    public Double getRatings () {
        return this.ratings;
    }

    /**
     *
     * @return
     */
    public String getSynopsis () {
        return this.synopsis;
    }

    /**
     *
     * @return
     */
    public String getIcon () {
        return this.icon;
    }

    /**
     *
     * @return
     */
    public String getImage () {
        return this.image;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getTags () {
        return this.tags;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getCasts () {
        return this.casts;
    }
}
