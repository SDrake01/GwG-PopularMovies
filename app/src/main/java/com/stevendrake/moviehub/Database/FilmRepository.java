package com.stevendrake.moviehub.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.stevendrake.moviehub.MovieData;

import java.util.List;

/**
 * Created by calebsdrake on 7/26/2018.
 */

public class FilmRepository {

    private LiveData<List<Film>> repoPopularMovies;
    private LiveData<List<Film>> repoTopRatedMovies;
    private LiveData<List<Film>> repoFavoriteMovies;
    private String movieFilter;

    public FilmRepository(Application application){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(application);
        movieFilter = preferences.getString("sort_setting", "");
        MovieData.setTestingString(movieFilter);
        FilmDatabase repoDb = FilmDatabase.getDatabase(application);
        FilmDao repoFilmDao = repoDb.filmDao();
        repoPopularMovies = repoFilmDao.getPopularMovies();
        repoTopRatedMovies = repoFilmDao.getTopRatedMovies();
        repoFavoriteMovies = repoFilmDao.getFavoriteMovies();
    }

    public LiveData<List<Film>> repoGetMoviesList(){
        if (movieFilter.equals("popular")) {
            return repoPopularMovies;
        }else if (movieFilter.equals("top_rated")){
            return repoTopRatedMovies;
        }else if (movieFilter.equals("favorite")){
            return repoFavoriteMovies;
        }else {
            return repoPopularMovies;
        }
    }
}
