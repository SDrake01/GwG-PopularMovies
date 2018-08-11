package com.stevendrake.moviehub.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVideo(Video... videos);

    @Query("SELECT * FROM videos_table WHERE video_movie_id = :vidIdIn")
    LiveData<List<Video>> getSelectedVideos(String vidIdIn);
}
