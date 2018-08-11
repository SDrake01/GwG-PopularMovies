package com.stevendrake.moviehub.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by calebsdrake on 7/22/2018.
 */

@Dao
public interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFilm(Film... films);

    @Delete
    void deleteOneFilm(Film film);

    @Update
    void updateOneFilm(Film film);

    @Query("DELETE FROM film_table")
    public void deleteAllFilms();

    @Query("SELECT * FROM film_table WHERE sort = 'popular' ORDER BY popularity DESC")
    LiveData<List<Film>> getPopularMovies();

    @Query("SELECT * FROM film_table WHERE sort = 'top_rated' ORDER BY rating DESC")
    LiveData<List<Film>> getTopRatedMovies();

    @Query("SELECT * FROM film_table WHERE favorite = 'favorite' ORDER BY title ASC")
    LiveData<List<Film>> getFavoriteMovies();

    @Query("SELECT * FROM film_table WHERE sort = 'popular' ORDER BY popularity DESC")
    List<Film> getPopularFilms();

    @Query("SELECT * FROM film_table WHERE sort = 'top_rated' ORDER BY rating DESC")
    List<Film> getTopRatedFilms();

    @Query("SELECT * FROM film_table WHERE favorite = 'favorite' ORDER BY title ASC")
    List<Film> getFavoriteFilms();

    @Query("SELECT title FROM film_table WHERE id = :idIn")
    String getOneFilmTitle(String idIn);
}
