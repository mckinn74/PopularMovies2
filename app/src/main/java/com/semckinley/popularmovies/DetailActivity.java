package com.semckinley.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
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
import android.widget.ToggleButton;

import com.semckinley.popularmovies.sampledata.MovieData;
import com.semckinley.popularmovies.sampledata.TrailerData;
import com.semckinley.popularmovies.utilities.JSONUtils;
import com.semckinley.popularmovies.utilities.MovieDBUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class DetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mRating;
    private TextView mPlot;
    private TextView mRelease;
    private ImageView mPoster;
    private RecyclerView mTrailer;
    private TrailerAdapter mAdapter;
    private String mId;
    private ProgressBar mLoading;
    private String mTrailerResults;
    private TextView mError;
    private Button mReview;
    private ArrayList<TrailerData> mTrailerDataList;
    private ToggleButton mFavorites;
    public MovieData mMovie;
    static final String ON_SAVE_INSTANCE_STATE = "Movie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


            mMovie = (MovieData) getIntent().getSerializableExtra("movieInfo");


        mTitle = (TextView) findViewById(R.id.tv_movie_title);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mPlot = (TextView) findViewById(R.id.tv_plot);
        mRelease = (TextView) findViewById(R.id.tv_release);
        mPoster = (ImageView) findViewById(R.id.iv_poster);
        mLoading =(ProgressBar) findViewById(R.id.pb_trailer_loading);
        mError = (TextView) findViewById(R.id.tv_trailer_error);
        mReview = (Button) findViewById(R.id.bt_reviews);
        mFavorites = (ToggleButton) findViewById(R.id.tb_fave);
        mReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = DetailActivity.this;

                Intent intent = new Intent(context, ReviewActivity.class);
                intent.putExtra("movieInfo", mMovie);
                //intent.putExtra("adapterPosition", adapterPosition);
                startActivityForResult(intent, 1);
            }
        });






        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + mMovie.getPath()).into(mPoster);
        mTitle.setText(mMovie.getName().toString());
        mRating.setText(mMovie.getRating().toString());
        mPlot.setText(mMovie.getPlot().toString());
        mRelease.setText(mMovie.getRelease().toString());
        mId = mMovie.getId().toString();



        mTrailer=(RecyclerView)findViewById(R.id.rv_trailers);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL,false );
        mTrailer.setLayoutManager(layoutManager);

        mTrailer.setHasFixedSize(true);
        mAdapter = new TrailerAdapter(5);
        mTrailer.setAdapter(mAdapter);
        makeTrailerSearch();

        }
        public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            if(resultCode == RESULT_OK){
                mMovie = (MovieData) data.getSerializableExtra("movieInfo");
            }

        }
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

            mAdapter.setmTrailerPath(mTrailerDataList);
            mAdapter.notifyDataSetChanged();

        }}
}}




