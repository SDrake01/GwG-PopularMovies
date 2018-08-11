package com.stevendrake.moviehub;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.stevendrake.moviehub.Database.Video;
import com.stevendrake.moviehub.Database.VideoRepository;

import java.util.List;

/**
 * Created by calebsdrake on 8/6/2018.
 */

class VideoViewModel extends AndroidViewModel {

    private LiveData<List<Video>> viewSelectedVideo;

    public VideoViewModel(@NonNull Application application) {
        super(application);

        VideoRepository videoRepository = new VideoRepository(application);
        viewSelectedVideo = videoRepository.getRepoSelectedVideos();
    }

    LiveData<List<Video>> getViewSelectedVideo(){
        return viewSelectedVideo;
    }
}
