package com.semckinley.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.semckinley.popularmovies.sampledata.MovieData;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private ReviewAdapter mAdapter;
    private RecyclerView reviewList;
    public ArrayList<String> mReviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        MovieData mMovie = (MovieData) getIntent().getSerializableExtra("movieInfo");

        Intent intent = new Intent ();
        intent.putExtra("movieInfo", mMovie);
        ReviewActivity.this.setResult(RESULT_OK, intent);



    }
}
