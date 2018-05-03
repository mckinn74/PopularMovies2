package com.semckinley.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.semckinley.popularmovies.Utilities.JSONUtils;
import com.semckinley.popularmovies.Utilities.MovieDBUtils;

import java.io.IOException;
import java.net.URL;

import static com.semckinley.popularmovies.Utilities.MovieDBUtils.getResponseFromHttpUrl;

public class MainActivity extends AppCompatActivity {

        private MovieAdapter mAdapter;
        private RecyclerView mMovieList;
        public String movieResults;
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
        String [] poster_paths = JSONUtils.parseMovieJSON(movieResults);
    }

    private void makeMovieDBSearch(){
        URL movieSearchUrl = MovieDBUtils.buildUrl();
         new MovieDBQueryTask().execute(movieSearchUrl);



    }
    public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params){

            URL searchUrl = params[0];
             movieResults = null;
            try{
                movieResults =MovieDBUtils.getResponseFromHttpUrl(searchUrl);
            }catch(IOException e){
                e.printStackTrace();
            }
            return movieResults;
        }
    }
}
