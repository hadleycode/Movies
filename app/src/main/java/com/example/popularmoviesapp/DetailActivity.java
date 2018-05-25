package com.example.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_detail);

        TextView movieTitleTextView = findViewById(R.id.tv_movie_title);
        TextView originalTitleTextView = findViewById(R.id.tv_original_title);
        TextView overviewTextView = findViewById(R.id.tv_overview);
        TextView releaseDateTextView = findViewById(R.id.tv_release_date);
        ImageView posterImageView = findViewById(R.id.iv_poster);
        ImageView splashPosterImageView = findViewById(R.id.iv_poster_splash);
        RatingBar movieScoreRatingBar = findViewById(R.id.rb_movie_score);
        TextView averageScoreTextView = findViewById(R.id.tv_average_score);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        movieTitleTextView.setText(movie.getTitle());
        if (movie.getTitle().equals(movie.getOriginalTitle())) {
            originalTitleTextView.setVisibility(View.GONE);
        } else
            originalTitleTextView.setText(movie.getOriginalTitle());

        overviewTextView.setText(getString(R.string.overview));
        overviewTextView.append(movie.getOverview());
        releaseDateTextView.setText(getString(R.string.released));
        releaseDateTextView.append(movie.getReleaseDate());

        Context context = DetailActivity.this;
        String imageLink = movie.getPosterPath();
        String splashImageLink = movie.getBackdropPath();
        Log.d("Splash Image", splashImageLink);

        float average = (float) movie.getVoteAverage();
        movieScoreRatingBar.setRating(average / 2);

        averageScoreTextView.setText(String.valueOf(movie.getVoteAverage()));
        averageScoreTextView.append(getString(R.string.out_of_ten));

        Picasso.with(context).load(imageLink).into(posterImageView);
        posterImageView.setContentDescription(getString(R.string.movie_poster_for) + movie.getTitle());
        Picasso.with(context).load(splashImageLink).into(splashPosterImageView);
        splashPosterImageView.setContentDescription(getString(R.string.large_movie_poster_for) + movie.getTitle());
    }
}
