package com.semckinley.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    static Context mContext;
   public MovieAdapter(int movieCount){
      //mPosterPath = posterPath;
    //mContext = context;
    mNumberMovies = movieCount;
    Log.d("MovieAdapter","Adapter Constructor Run" );

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

            }
            void bind(int movieIndex){
                //listMovieNumberView.setText(String.valueOf(movieIndex));
            }
        }
        public void setmPosterPath(String[] posterPath){this.mPosterPath = posterPath;}
}
