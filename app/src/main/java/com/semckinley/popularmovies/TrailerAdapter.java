package com.semckinley.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.semckinley.popularmovies.sampledata.MovieData;

import java.util.ArrayList;

/**
 * Created by stephen.mckinley on 5/19/18.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.semckinley.popularmovies.sampledata.TrailerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class TrailerAdapter extends RecyclerView.Adapter<com.semckinley.popularmovies.TrailerAdapter.TrailerViewHolder> {
        private int mNumberTrailers;
        private String [] mTrailerPath;
        //private int movieCount;
        static Context mContext;
       ArrayList<TrailerData> mTrailerList;



        public TrailerAdapter(int trailerCount){

            mNumberTrailers = trailerCount;


        }

        @Override
        public TrailerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            Context context = viewGroup.getContext();
            Log.d("onCreateViewHolder", "onCreateViewHolder Started");
            int layoutIdForTrailerItem = R.layout.trailer_list;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;
            View view = inflater.inflate(layoutIdForTrailerItem, viewGroup, shouldAttachToParentImmediately);
            TrailerAdapter.TrailerViewHolder viewHolder = new TrailerAdapter.TrailerViewHolder(view);

            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder holder, int position) {
        holder.listTrailerView.setText("This is Filler");
        }

        @Override
        public int getItemCount() {
            return 0;
        }

class TrailerViewHolder extends RecyclerView.ViewHolder  {
    Button listTrailerView;

    public TrailerViewHolder(View itemView){
        super(itemView);
        listTrailerView = (Button) itemView.findViewById(R.id.bt_trailer_list);
       /* listTrailerView.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

    void bind(int movieIndex){
        //listMovieNumberView.setText(String.valueOf(movieIndex));
    }
}

}
