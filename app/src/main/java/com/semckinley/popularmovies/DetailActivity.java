package com.semckinley.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.semckinley.popularmovies.sampledata.MovieData;
import com.semckinley.popularmovies.sampledata.TrailerData;
import com.semckinley.popularmovies.utilities.JSONUtils;
import com.semckinley.popularmovies.utilities.MovieDBUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class DetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mRating;
    private TextView mPlot;
    private TextView mRelease;
    private ImageView mPoster;
    private RecyclerView mTrailer;
    private TrailerAdapter mAdapter;
    private String mId = "253412";
    private ProgressBar mLoading;
    private String mTrailerResults;
    private TextView mError;
    private TrailerData mTrailerDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mTitle = (TextView) findViewById(R.id.tv_movie_title);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mPlot = (TextView) findViewById(R.id.tv_plot);
        mRelease = (TextView) findViewById(R.id.tv_release);
        mPoster = (ImageView) findViewById(R.id.iv_poster);
        mLoading =(ProgressBar) findViewById(R.id.pb_trailer_loading);
        mError = (TextView) findViewById(R.id.tv_trailer_error);


        MovieData movie = (MovieData) getIntent().getSerializableExtra("movieInfo");

        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getPath()).into(mPoster);
        mTitle.setText(movie.getName().toString());
        mRating.setText(movie.getRating().toString());
        mPlot.setText(movie.getPlot().toString());
        mRelease.setText(movie.getRelease().toString());
        mId = movie.getId().toString();



        mTrailer=(RecyclerView)findViewById(R.id.rv_trailers);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,false );
        mTrailer.setLayoutManager(layoutManager);

        mTrailer.setHasFixedSize(true);
        mAdapter = new TrailerAdapter(5);
        mTrailer.setAdapter(mAdapter);
        makeTrailerSearch();

        }

    private void makeTrailerSearch(){
        //SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        //boolean popular = sharedPreferences.getBoolean("search_option", false);
        URL movieSearchUrl = MovieDBUtils.trailerBuildUrl(mId);
        new DetailActivity.TrailerQueryTask().execute(movieSearchUrl);




    }


public class TrailerQueryTask extends AsyncTask<URL, Void, String> {

    @Override
    protected void onPreExecute (){
        super.onPreExecute();
        mLoading.setVisibility(View.VISIBLE);
    }
    @Override
    protected String doInBackground(URL... params){

        URL searchUrl = params[0];
        mTrailerResults = null;
        try{
            mTrailerResults =MovieDBUtils.getResponseFromHttpUrl(searchUrl);
        }catch(IOException e){
            e.printStackTrace();
        }

        return mTrailerResults;
    }

    protected void onPostExecute(String mTrailerResults){
        mLoading.setVisibility(View.INVISIBLE);

        if(mTrailerResults == null)
        {
            mError.setVisibility(View.VISIBLE);
            mTrailer.setVisibility(View.GONE);

        } else{

            mTrailerDataList = JSONUtils.parseTrailerJSON(mTrailerResults);
            mError.setVisibility(View.INVISIBLE);
            mTrailer.setVisibility(View.VISIBLE);

            //mAdapter.setmPosterPath(mMovieDataList);
            mAdapter.notifyDataSetChanged();

        }}
}}




