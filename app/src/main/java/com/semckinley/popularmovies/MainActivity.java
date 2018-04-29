package com.semckinley.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.semckinley.popularmovies.Utilities.MovieDBUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

        private MovieAdapter mAdapter;
        private RecyclerView mMovieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = (RecyclerView) findViewById(R.id.rv_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,false );
        mMovieList.setLayoutManager(layoutManager);
        mMovieList.setHasFixedSize(true);
        mAdapter = new MovieAdapter(10);
        mMovieList.setAdapter(mAdapter);
        makeMovieDBSearch();
    }

    private void makeMovieDBSearch(){
        URL movieSearchUrl = MovieDBUtils.buildUrl();

    }
}
