package com.example.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private final int mVoteCount;
    private final int mId;
    private final boolean mVideo;
    private final double mVoteAverage;
    private final String mTitle;
    private final double mPopularity;
    private final String mPosterPath;
    private final String mOriginalLanguage;
    private final String mOriginalTitle;
 //   private final int[] mGenreIds;
    private final String mBackdropPath;
    private final boolean mAdult;
    private final String mOverview;
    private final String mReleaseDate;

    public int getmVoteCount() {
        return mVoteCount;
    }

    public int getmId() {
        return mId;
    }

    public boolean ismVideo() {
        return mVideo;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public double getmPopularity() {
        return mPopularity;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public boolean ismAdult() {
        return mAdult;
    }

    public String getmOverview() {
        return mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public Movie(int mVoteCount, int mId, boolean mVideo, double mVoteAverage, String mTitle, double mPopularity, String mPosterPath, String mOriginalLanguage, String mOriginalTitle, String mBackdropPath, boolean mAdult, String mOverview, String mReleaseDate) {
        this.mVoteCount = mVoteCount;
        this.mId = mId;
        this.mVideo = mVideo;
        this.mVoteAverage = mVoteAverage;
        this.mTitle = mTitle;
        this.mPopularity = mPopularity;
        this.mPosterPath = mPosterPath;
        this.mOriginalLanguage = mOriginalLanguage;
        this.mOriginalTitle = mOriginalTitle;
   //     this.mGenreIds = mGenreIds;
        this.mBackdropPath = mBackdropPath;
        this.mAdult = mAdult;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
    }


    protected Movie(Parcel in) {
        mVoteCount = in.readInt();
        mId = in.readInt();
        mVideo = in.readByte() != 0;
        mVoteAverage = in.readDouble();
        mTitle = in.readString();
        mPopularity = in.readDouble();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
  //      mGenreIds = in.createIntArray();
        mBackdropPath = in.readString();
        mAdult = in.readByte() != 0;
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVoteCount);
        dest.writeInt(mId);
        dest.writeByte((byte) (mVideo ? 1 : 0));
        dest.writeDouble(mVoteAverage);
        dest.writeString(mTitle);
        dest.writeDouble(mPopularity);
        dest.writeString(mPosterPath);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mOriginalTitle);
     //   dest.writeIntArray(mGenreIds);
        dest.writeString(mBackdropPath);
        dest.writeByte((byte) (mAdult ? 1 : 0));
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
    }
}
