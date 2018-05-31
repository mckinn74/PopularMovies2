package com.semckinley.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        MovieData mMovie = (MovieData) getIntent().getSerializableExtra("movieInfo");
        String id = mMovie.getId().toString();
        mError = (TextView) findViewById(R.id.tv_rev_error_message);
        mLoading = (ProgressBar) findViewById(R.id.pb_rev_loading);

    }

    @Override
    public void onBackPressed(){
        //TODO Fix the back button so app doesn't crash
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
            mError.setText(mReviewDataList.get(0).getContent().toString());
            mReview.setVisibility(View.VISIBLE);

            //mAdapter.setmTrailerPath(mReviewDataList);
            //mAdapter.notifyDataSetChanged();

        }}
}}
