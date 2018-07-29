package com.stevendrake.moviehub;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.stevendrake.moviehub.Database.Film;
import com.stevendrake.moviehub.Database.FilmRepository;

import java.util.List;

/**
 * Created by calebsdrake on 7/26/2018.
 */

public class FilmViewModel extends AndroidViewModel {

    private FilmRepository viewRepository;
    private LiveData<List<Film>> viewMoviesList;

    public FilmViewModel(@NonNull Application application) {
        super(application);

        viewRepository = new FilmRepository(application);
        viewMoviesList = viewRepository.repoGetMoviesList();
    }

    LiveData<List<Film>> getMoviesList(){
        return viewMoviesList;
    }
}
