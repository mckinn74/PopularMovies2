package com.semckinley.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieDBUtils {

     final static String API_KEY = "api_key="; //add requested API_KEY from themovieDB here
    final static String POPULAR = "popular?";
    final static String HIGHEST_RATED = "top_rated?";
    final static String MOVIE_DB_BASE_URL = "http://api.themoviedb.org/3/movie/";
    final static String END_STRING ="&language=en-US&page=1";

    public static URL buildUrl (boolean rating){
        Uri builtUri;
        if(rating==true){
         builtUri = Uri.parse(MOVIE_DB_BASE_URL + HIGHEST_RATED + API_KEY +END_STRING).buildUpon()
                .build();}
               else{ builtUri = Uri.parse(MOVIE_DB_BASE_URL + POPULAR + API_KEY +END_STRING).buildUpon()
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

