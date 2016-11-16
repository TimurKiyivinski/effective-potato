package com.swinburne.timur.assignment7task1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MovieAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Movie> movies;
    private static LayoutInflater inflater = null;

    public MovieAdapter (Context context, ArrayList<Movie> movies) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.movies = movies;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowView = convertView;
        if (rowView == null)
            rowView = inflater.inflate(R.layout.row, null);

        final Movie movie = (Movie) this.getItem(position);

        // Get elements
        ImageView icon = (ImageView) rowView.findViewById(R.id.rowIcon);
        TextView title = (TextView) rowView.findViewById(R.id.rowTitle);
        TextView rating = (TextView) rowView.findViewById(R.id.rowRating);
        TextView genre = (TextView) rowView.findViewById(R.id.rowGenre);

        // Set element data
        icon.setImageResource(context.getResources().getIdentifier(movie.getIcon(), "drawable", context.getPackageName()));
        title.setText(movie.getTitle());
        rating.setText(movie.getRatings().toString());
        genre.setText(movie.getGenre());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewMovie.class);
                intent.putExtra("movie", movie);
                ((Activity) context).startActivityForResult(intent, 1);
            }
        });

        return rowView;
    }
}
