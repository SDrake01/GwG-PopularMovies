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

    private FilmDao repoFilmDao;
    private LiveData<List<Film>> repoPopularMovies;
    private LiveData<List<Film>> repoTopRatedMovies;
    private LiveData<List<Film>> repoFavoriteMovies;
//    private List<Film> repoPopularFilms;
//    private List<Film> repoTopRatedFilms;
//    private List<Film> repoFavoriteFilms;
    private SharedPreferences preferences;
    String movieFilter;

    public FilmRepository(Application application){
        preferences = PreferenceManager.getDefaultSharedPreferences(application);
        movieFilter = preferences.getString("sort_setting", "");
        MovieData.setTestingString(movieFilter);
        FilmDatabase repoDb = FilmDatabase.getDatabase(application);
        repoFilmDao = repoDb.filmDao();
        repoPopularMovies = repoFilmDao.getPopularMovies();
        repoTopRatedMovies = repoFilmDao.getTopRatedMovies();
        repoFavoriteMovies = repoFilmDao.getFavoriteMovies();
//        repoPopularFilms = repoFilmDao.getPopularFilms();
//        repoTopRatedFilms = repoFilmDao.getTopRatedFilms();
//        repoFavoriteFilms = repoFilmDao.getFavoriteFilms();
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

//    public List<Film> repoGetFilms(){
//        switch (movieFilter){
//            case "popular":
//                return repoPopularFilms;
//            case "top_rated":
//                return repoTopRatedFilms;
//            case "favorite":
//                return repoFavoriteFilms;
//        }
//        return null;
//    }
}
