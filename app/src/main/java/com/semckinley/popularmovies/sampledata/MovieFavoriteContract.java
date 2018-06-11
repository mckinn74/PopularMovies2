package com.semckinley.popularmovies.sampledata;

import android.provider.BaseColumns;

/**
 * Created by stephen.mckinley on 5/18/18.
 */

public class MovieFavoriteContract {
    private MovieFavoriteContract(){}

    public static final class MovieFavoriteList implements BaseColumns {

        public static final String TABLE_NAME = "favorite_list";
        public static final String COLUMN_TITLE="movie_title";
        public static final String COLUMN_RATING="movie_rating";
        public static final String COLUMN_POSTER_PATH="poster_path";
        public static final String COLUMN_SYNOPSIS="movie_plot";
        public static final String COLUMN_RELEASE="release_date";
        public static final String COLUMN_MOVIE_ID ="id_number";

    }
}
