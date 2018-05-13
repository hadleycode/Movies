package com.example.popularmoviesapp.utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MoviesJsonUtils {

    public static String[] getImageLinksFromJson(String moviesJsonStr) throws JSONException {
        final String MDB_RESULTS = "results";
        final String MDB_TITLE = "title";
        final String MDB_POSTER = "poster_path";

        String[] parsedMovieData = null;

        Log.d("Json", "The string to parse is " + moviesJsonStr);

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        JSONArray moviesArray = moviesJson.getJSONArray(MDB_RESULTS);

        parsedMovieData = new String[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            String posterPath;
            String title;

            JSONObject movie = moviesArray.getJSONObject(i);

            posterPath = movie.getString(MDB_POSTER);
            title = movie.getString(MDB_TITLE);

            parsedMovieData[i] = title + " = " + posterPath;
        }

        return parsedMovieData;
    }

}
