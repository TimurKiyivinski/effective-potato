package com.swinburne.timur.assignment7task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppMovieTheme);
        setContentView(R.layout.view_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get movie from intent parcel
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getParcelableExtra("movie");

        ImageView image = (ImageView) findViewById(R.id.movieImage);
        TextView casts = (TextView) findViewById(R.id.movieCasts);
        TextView synopsis = (TextView) findViewById(R.id.movieSynopsis);

        // Set contents
        getSupportActionBar().setLogo(getResources().getIdentifier(movie.getIcon(), "drawable", getPackageName()));
        getSupportActionBar().setTitle(movie.getTitle());
        image.setImageResource(getResources().getIdentifier(movie.getImage(), "drawable", getPackageName()));
        casts.setText(movie.getCast());
        synopsis.setText(movie.getSynopsis());
    }
}
