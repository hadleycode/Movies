package com.example.popularmoviesapp.utilities;

import com.example.popularmoviesapp.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MoviesJsonUtils {

    private final static String MDB_RESULTS = "results";
    private final static String MDB_TITLE = "title";
    private final static String MDB_POSTER = "poster_path";
    private final static String MDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String MDB_POSTER_SIZE = "w185";
    private final static String MDB_POSTER_SPLASH_SIZE = "w342";
    private final static String MDB_VOTE_COUNT = "vote_count";
    private final static String MDB_ID = "id";
    private final static String MDB_VIDEO = "video";
    private final static String MDB_VOTE_AVERAGE = "vote_average";
    private final static String MDB_POPULARITY = "popularity";
    private final static String MDB_ORIGINAL_LANGUAGE = "original_language";
    private final static String MDB_ORIGINAL_TITLE = "original_title";
    //   final static String MDB_GENRE_IDS = "genre_ids";
    private final static String MDB_BACKDROP_PATH = "backdrop_path";
    private final static String MDB_ADULT = "adult";
    private final static String MDB_OVERVIEW = "overview";
    private final static String MD_RELEASE_DATE = "release_date";

    public static Movie[] getMoviesFromJson(String moviesJsonStr) throws JSONException {
        Movie[] parsedMovies;
        int voteCount, id;
        //    int[] genreIds;
        boolean video, adult;
        double voteAverage, popularity;
        String title, posterPath, originalLanguage, originalTitle, backdropPath, overview, releaseDate;

        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray(MDB_RESULTS);
        parsedMovies = new Movie[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movie = moviesArray.getJSONObject(i);
            voteCount = movie.optInt(MDB_VOTE_COUNT);
            id = movie.optInt(MDB_ID);
            video = movie.optBoolean(MDB_VIDEO);
            adult = movie.optBoolean(MDB_ADULT);
            voteAverage = movie.optDouble(MDB_VOTE_AVERAGE);
            popularity = movie.optDouble(MDB_POPULARITY);
            title = movie.optString(MDB_TITLE);
            posterPath = MDB_POSTER_BASE_URL + MDB_POSTER_SIZE + movie.optString(MDB_POSTER);
            originalLanguage = movie.optString(MDB_ORIGINAL_LANGUAGE);
            originalTitle = movie.optString(MDB_ORIGINAL_TITLE);
            backdropPath = MDB_POSTER_BASE_URL + MDB_POSTER_SPLASH_SIZE + movie.optString(MDB_BACKDROP_PATH);
            overview = movie.optString(MDB_OVERVIEW);
            releaseDate = movie.optString(MD_RELEASE_DATE);

            parsedMovies[i] = new Movie(voteCount, id, video, voteAverage, title, popularity, posterPath, originalLanguage, originalTitle, backdropPath, adult, overview, releaseDate);
        }

        return parsedMovies;


    }

}
