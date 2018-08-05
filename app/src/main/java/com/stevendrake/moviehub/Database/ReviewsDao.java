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
public interface ReviewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReview(Review... reviews);

    @Query("SELECT * FROM reviews_table")
    LiveData<List<Review>> getAllReviews();

    @Query("SELECT * FROM reviews_table WHERE review_movie_id = :idIn")
    LiveData<List<Review>> getSelectedReviews(String idIn);

    @Query("DELETE FROM reviews_table")
    void deleteAllReviews();
}
