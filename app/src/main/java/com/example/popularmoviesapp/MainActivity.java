package com.example.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.popularmoviesapp.utilities.MoviesJsonUtils;
import com.example.popularmoviesapp.utilities.NetworkUtils;

import java.net.URL;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
        mRecyclerView = findViewById(R.id.rv_movie_grid);

        loadMovieData("popular");

        int numberOfCol = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfCol);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    private void loadMovieData(String sortBy) {
        showLoading();
        URL builtUrl = NetworkUtils.buildUrl(sortBy);
        Log.d("Url", "The URL is : " + builtUrl);

        OnTaskCompleted onTaskCompleted = new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(Movie[] movies) {
                mMovieAdapter.setImageLinks(movies);
                hideLoading();
            }
        };

        if (isOnline()) {
            new FetchMoviesTask(onTaskCompleted).execute(builtUrl);
        } else
            showNetworkError();
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /* The isOnline function was taken from a Response by Gar on StackOverflow
    https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void showNetworkError() {

    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("movie", movie);
        startActivity(detailIntent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int popularity = R.id.action_sort_popularity;

        if (item.getItemId() == popularity) {
            loadMovieData("popular");
        } else
            loadMovieData("top_rated");
        return true;
    }
}
