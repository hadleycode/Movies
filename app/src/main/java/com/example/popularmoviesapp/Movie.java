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

// --Commented out by Inspection START (5/25/18, 5:53 PM):
//    public int getVoteCount() {
//        return mVoteCount;
//    }
// --Commented out by Inspection STOP (5/25/18, 5:53 PM)

// --Commented out by Inspection START (5/25/18, 5:53 PM):
//    public int getId() {
//        return mId;
//    }
// --Commented out by Inspection STOP (5/25/18, 5:53 PM)

// --Commented out by Inspection START (5/25/18, 5:53 PM):
//    public boolean isVideo() {
//        return mVideo;
//    }
// --Commented out by Inspection STOP (5/25/18, 5:53 PM)

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

// --Commented out by Inspection START (5/25/18, 5:53 PM):
//    public double getPopularity() {
//        return mPopularity;
//    }
// --Commented out by Inspection STOP (5/25/18, 5:53 PM)

    public String getPosterPath() {
        return mPosterPath;
    }

// --Commented out by Inspection START (5/25/18, 5:53 PM):
//    public String getOriginalLanguage() {
//        return mOriginalLanguage;
//    }
// --Commented out by Inspection STOP (5/25/18, 5:53 PM)

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

// --Commented out by Inspection START (5/25/18, 5:53 PM):
//    public boolean isAdult() {
//        return mAdult;
//    }
// --Commented out by Inspection STOP (5/25/18, 5:53 PM)

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
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


    private Movie(Parcel in) {
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
