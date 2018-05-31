package com.semckinley.popularmovies.sampledata;

/**
 * Created by stephen.mckinley on 5/26/18.
 */

public class ReviewData {

    String author;
    String content;
    String id;
    String url;



    public ReviewData(){


    }

    public void setAuthor(String name){
        this.author = name;
    }
    public void setContent(String content){
        this.content = content;
    }

    public void setUrl(String url){this.url = url;}
    public void setId(String id){this.id= id;}


    public String getAuthor() { return this.author; }
    public String getContent(){ return this.content; }
    public String getUrl() { return this.url; }

    public String getId(){return this.id;}

}


