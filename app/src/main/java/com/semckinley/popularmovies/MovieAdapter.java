package com.semckinley.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.semckinley.popularmovies.sampledata.MovieData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
   private int mNumberMovies;
    private String [] mPosterPath = new String[20];
    private int movieCount;
    static Context mContext;
    ArrayList<MovieData> mMovieDataList;



   public MovieAdapter(int movieCount){

    mNumberMovies = movieCount;


   }

   @Override
   public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
       Context context = viewGroup.getContext();
       Log.d("onCreateViewHolder", "onCreateViewHolder Started");
       int layoutIdForMovieItem = R.layout.movie_list;
       LayoutInflater inflater = LayoutInflater.from(context);
       boolean shouldAttachToParentImmediately = false;
       View view = inflater.inflate(layoutIdForMovieItem, viewGroup, shouldAttachToParentImmediately);
       MovieViewHolder viewHolder = new MovieViewHolder(view);

       return viewHolder;

   }
   @Override
   public void onBindViewHolder(MovieViewHolder holder, int position){
       holder.bind(position);

       Log.d("onBindViewHolder", "onBindViewHolder Started");
       if(mPosterPath == null){
           Log.d("mPosterPath", "PosterPath Array is Null");

               String path = "/Dvpvklr.png";
               Picasso.get().load("http://i.imgur.com" + path).into(holder.listMovieNumberView);

       }
       else{

           String path = mPosterPath[position];
           Picasso.get().load("http://image.tmdb.org/t/p/w185/" + path).into(holder.listMovieNumberView);
       }
   }
    public int getItemCount(){
        if (mPosterPath != null){return mPosterPath.length;}

       return mNumberMovies;
    }



    class MovieViewHolder extends RecyclerView.ViewHolder  {
        ImageView listMovieNumberView;

            public MovieViewHolder(View itemView){
                super(itemView);
                listMovieNumberView = (ImageView) itemView.findViewById(R.id.iv_movie_item);
                listMovieNumberView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = getAdapterPosition();
                        MovieData movie =mMovieDataList.get(adapterPosition);
                        Context context = v.getContext();
                        //Bundle b = new Bundle();
                        //String key = "movieInfo";
                        //b.putStringArray(key, mPosterPath);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("movieInfo", movie);
                        //intent.putExtra("adapterPosition", adapterPosition);
                        context.startActivity(intent);
                    }
                });
            }

            void bind(int movieIndex){
                //listMovieNumberView.setText(String.valueOf(movieIndex));
            }
        }
        public void setmPosterPath(ArrayList<MovieData> movieDataList){
        if (movieDataList == null){
            mMovieDataList = null;

        }
        else{
       this.mMovieDataList = movieDataList;

            for (int j = 0; j < mMovieDataList.size(); j++){
                mPosterPath[j]= mMovieDataList.get(j).getPath();
            }}



    }}

