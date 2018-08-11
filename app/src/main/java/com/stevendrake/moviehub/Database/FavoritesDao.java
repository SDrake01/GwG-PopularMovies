package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FavoritesDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertFavorite(Favorite... favorites);
//
//    @Query("DELETE FROM favorites_table WHERE favMovieId = :favIdIn")
//    void deleteOneFavorite(String favIdIn);
//
//    @Query("SELECT * FROM favorites_table ORDER BY favTitle ASC")
//    LiveData<List<Favorite>> getAllFavorites();
}
