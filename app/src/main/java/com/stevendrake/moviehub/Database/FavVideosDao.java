package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FavVideosDao {

    @Insert
    void insertFavVideo(FavVideo... favVideos);

    @Query("SELECT fav_video_movie_id,favKey,favName,favSite,favType FROM FavVideo")
    List<FavVideo> getAllFavVideos();
}
