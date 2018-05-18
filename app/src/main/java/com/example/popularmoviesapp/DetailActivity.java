package com.example.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView movieTitleTextView = findViewById(R.id.tv_movie_title);
        TextView originalTitleTextView = findViewById(R.id.tv_original_title);
        TextView originalLanguageTextView = findViewById(R.id.tv_original_language);
        TextView overviewTextView = findViewById(R.id.tv_overview);
        TextView releaseDateTextView = findViewById(R.id.tv_release_date);
        ImageView posterImageView = findViewById(R.id.iv_poster);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        movieTitleTextView.setText("Title : " + movie.getmTitle());
        originalTitleTextView.setText("Original Title : " + movie.getmOriginalTitle());
        originalLanguageTextView.setText("Original Language : " + movie.getmOriginalLanguage());
        overviewTextView.setText("Overview : " + movie.getmOverview());
        releaseDateTextView.setText("Released on : " + movie.getmReleaseDate());

        Context context = DetailActivity.this;
        String imageLink = movie.getmPosterPath();

        Picasso.with(context).load(imageLink).into(posterImageView);


    }
}
