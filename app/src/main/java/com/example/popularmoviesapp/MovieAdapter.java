package com.example.popularmoviesapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MovieAdapter {

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView listItemImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
       //     listItemImageView = (ImageView) itemView.findViewById(R.id.);
        }

        void bind(int listIndex) {
            // listItemImageView.setImage //load image from API
        }
    }
}
