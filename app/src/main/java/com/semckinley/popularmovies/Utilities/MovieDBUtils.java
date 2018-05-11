package com.semckinley.popularmovies.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieDBUtils {

    final static String MOVIE_DB_BASE_URL_POPULAR= "http://api.themoviedb.org/3/movie/popular?api_key=&language=en-US&page=1";
    final static String MOVIE_DB_URL_HIGHEST_RATED = "http://api.themoviedb.org/3/movie/top_rated?api_key=&language=en-US&page=1";
    //final static String API_KEY = "api_key={}";//request an actual API_KEY from movieDB
    //final static String POPULAR = "popular?";
    //final static String HIGHEST_RATED = "highest_rated?";

    public static URL buildUrl (boolean rating){
        Uri builtUri;
        if(rating==true){
         builtUri = Uri.parse(MOVIE_DB_URL_HIGHEST_RATED).buildUpon()
                .build();}
               else{ builtUri = Uri.parse(MOVIE_DB_BASE_URL_POPULAR).buildUpon()
                .build();}
        URL url = null;
        try{
            url = new URL(builtUri.toString());
            } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }finally{
                urlConnection.disconnect();
            }
        }
    }

