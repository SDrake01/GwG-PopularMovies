package com.stevendrake.moviehub.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilm(Film... films);

    @Query("SELECT * FROM film_table WHERE sort = 'popular' ORDER BY popularity DESC")
    List<Film> getPopularMovies();

    @Query("SELECT title FROM film_table WHERE id = :idIn")
    String getOneFilmTitle(String idIn);
}
