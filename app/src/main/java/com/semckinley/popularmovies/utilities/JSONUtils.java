package com.semckinley.popularmovies.utilities;

import com.semckinley.popularmovies.sampledata.MovieData;
import com.semckinley.popularmovies.sampledata.ReviewData;
import com.semckinley.popularmovies.sampledata.TrailerData;

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
        final String MDB_ID="id";
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
               movieData.setId(movieInfo.getString(MDB_ID));
               mMovieList.add(movieData);
               parsedMoviePathData[i] = path;
           }
       }catch(JSONException e){
           e.printStackTrace();
       }
        return mMovieList;
    }

    public static ArrayList<TrailerData> parseTrailerJSON (String jsonTrailer){
        String [] parsedTrailerData = null;
        final String TDB_RESULTS = "results";
        final String TDB_SITE = "site";
        final String TDB_TYPE = "type";
        final String TDB_KEY = "key";
        final String TDB_NAME = "name";
        final String TDB_ID="id";
        ArrayList<TrailerData> mTrailerList = new ArrayList<>();

        try {
            JSONObject trailJson = new JSONObject(jsonTrailer);
            JSONArray trailerList = trailJson.getJSONArray(TDB_RESULTS);

            parsedTrailerData = new String[trailerList.length()];
            for(int i = 0; i< trailerList.length(); i++){
                TrailerData trailerData = new TrailerData();

                //String path;
                JSONObject trailerInfo = trailerList.getJSONObject(i);
                trailerData.setSite(trailerInfo.getString(TDB_SITE));
                trailerData.setType(trailerInfo.getString(TDB_TYPE));
                trailerData.setName(trailerInfo.getString(TDB_NAME));
                trailerData.setKey(trailerInfo.getString(TDB_KEY));

                trailerData.setId(trailerInfo.getString(TDB_ID));
                mTrailerList.add(trailerData);
                //parsedTrailerData[i] = path;
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        return mTrailerList;
    }

    public static ArrayList<ReviewData> parseReviewJSON(String jsonReview){
        ArrayList<ReviewData> mReviewList = new ArrayList<>();
        final String RDB_RESULTS = "results";
        final String RDB_AUTHOR = "author";
        final String RDB_CONTENT = "content";
        final String RDB_ID = "id";
        final String RDB_URL = "url";


        try {
            JSONObject revJSON = new JSONObject(jsonReview);
            JSONArray reviewList = revJSON.getJSONArray(RDB_RESULTS);

            for(int j = 0; j<reviewList.length(); j++) {
                ReviewData reviewData = new ReviewData();
                JSONObject reviewInfo = reviewList.getJSONObject(j);
                reviewData.setAuthor(reviewInfo.getString(RDB_AUTHOR));
                reviewData.setContent(reviewInfo.getString(RDB_CONTENT));
                reviewData.setId(reviewInfo.getString(RDB_ID));
                reviewData.setUrl(reviewInfo.getString(RDB_URL));
                mReviewList.add(reviewData);
            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


        return mReviewList;
    }
}
