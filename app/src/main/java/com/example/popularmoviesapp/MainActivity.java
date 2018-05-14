package com.example.popularmoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.popularmoviesapp.utilities.MoviesJsonUtils;
import com.example.popularmoviesapp.utilities.NetworkUtils;

import java.net.URL;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMovieData();

        mRecyclerView = findViewById(R.id.rv_movie_grid);
        int numberOfCol = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfCol);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    private void loadMovieData() {
        //Can be changed to "top_rated"
        String sortBy = "popular";

        URL builtUrl = NetworkUtils.buildUrl(sortBy);
        Log.d("Url", "The URL is : " + builtUrl);

        OnTaskCompleted onTaskCompleted = new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(Movie[] movies) {
                mMovieAdapter.setImageLinks(movies);
            }
        };

        new FetchMoviesTask(onTaskCompleted).execute(builtUrl);
    }

    @Override
    public void onClick(String movieTitle) {
        Toast mToast;

        mToast = Toast.makeText(this, movieTitle, Toast.LENGTH_SHORT);
        mToast.show();

    }

    private class FetchMoviesTask extends AsyncTask<URL, Void, Movie[]> {

        private final OnTaskCompleted mListener;

        public FetchMoviesTask(OnTaskCompleted listener) {
            mListener = listener;
        }

        @Override
        protected Movie[] doInBackground(URL... urls) {
            URL url = urls[0];
            String response;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(url);

                return MoviesJsonUtils.getMoviesFromJson(response);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            mListener.onTaskCompleted(movies);
        }
    }
}
