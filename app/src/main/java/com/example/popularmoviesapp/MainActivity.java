package com.example.popularmoviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.popularmoviesapp.utilities.MoviesJsonUtils;
import com.example.popularmoviesapp.utilities.NetworkUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private TextView testResponseTextView;
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testResponseTextView = (TextView) findViewById(R.id.tv_test_response);

        loadMovieData();


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_grid);
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

    //to implement
    public class FetchMoviesTask extends AsyncTask<URL, Void, String[]> {

        @Override
        protected String[] doInBackground(URL... urls) {
            URL url = urls[0];
            String response = null;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(url);

                String[] testParsedJson = MoviesJsonUtils.getImageLinksFromJson(response);

                return testParsedJson;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] s) {
            int i = 0;
            while (i < s.length) {
                testResponseTextView.append("\n" + s[i]);
                i++;
            }
            mMovieAdapter.setImageLinks(s);
        }
    }
}
