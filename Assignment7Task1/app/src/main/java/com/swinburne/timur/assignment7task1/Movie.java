package com.swinburne.timur.assignment7task1;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private Double ratings;
    private String synopsis;
    private String icon;
    private String image;
    private String casts;

    /**
     * Movie class to encapsulate movie data.
     * Class implements Serializable to enable easy bundling.
     */
    Movie () {
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
     * @param genre
     */
    public void setGenre (String genre) {
        this.genre = genre;
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
     * @param casts
     */
    public void setCast (String casts) {
        this.casts = casts;
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
    public String getGenre () {
        return this.genre;
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
    public String getCast () {
        return this.casts;
    }

    public String toString () {
        return "Movie: " + this.title;
    }
}
