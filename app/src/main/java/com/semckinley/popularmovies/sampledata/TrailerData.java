package com.semckinley.popularmovies.sampledata;

import java.io.Serializable;

/**
 * Created by stephen.mckinley on 5/24/18.
 */

public class TrailerData implements Serializable {

    String mTrailer_id;
    String mKey;
    String mName;
    String mSite;
    String mType;
    String mCount;


    public TrailerData(){}


    public void setName(String name){
        this.mName = name;
    }
    public void setCount(String count){
        this.mCount = count;
    }
    public void setSite(String site){this.mSite = site;}
    public void setType(String type){this.mType =type;}
    public void setKey(String key){this.mKey = key;}
    public void setId(String id){this.mTrailer_id= id;}


    public String getName() { return this.mName; }
    public String getCount(){ return this.mCount; }
    public String getType() { return this.mType; }
    public String getSite(){ return this.mSite; }
    public String getKey() { return this.mKey; }
    public String getId(){return this.mTrailer_id;}

}
