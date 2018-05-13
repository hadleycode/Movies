package com.example.popularmoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ImageViewHolder> {

    private String[] mImageLinks;

    public MovieAdapter() {

    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView listItemImageView;

        public ImageViewHolder(View view) {
            super(view);
            listItemImageView = itemView.findViewById(R.id.iv_movie_item);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Context context = holder.listItemImageView.getContext();
        ImageView imageView = holder.listItemImageView;

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + mImageLinks[0]).into(imageView);



      // holder.listItemImageView.setImage(mImageLinks[position])
        //somethingsomething parsed from json
    }

    @Override
    public int getItemCount() {
        if (mImageLinks == null)
            return 0;
        return mImageLinks.length;
    }

    public void setImageLinks(String[] imageLinks) {
        mImageLinks = imageLinks;
        notifyDataSetChanged();
    }


}
