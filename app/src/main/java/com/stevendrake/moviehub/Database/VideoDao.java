package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface VideoDao {

    @Insert
    void insertVideo(Video... videos);

    @Query("SELECT video_movie_id,vidkey,name,site,type FROM videos_table")
    List<Video> getAllVideos();
}
