package com.semckinley.popularmovies;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.semckinley.popularmovies.sampledata.MovieDbHelper;
import com.semckinley.popularmovies.sampledata.MovieFavoriteContract;
import com.semckinley.popularmovies.utilities.JSONUtils;
import com.semckinley.popularmovies.utilities.MovieDBUtils;
import com.semckinley.popularmovies.sampledata.MovieData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

        private MovieAdapter mAdapter;
        private RecyclerView mMovieList;
        public String mMovieResults;
        private ProgressBar mLoading;
        public String [] mPosterPath = new String[20];
        private TextView mErrorMessage;
        SharedPreferences mPreference;
        private int FAVORITELIST = 3;

        public ArrayList<MovieData> mMovieDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayUseLogoEnabled(true);
        }
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
        mLoading = (ProgressBar) findViewById(R.id.pb_loading);

        mMovieList = (RecyclerView) findViewById(R.id.rv_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,false );
        mMovieList.setLayoutManager(layoutManager);

        mMovieList.setHasFixedSize(false);
        mAdapter = new MovieAdapter(20);

        mMovieList.setAdapter(mAdapter);
        mPreference = getDefaultSharedPreferences(this);
        mPreference.registerOnSharedPreferenceChangeListener(this);


        makeMovieDBSearch();


    }

    private void makeMovieSQLite() {
        MovieDbHelper movieDbHelper = new MovieDbHelper(this);
        SQLiteDatabase mDb = movieDbHelper.getReadableDatabase();
        mMovieDataList = null;
        mAdapter.setmPosterPath(mMovieDataList);
        mAdapter.notifyDataSetChanged();
        ArrayList<MovieData> movieData = new ArrayList<>();
        Context context = this;
        Cursor cursor = new FavoriteQueryTask(context).loadInBackground();
        //mDb.query(MovieFavoriteContract.MovieFavoriteList.TABLE_NAME, null, null, null, null, null,
          //      null);
      
        if (cursor != null){
            mErrorMessage.setVisibility(View.INVISIBLE);
            mLoading.setVisibility(View.VISIBLE);
            for (int i = 0; i < cursor.getCount(); i++){
                cursor.moveToPosition(i);
                MovieData movie = new MovieData();
                movie.setName(cursor.getString(cursor.getColumnIndex(MovieFavoriteContract.MovieFavoriteList.COLUMN_TITLE)));
                movie.setRating(cursor.getString(cursor.getColumnIndex(MovieFavoriteContract.MovieFavoriteList.COLUMN_RATING)));
                movie.setPlot(cursor.getString(cursor.getColumnIndex(MovieFavoriteContract.MovieFavoriteList.COLUMN_SYNOPSIS)));
                movie.setRelease(cursor.getString(cursor.getColumnIndex(MovieFavoriteContract.MovieFavoriteList.COLUMN_RELEASE)));
                movie.setPath(cursor.getString(cursor.getColumnIndex(MovieFavoriteContract.MovieFavoriteList.COLUMN_POSTER_PATH)));
                movie.setId(cursor.getString(cursor.getColumnIndex(MovieFavoriteContract.MovieFavoriteList.COLUMN_MOVIE_ID)));
                movieData.add(movie);
            }
            mAdapter.setmPosterPath(movieData);
            mAdapter.notifyDataSetChanged();
            mLoading.setVisibility(View.INVISIBLE);
        }
        else{
            mErrorMessage.setVisibility(View.VISIBLE);
        }
        cursor.close();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();


       Log.d("onResume", "onResume Called");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        mAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onPause(){
        super.onPause();
        mMovieList.removeAllViewsInLayout();
        mAdapter.notifyItemRangeRemoved(0,20);
       // mPreference.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        mMovieList.removeAllViewsInLayout();
        mAdapter.notifyItemRangeRemoved(0,20);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAdapter.notifyItemRangeRemoved(0, mPosterPath.length);
        mMovieList.removeAllViewsInLayout();
        mAdapter.notifyItemRangeRemoved(0,20);
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        mMovieList.removeAllViews();
        if(id==R.id.search_settings){
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    private void makeMovieDBSearch(){
        if ( Integer.parseInt(mPreference.getString("search_option", "3"))== FAVORITELIST){

            mMovieList.removeAllViewsInLayout();
            makeMovieSQLite();

        }
       else{ mPreference = getDefaultSharedPreferences(this);
        String popular = mPreference.getString("search_option", "1");
        URL movieSearchUrl = MovieDBUtils.buildUrl(popular);
        new MovieDBQueryTask().execute(movieSearchUrl);}
       mPreference.registerOnSharedPreferenceChangeListener(this);



    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals(getString(R.string.search_option))){
            makeMovieDBSearch();
        }
    }

    public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute (){
            super.onPreExecute();
            mLoading.setVisibility(View.VISIBLE);
        }
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
            mLoading.setVisibility(View.INVISIBLE);
            if(mMovieResults == null)
            {
                mErrorMessage.setVisibility(View.VISIBLE);
                mMovieList.setVisibility(View.GONE);

            } else{

            mMovieDataList = JSONUtils.parseMovieJSON(mMovieResults);
            mErrorMessage.setVisibility(View.INVISIBLE);
            mMovieList.setVisibility(View.VISIBLE);

            mAdapter.setmPosterPath(mMovieDataList);
            mAdapter.notifyDataSetChanged();

        }}
    }
}

