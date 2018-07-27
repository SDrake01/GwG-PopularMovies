package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FavoritesDao {

    @Insert
    void insertFavorite(Favorite... favorites);

    @Query("SELECT * FROM favorites_table ORDER BY favTitle ASC")
    List<Favorite> getAllFavorites();
}
