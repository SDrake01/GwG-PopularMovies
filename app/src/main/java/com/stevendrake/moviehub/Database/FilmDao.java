package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FilmDao {

    @Insert
    void insertFilm(Film... films);

    @Query("SELECT * FROM film_table WHERE sort = 'popular' ORDER BY popularity DESC")
    List<Film> getPopularMovies();
}
