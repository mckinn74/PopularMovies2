package com.semckinley.popularmovies.sampledata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stephen.mckinley on 5/19/18.
 */

public class MovieDbHelper extends SQLiteOpenHelper {


    /*To do this I need to extend SQliteOpenHelper and to extend that I need to override onCreate and onUpgrade

     */

    private static final String DATABASE_NAME = "movieinfo.db";
    private static final int DATABASE_VERSION = 1;

    //initializing database version for future incrementation to upgrade database after changes
    //Create the constructor
    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Here I make the string that will create the database that will house the student information
        final String SQL_CREATE_STUDENT_TABLE =
                "CREATE TABLE " + MovieFavoriteContract.MovieFavoriteList.TABLE_NAME + " (" +
                        MovieFavoriteContract.MovieFavoriteList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        MovieFavoriteContract.MovieFavoriteList.COLUMN_TITLE + " TEXT NOT NULL, " +
                        MovieFavoriteContract.MovieFavoriteList.COLUMN_RATING + " INTEGER NOT NULL, "
                        + MovieFavoriteContract.MovieFavoriteList.COLUMN_SYNOPSIS + " TEXT NOT NULL, " +
                        MovieFavoriteContract.MovieFavoriteList.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                        MovieFavoriteContract.MovieFavoriteList.COLUMN_MOVIE_ID + " TEXT NOT NULL, " +
                        MovieFavoriteContract.MovieFavoriteList.COLUMN_RELEASE + " TEXT NOT NULL" +
                        "); ";
        db.execSQL(SQL_CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //The example I'm following in creating drops the table if there is a version change and creates a new one...
        db.execSQL("DROP TABLE IF EXISTS " + MovieFavoriteContract.MovieFavoriteList.TABLE_NAME);//TODO Study SQLiteDatabases
        onCreate(db);

    }
}