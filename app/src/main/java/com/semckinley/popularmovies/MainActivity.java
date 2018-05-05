package com.semckinley.popularmovies;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.semckinley.popularmovies.Utilities.JSONUtils;
import com.semckinley.popularmovies.Utilities.MovieDBUtils;

import java.io.IOException;
import java.net.URL;

import static com.semckinley.popularmovies.Utilities.MovieDBUtils.getResponseFromHttpUrl;

public class MainActivity extends AppCompatActivity {

        private MovieAdapter mAdapter;
        private RecyclerView mMovieList;
        public String mMovieResults;
        public String [] mPosterPath = {"/Dvpvk1r.png"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Context context = MainActivity.this;
        mMovieList = (RecyclerView) findViewById(R.id.rv_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,false );
        mMovieList.setLayoutManager(layoutManager);

        mMovieList.setHasFixedSize(true);
        mAdapter = new MovieAdapter(20);

        mMovieList.setAdapter(mAdapter);


        makeMovieDBSearch();


    }

    private void makeMovieDBSearch(){
        URL movieSearchUrl = MovieDBUtils.buildUrl();
        new MovieDBQueryTask().execute(movieSearchUrl);



    }
    public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params){

            URL searchUrl = params[0];
             mMovieResults = null;
            try{
                mMovieResults =MovieDBUtils.getResponseFromHttpUrl(searchUrl);
            }catch(IOException e){
                e.printStackTrace();
            }

            return mMovieResults;
        }

        protected void onPostExecute(String mMovieResults){
            mPosterPath = JSONUtils.parseMovieJSON(mMovieResults);


            mAdapter.setmPosterPath(mPosterPath);
            mAdapter.notifyDataSetChanged();

        }
    }
}
