package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Entity(tableName = "favorites_table")
public class Favorite {

    @PrimaryKey
    @NonNull
    private String favMovieId;
    private String favTitle;
    private Long favRating;
    private String favDescription;
    private String favPoster;
    private String favBackdrop;
    private String favReleased;

    public void setFavMovieId(String newFavMovieId){favMovieId = newFavMovieId;}
    public void setFavTitle(String newFavTitle){favTitle = newFavTitle;}
    public void setFavRating(Long newFavRating){favRating = newFavRating;}
    public void setFavDescription(String newFavDescription){favDescription = newFavDescription;}
    public void setFavPoster(String newFavPoster){favPoster = newFavPoster;}
    public void setFavBackdrop(String newFavBackdrop){favBackdrop = newFavBackdrop;}
    public void setFavReleased(String newFavReleased){favReleased = newFavReleased;}

    public String getFavMovieId(){return favMovieId;}
    public String getFavTitle(){return favTitle;}
    public Long getFavRating(){return favRating;}
    public String getFavDescription(){return favDescription;}
    public String getFavPoster(){return favPoster;}
    public String getFavBackdrop(){return favBackdrop;}
    public String getFavReleased(){return favReleased;}
}
