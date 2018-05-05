package com.semckinley.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
   private int mNumberMovies;
    private String [] mPosterPath;
    private int movieCount;
   public MovieAdapter(String[] posterPath){
      mPosterPath = posterPath;


   }

   @Override
   public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
       Context context = viewGroup.getContext();
       int layoutIdForMovieItem = R.layout.movie_list;
       LayoutInflater inflater = LayoutInflater.from(context);
       boolean shouldAttachToParentImmediately = false;
       View view = inflater.inflate(layoutIdForMovieItem, viewGroup, shouldAttachToParentImmediately);
       MovieViewHolder viewHolder = new MovieViewHolder(view);
        movieCount++;

       return viewHolder;

   }
   @Override
   public void onBindViewHolder(MovieViewHolder holder, int position){
       holder.bind(position);
       for (int i = 0; i< mPosterPath.length; i++) {
           String path = mPosterPath[i];
           Picasso.get().load("http://i.imgur.com" + path).into(holder.listMovieNumberView);
       }
   }
    public int getItemCount(){
        return mNumberMovies;
    }

        class MovieViewHolder extends RecyclerView.ViewHolder  {
        ImageView listMovieNumberView;

            public MovieViewHolder(View itemView){
                super(itemView);
                listMovieNumberView = (ImageView) itemView.findViewById(R.id.iv_movie_item);

            }
            void bind(int movieIndex){
                //listMovieNumberView.setText(String.valueOf(movieIndex));
            }
        }
        public void setmPosterPath(String[] posterPath){this.mPosterPath = posterPath;}
}
