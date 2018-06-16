package com.semckinley.popularmovies.sampledata;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.semckinley.popularmovies.sampledata.MovieFavoriteContract.MovieFavoriteList.TABLE_NAME;

public class FavoriteContentProvider extends ContentProvider {
    public final static int FAVORITES = 100;
    public final static int FAVORITES_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MovieFavoriteContract.AUTHORITY, MovieFavoriteContract.PATH_FAVORITES, FAVORITES);
        uriMatcher.addURI(MovieFavoriteContract.AUTHORITY, MovieFavoriteContract.PATH_FAVORITES + "/#", FAVORITES_WITH_ID);


        return uriMatcher;
    }

    private MovieDbHelper movieDbHelper;
    @Override
    public boolean onCreate() {
        Context context = getContext();
        movieDbHelper = new MovieDbHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = movieDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor cursor;
        switch (match){
            case FAVORITES:
                cursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs, null, null,
                        sortOrder);
                break;
            case FAVORITES_WITH_ID:
                String id = uri.getLastPathSegment();
                String mSelection = "id_number=?";
                String [] mSelectionArgs = new String[]{id};
                cursor = db.query(TABLE_NAME, projection, mSelection, mSelectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = movieDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch(match){
            case FAVORITES:
                long id = db.insert(TABLE_NAME, null, values);
                    if(id > 0){
                        returnUri = ContentUris.withAppendedId(MovieFavoriteContract.MovieFavoriteList.CONTENT_URI, id);
                    } else{
                        throw new android.database.SQLException("Failed to insert row into " + uri);
                    }
                break;
                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

         final SQLiteDatabase db = movieDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        int moviesDeleted; // starts as 0


        switch (match) {
             case FAVORITES_WITH_ID:

                String id = uri.getPathSegments().get(1);
                 moviesDeleted = db.delete(TABLE_NAME, "id_number=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

         if (moviesDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of tasks deleted
        return moviesDeleted;


    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
