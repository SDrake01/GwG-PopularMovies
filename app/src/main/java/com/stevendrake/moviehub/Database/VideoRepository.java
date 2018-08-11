package com.stevendrake.moviehub.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.stevendrake.moviehub.MovieData;

import java.util.List;

/**
 * Created by calebsdrake on 8/6/2018.
 */

public class VideoRepository {

    private VideoDao repoVideoDao;
    private LiveData<List<Video>> repoSelectedVideos;
    String requestId;

    public VideoRepository(Application application){
        requestId = MovieData.videoFilmId;
        FilmDatabase videosRepoDb = FilmDatabase.getDatabase(application);
        repoVideoDao = videosRepoDb.videoDao();
        repoSelectedVideos = repoVideoDao.getSelectedVideos(requestId);
    }

    public LiveData<List<Video>> getRepoSelectedVideos(){
        return repoSelectedVideos;
    }
}
