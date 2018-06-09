package com.semckinley.popularmovies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.semckinley.popularmovies.sampledata.ReviewData;
import com.semckinley.popularmovies.sampledata.TrailerData;

import java.util.ArrayList;

/**
 * Created by stephen.mckinley on 5/25/18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private int mNumberReviews;
    private String [] mReviewPath;

    static Context mContext;
    ArrayList<ReviewData> mReviewList;

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        Log.d("onCreateViewHolder", "onCreateViewHolder Started");
        int layoutIdForTrailerItem = R.layout.review_list;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForTrailerItem, parent, shouldAttachToParentImmediately);
        ReviewAdapter.ReviewViewHolder viewHolder = new ReviewAdapter.ReviewViewHolder(view);

        return viewHolder;
       // return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.bind(position);
        if(mReviewList == null){
            holder.listReviewView.setText(R.string.no_review);
        }
        else {

            holder.listReviewView.setText(mReviewList.get(position).getContent());
            if(position%2==0){
            holder.listReviewView.setBackgroundResource(R.color.colorPrimary);
            }
            else{
                holder.listReviewView.setBackgroundResource(R.color.my_white);

            }
            }


    }

    @Override
    public int getItemCount() {
        if (mReviewList == null){
        return 1;}
        else {
            return mReviewList.size();
        }

    }

class ReviewViewHolder extends RecyclerView.ViewHolder  {
    TextView listReviewView;

    public ReviewViewHolder(View itemView){
        super(itemView);
        listReviewView = (TextView) itemView.findViewById(R.id.tv_review_item);
        /*listTrailerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int adapterPosition = getAdapterPosition();

                TrailerData trailer =mReviewList.get(adapterPosition);
                String key = trailer.getKey();
                String url = "http://youtube.com/watch?v=" + key;
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if(intent.resolveActivity(mContext.getPackageManager()) != null){
                    mContext.startActivity(intent);
                }



            }
        });*/
    }


    void bind(int movieIndex){

    }
}
    public void setmReviewList(ArrayList<ReviewData> reviewList){
        this.mReviewList = reviewList;
    }
}
