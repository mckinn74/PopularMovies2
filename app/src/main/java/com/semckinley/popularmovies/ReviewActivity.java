package com.semckinley.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.semckinley.popularmovies.sampledata.MovieData;
import com.semckinley.popularmovies.sampledata.ReviewData;
import com.semckinley.popularmovies.utilities.JSONUtils;
import com.semckinley.popularmovies.utilities.MovieDBUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private ReviewAdapter mAdapter;
    private RecyclerView mReview;
    public ArrayList<String> mReviewList;
    private TextView mError;
    private ProgressBar mLoading;
    private String mReviewResults;
    private ArrayList<ReviewData> mReviewDataList;
    private MovieData mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        mMovie = (MovieData) getIntent().getSerializableExtra("movieInfo");
        String id = mMovie.getId().toString();
        mError = (TextView) findViewById(R.id.tv_rev_error_message);
        mLoading = (ProgressBar) findViewById(R.id.pb_rev_loading);
        mReview=(RecyclerView)findViewById(R.id.rv_reviews);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL,false );
        mReview.setLayoutManager(layoutManager);

        mReview.setHasFixedSize(true);
        mAdapter = new ReviewAdapter();
        mReview.setAdapter(mAdapter);


        makeReviewSearch(id);


    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("movieInfo", mMovie);
       setResult(Activity.RESULT_OK, intent);
       finish();//TODO Fix the back button so app doesn't crash
    }
    private void makeReviewSearch(String id){
        //SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        //boolean popular = sharedPreferences.getBoolean("search_option", false);
        URL movieSearchUrl = MovieDBUtils.reviewBuildUrl(id);
        new ReviewActivity.ReviewQueryTask().execute(movieSearchUrl);




    }

public class ReviewQueryTask extends AsyncTask<URL, Void, String> {

    @Override
    protected void onPreExecute (){
        super.onPreExecute();
        mLoading.setVisibility(View.VISIBLE);
    }
    @Override
    protected String doInBackground(URL... params){

        URL searchUrl = params[0];
        mReviewResults = null;
        try{
            mReviewResults = MovieDBUtils.getResponseFromHttpUrl(searchUrl);
        }catch(IOException e){
            e.printStackTrace();
        }

        return mReviewResults;
    }

    protected void onPostExecute(String mReviewResults){
        mLoading.setVisibility(View.INVISIBLE);

        if(mReviewResults == null)
        {
            mError.setVisibility(View.VISIBLE);
            mReview.setVisibility(View.GONE);

        } else{

            mReviewDataList = JSONUtils.parseReviewJSON(mReviewResults);
            mError.setVisibility(View.INVISIBLE);
            mReview.setVisibility(View.VISIBLE);

            mAdapter.setmReviewList(mReviewDataList);
            mAdapter.notifyDataSetChanged();

        }}
}}
