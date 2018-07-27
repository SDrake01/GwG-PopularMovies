package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FavReviewsDao {

    @Insert
    void insertFavReview(FavReview... favReviews);

    @Query("SELECT * FROM fav_reviews_table")
    List<FavReview> getAllFavReviews();
}
