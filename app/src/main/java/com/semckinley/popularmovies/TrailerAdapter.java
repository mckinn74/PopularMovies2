package com.semckinley.popularmovies;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
            mContext = viewGroup.getContext();
            Log.d("onCreateViewHolder", "onCreateViewHolder Started");
            int layoutIdForTrailerItem = R.layout.trailer_list;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            boolean shouldAttachToParentImmediately = false;
            View view = inflater.inflate(layoutIdForTrailerItem, viewGroup, shouldAttachToParentImmediately);
            TrailerViewHolder viewHolder = new TrailerViewHolder(view);

            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder holder, int position) {
        holder.bind(position);
        if(mTrailerList == null){
            holder.listTrailerView.setText(R.string.no_trailer);
        }
        else {
            holder.listTrailerView.setText(mTrailerList.get(position).getName());

            }Log.d("onBindViewHolder", "onBindViewHolder"+ position);
        }

        @Override
        public int getItemCount() {
            if(mTrailerList !=null){return mTrailerList.size();}
            return 1;
        }

class TrailerViewHolder extends RecyclerView.ViewHolder  {
    Button listTrailerView;

    public TrailerViewHolder(View itemView){
        super(itemView);
        listTrailerView = (Button) itemView.findViewById(R.id.bt_trailer_list);
       listTrailerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = getAdapterPosition();

                TrailerData trailer =mTrailerList.get(adapterPosition);
                String key = trailer.getKey();
                String url = "http://youtube.com/watch?v=" + key;
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if(intent.resolveActivity(mContext.getPackageManager()) != null){
                    mContext.startActivity(intent);
                }



            }
        });
    }


    void bind(int movieIndex){

    }
}

        public void setmTrailerPath(ArrayList<TrailerData> trailerList){
            this.mTrailerList = trailerList;
           }

}
