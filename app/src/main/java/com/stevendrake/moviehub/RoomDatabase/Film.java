package com.stevendrake.moviehub.RoomDatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/1/2018.
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

    public Film(){
    }

    public String getId(){return id;}
    public String getTitle(){return title;}
    public Long getRating(){return rating;}
    public Long getPopularity(){return popularity;}
    public String getDescription(){return description;}
    public String getPoster(){return poster;}
    public String getBackdrop(){return backdrop;}
    public String getReleased(){return released;}
    public String getSort(){return sort;}

    public void setId(String id){this.id = id;}
    public void setTitle(String title){this.title = title;}
    public void setRating(Long rating){this.rating = rating;}
    public void setPopularity(Long popularity){this.popularity = popularity;}
    public void setDescription(String description){this.description = description;}
    public void setPoster(String poster){this.poster = poster;}
    public void setBackdrop(String backdrop){this.backdrop = backdrop;}
    public void setReleased(String released){this.released = released;}
    public void setSort(String sort){this.sort = sort;}

}
