package com.stevendrake.moviehub.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

/**
 * Created by calebsdrake on 7/26/2018.
 */

public class FilmRepository {

    private FilmDao repoFilmDao;
    private LiveData<List<Film>> repoPopularFilms;
    private LiveData<List<Film>> repoTopRatedFilms;
    private SharedPreferences preferences;
    String movieFilter;

    public FilmRepository(Application application){
        preferences = PreferenceManager.getDefaultSharedPreferences(application);
        movieFilter = preferences.getString("sort_setting", "");
        FilmDatabase repoDb = FilmDatabase.getDatabase(application);
        repoFilmDao = repoDb.filmDao();
        repoPopularFilms = repoFilmDao.getPopularMovies();
        repoTopRatedFilms = repoFilmDao.getTopRatedMovies();
    }

    public LiveData<List<Film>> repoGetMoviesList(){
        if (movieFilter.equals("popular")) {
            return repoPopularFilms;
        }else if (movieFilter.equals("top_rated")){
            return repoTopRatedFilms;
        }else {
            return repoPopularFilms;
        }
    }
}
