package com.swinburne.timur.assignment7task1;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private String genre;
    private Double ratings;
    private String synopsis;
    private String icon;
    private String image;
    private String casts;

    /**
     * Movie class to encapsulate movie data.
     * Class implements Parcelable to enable easy bundling.
     */
    Movie () {
    }

    /**
     * Recreate Movie from parcel
     * @param in Movie parcel
     */
    protected Movie (Parcel in) {
        String[] data = new String[7];

        in.readStringArray(data);
        this.setTitle(data[0]);
        this.setGenre(data[1]);
        this.setRatings(Double.parseDouble(data[2]));
        this.setSynopsis(data[3]);
        this.setIcon(data[4]);
        this.setImage(data[5]);
        this.setCast(data[6]);
    }

    // Getters and setters
    public void setTitle (String title) {
        this.title = title;
    }

    public void setGenre (String genre) {
        this.genre = genre;
    }

    public void setRatings (Double ratings) {
        this.ratings = ratings;
    }

    public void setSynopsis (String synopsis) {
        this.synopsis = synopsis;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    public void setImage (String image) {
        this.image = image;
    }

    public void setCast (String casts) {
        this.casts = casts;
    }

    public String getTitle () {
        return this.title;
    }

    public String getGenre () {
        return this.genre;
    }

    public Double getRatings () {
        return this.ratings;
    }

    public String getSynopsis () {
        return this.synopsis;
    }

    public String getIcon () {
        return this.icon;
    }

    public String getImage () {
        return this.image;
    }

    public String getCast () {
        return this.casts;
    }

    public String toString () {
        return "Movie: " + this.title;
    }

    /**
     * Default Parcel creator method, auto-generated stub
     */
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Parcel generator code
     *
     * @param dest Destination flattened parcel
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.getTitle(),
                this.getGenre(),
                this.getRatings().toString(),
                this.getSynopsis(),
                this.getIcon(),
                this.getImage(),
                this.getCast()
        });
    }
}
