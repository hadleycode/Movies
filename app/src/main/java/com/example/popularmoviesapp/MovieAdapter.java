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

    private Movie[] mMovies;

    final private MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Context context = holder.listItemImageView.getContext();
        ImageView imageView = holder.listItemImageView;
        String imageLink = mMovies[position].getPosterPath();

        Picasso.with(context).load(imageLink).into(imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null)
            return 0;
        return mMovies.length;
    }

    public void setImageLinks(Movie[] movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView listItemImageView;

        private ImageViewHolder(View view) {
            super(view);
            listItemImageView = itemView.findViewById(R.id.iv_movie_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = mMovies[position];
            mClickHandler.onClick(movie);
        }
    }


}
