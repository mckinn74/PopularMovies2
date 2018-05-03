package com.semckinley.popularmovies.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static String[] parseMovieJSON (String jsonMovie) {
        String [] parsedMoviePathData = null;
        final String MDB_RESULTS = "results";
        final String MDB_PATH = "poster_path";

       try {
           JSONObject movieJson = new JSONObject(jsonMovie);
           JSONArray movieArray = movieJson.getJSONArray(MDB_RESULTS);

           parsedMoviePathData = new String[movieArray.length()];
           for(int i = 0; i< movieArray.length(); i++){
               String path;
               JSONObject movieInfo = movieArray.getJSONObject(i);
               path = movieInfo.getString(MDB_PATH);
               parsedMoviePathData[i] = path;
           }
       }catch(JSONException e){
           e.printStackTrace();
       }
        return parsedMoviePathData;
    }
}
