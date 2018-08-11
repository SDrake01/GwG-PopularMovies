package com.stevendrake.moviehub;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stevendrake.moviehub.Database.Video;

import java.util.List;

/**
 * Created by calebsdrake on 6/17/2018.
 */

public class MovieTrailers extends AppCompatActivity {

    private VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_trailers);

        RecyclerView videosRecyclerView = findViewById(R.id.rv_movie_trailers_recycler);
        final VideoAdapter videoAdapter = new VideoAdapter(this);
        videosRecyclerView.setAdapter(videoAdapter);
        videosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        videoViewModel.getViewSelectedVideo().observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(@Nullable final List<Video> videos) {
                videoAdapter.setVideos(videos);
            }
        });
    }
}
