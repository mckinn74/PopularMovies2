package com.semckinley.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.semckinley.popularmovies.sampledata.FavoriteContentProvider;

import com.semckinley.popularmovies.sampledata.MovieDbHelper;
import com.semckinley.popularmovies.sampledata.MovieFavoriteContract;

public class FavoriteQueryTask extends CursorLoader{
    Context sContext;
    public FavoriteQueryTask(@NonNull Context context) {
        super(context);
        sContext = context;
    }

    @Nullable
    @Override
    public Cursor loadInBackground() {
        try {

            return sContext.getContentResolver().query(MovieFavoriteContract.MovieFavoriteList.CONTENT_URI,
                    null,
                    null,
                    null,
                    MovieFavoriteContract.MovieFavoriteList.COLUMN_MOVIE_ID);

        } catch (Exception e) {
            //Log.e(TAG, "Failed to asynchronously load data.");
            e.printStackTrace();
            return null;
        }

    }
}
