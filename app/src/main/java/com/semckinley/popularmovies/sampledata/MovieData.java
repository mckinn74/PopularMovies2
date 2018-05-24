package com.semckinley.popularmovies.sampledata;

import java.io.Serializable;

public class MovieData implements Serializable  {

    String name;
    String rating;
    String path;
    String plot;
    String release;
    String id;

    public MovieData(){


    }

    public void setName(String name){
        this.name = name;
    }
    public void setRating(String count){
        this.rating = count;
    }
    public void setPath(String path){this.path = path;}
    public void setPlot(String plot){this.plot =plot;}
    public void setRelease(String release){this.release = release;}
    public void setId(String id){this.id= id;}


    public String getName() { return this.name; }
    public String getRating(){ return this.rating; }
    public String getPlot() { return this.plot; }
    public String getPath(){ return this.path; }
    public String getRelease() { return this.release; }
    public String getId(){return this.id;}

}
