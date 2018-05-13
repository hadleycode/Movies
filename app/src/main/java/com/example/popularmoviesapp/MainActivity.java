package com.example.popularmoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.popularmoviesapp.utilities.MoviesJsonUtils;
import com.example.popularmoviesapp.utilities.NetworkUtils;

import java.net.URL;


public class MainActivity extends AppCompatActivity {

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

        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    private void loadMovieData() {
        //Can be changed to "top_rated"
        String sortBy = "popular";

        URL builtUrl = NetworkUtils.buildUrl(sortBy);
        Log.d("Url", "The URL is : " + builtUrl);

        new FetchMoviesTask().execute(builtUrl);
    }

    private class FetchMoviesTask extends AsyncTask<URL, Void, String[]> {

        @Override
        protected String[] doInBackground(URL... urls) {
            URL url = urls[0];
            String response;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(url);

                return MoviesJsonUtils.getImageLinksFromJson(response);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] s) {
            mMovieAdapter.setImageLinks(s);
        }
    }
}
