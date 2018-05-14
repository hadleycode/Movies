package com.example.popularmoviesapp;

public class Movie {
    private final int mVoteCount;
    private final int mId;
    private final boolean mVideo;
    private final float mVoteAverage;
    private final String mtitle;
    private final float mPopularity;
    private final String mPosterPath;
    private final String mOriginalLanguage;
    private final String mOriginalTitle;
    private final int[] mGenreIds;
    private final String mBackdropPath;
    private final boolean mAdult;
    private final String mOverview;
    private final String mReleaseDate;

    public Movie(String title) {
        mtitle = title;
    }

}
