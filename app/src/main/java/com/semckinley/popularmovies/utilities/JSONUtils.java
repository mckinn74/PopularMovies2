package com.semckinley.popularmovies.utilities;

import com.semckinley.popularmovies.sampledata.MovieData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {
    public static ArrayList<MovieData> parseMovieJSON (String jsonMovie) {
        String [] parsedMoviePathData = null;
        final String MDB_RESULTS = "results";
        final String MDB_PATH = "poster_path";
        final String MDB_RATING = "vote_average";
        final String MDB_RELEASE = "release_date";
        final String MDB_SYNOPSIS = "overview";
        final String MDB_TITLE = "original_title";
        ArrayList<MovieData> mMovieList = new ArrayList<>();

       try {
           JSONObject movieJson = new JSONObject(jsonMovie);
           JSONArray movieArray = movieJson.getJSONArray(MDB_RESULTS);

           parsedMoviePathData = new String[movieArray.length()];
           for(int i = 0; i< movieArray.length(); i++){
               MovieData movieData = new MovieData();

               String path;
               JSONObject movieInfo = movieArray.getJSONObject(i);

               path=movieInfo.getString(MDB_PATH);
               movieData.setPath(movieInfo.getString(MDB_PATH));
               movieData.setRating(movieInfo.getString(MDB_RATING));
               movieData.setName(movieInfo.getString(MDB_TITLE));
               movieData.setPlot(movieInfo.getString(MDB_SYNOPSIS));
               movieData.setRelease(movieInfo.getString(MDB_RELEASE));
               mMovieList.add(movieData);
               parsedMoviePathData[i] = path;
           }
       }catch(JSONException e){
           e.printStackTrace();
       }
        return mMovieList;
    }
}
