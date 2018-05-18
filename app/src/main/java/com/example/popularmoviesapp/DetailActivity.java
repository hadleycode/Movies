package com.example.popularmoviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView movieTitleTextView = findViewById(R.id.tv_movie_title);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        movieTitleTextView.setText(movie.getmTitle());

    }
}
