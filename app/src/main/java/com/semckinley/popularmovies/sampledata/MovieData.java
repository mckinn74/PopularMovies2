package com.semckinley.popularmovies.sampledata;

public class MovieData {

    String name; //Student's name
    int rating; //How many times student spoke

    public MovieData(String name, int rating){
        this.name = name;
        this.rating = rating;

    }

    public void setName(String name){
        this.name = name;
    }
    public void setRating(int count){
        this.rating = count;
    }


    public String getName()
    {

        return this.name;
    }
    public int getRating(){
        return this.rating;
    }

}
