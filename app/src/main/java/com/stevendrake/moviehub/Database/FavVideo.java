package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Entity(tableName = "fav_videos_table")
public class FavVideo {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int fvkey;
    @ColumnInfo(name = "fav_video_movie_id")
    private String favVideoMovieId;
    private String favKey;
    private String favName;
    private String favSite;
    private String favType;

    public void setFavVideoMovieId(String newFavVideoMovieId){this.favVideoMovieId = newFavVideoMovieId;}
    public void setFavKey(String newFavKey){this.favKey = newFavKey;}
    public void setFavName(String newFavName){this.favName = newFavName;}
    public void setFavSite(String newFavSite){this.favSite = newFavSite;}
    public void setFavType(String newFavType){this.favType = newFavType;}

    public String getFavVideoMovieId(){return favVideoMovieId;}
    public String getFavKey(){return favKey;}
    public String getFavName(){return favName;}
    public String getFavSite(){return favSite;}
    public String getFavType(){return favType;}
}
