package com.example.popularmoviesapp.utilities;

import android.net.Uri;

import com.example.popularmoviesapp.BuildConfig;

import java.net.MalformedURLException;
import java.net.URL;

public final class NetworkUtils {
    private static final String MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API = "api_key";
    private static final String api_key = BuildConfig.API_KEY;

    public static URL buildUrl(String sortBy) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                .appendPath(sortBy)
                .appendQueryParameter(API, api_key)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    };


}
