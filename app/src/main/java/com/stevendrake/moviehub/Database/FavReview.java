package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Entity(tableName = "fav_reviews_table")
public class FavReview {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int frkey;
    @ColumnInfo(name = "fav_review_movie_id")
    private String favReviewMovieId;
    private int favResults;
    private String favAuthor;
    private String favContents;

    public void setFavReviewMovieId(String newFavReviewMovieId){this.favReviewMovieId = newFavReviewMovieId;}
    public void setFavResults(int newFavResults){this.favResults = newFavResults;}
    public void setFavAuthor(String newFavAuthor){this.favAuthor = newFavAuthor;}
    public void setFavContents(String newFavContents){this.favContents = newFavContents;}

    public String getFavReviewMovieId(){return favReviewMovieId;}
    public int getFavResults(){return favResults;}
    public String getFavAuthor(){return favAuthor;}
    public String getFavContents(){return favContents;}
}
