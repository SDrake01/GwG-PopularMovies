package com.stevendrake.moviehub.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by calebsdrake on 7/26/2018.
 */

public class FilmRepository {

    private FilmDao repoFilmDao;
    private LiveData<List<Film>> repoPopularFilms;

    public FilmRepository(Application application){
        FilmDatabase repoDb = FilmDatabase.getDatabase(application);
        repoFilmDao = repoDb.filmDao();
        repoPopularFilms = repoFilmDao.getPopularMovies();
    }

    public LiveData<List<Film>> repoGetPopularMovies(){
        return repoPopularFilms;
    }
}
