package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Entity(tableName = "videos_table")
public class Video {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int vkey;
    @ColumnInfo(name = "video_movie_id")
    private String videoMovieId;
    private String vidkey;
    private String name;
    private String site;
    private String type;

    public void setVkey(int newVkey){this.vkey = newVkey;}
    public void setVideoMovieId(String newVideoMovieId){this.videoMovieId = newVideoMovieId;}
    public void setVidkey (String newKey){this.vidkey = newKey;}
    public void setName (String newName){this.name = newName;}
    public void setSite (String newSite){this.site = newSite;}
    public void setType (String newType){this.type = newType;}

    public int getVkey(){return vkey;}
    public String getVideoMovieId(){return videoMovieId;}
    public String getVidkey(){return vidkey;}
    public String getName(){return name;}
    public String getSite(){return site;}
    public String getType(){return type;}

}
