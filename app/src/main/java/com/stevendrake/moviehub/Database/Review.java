package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Entity(tableName = "reviews_table", indices = @Index(value = {"id"}, unique = true))
public class Review {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int rkey;
    @ColumnInfo(name = "review_movie_id")
    private String reviewMovieId;
    private String id;
    private int results;
    private String author;
    private String contents;

    public void setRkey(int newRkey){this.rkey = newRkey;}
    public void setReviewMovieId(String newMovieId){this.reviewMovieId = newMovieId;}
    public void setId(String newId){this.id = newId;}
    public void setResults(int newResults){this.results = newResults;}
    public void setAuthor(String newAuthor){this.author = newAuthor;}
    public void setContents(String newContents){this.contents = newContents;}

    public int getRkey(){return rkey;}
    public String getReviewMovieId(){return reviewMovieId;}
    public String getId(){return id;}
    public int getResults(){return results;}
    public String getAuthor(){return author;}
    public String getContents(){return contents;}
}
