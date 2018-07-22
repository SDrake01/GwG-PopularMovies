package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Entity(tableName = "film_table")
public class Film {

    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private Long rating;
    private Long popularity;
    private String description;
    private String poster;
    private String backdrop;
    private String released;
    private String sort;

    public void setId(String newId){this.id = newId;}
    public void setTitle(String newTitle){this.title = newTitle;}
    public void setRating(Long newRating){this.rating = newRating;}
    public void setPopularity(Long newPopularity){this.popularity = newPopularity;}
    public void setDescription(String newDescription){this.description = newDescription;}
    public void setPoster(String newPoster){this.poster = newPoster;}
    public void setBackdrop(String newBackdrop){this.backdrop = newBackdrop;}
    public void setReleased(String newReleased){this.released = newReleased;}
    public void setSort(String newSort){this.sort = newSort;}

    public String getId(){return id;}
    public String getTitle(){return title;}
    public Long getRating(){return rating;}
    public Long getPopularity(){return popularity;}
    public String getDescription(){return description;}
    public String getPoster(){return poster;}
    public String getBackdrop(){return backdrop;}
    public String getReleased(){return released;}
    public String getSort(){return sort;}
}
