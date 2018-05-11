package com.semckinley.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.semckinley.popularmovies.sampledata.MovieData;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mRating;
    private TextView mPlot;
    private TextView mRelease;
    private ImageView mPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mTitle = (TextView) findViewById(R.id.tv_movie_title);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mPlot = (TextView) findViewById(R.id.tv_plot);
        mRelease = (TextView) findViewById(R.id.tv_release);
        mPoster = (ImageView) findViewById(R.id.iv_poster);
        //Bundle b = this.getIntent().getExtras();

        MovieData movie = (MovieData) getIntent().getSerializableExtra("movieInfo");
        //int adapterPosition = getIntent().getIntExtra("adapterPosition", 0);
        //String path = movie.getPath();
        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getPath()).into(mPoster);
        mTitle.setText(movie.getName().toString());
        mRating.setText(movie.getRating().toString());
        mPlot.setText(movie.getPlot().toString());
        mRelease.setText(movie.getRelease().toString());

    }
}
