package com.stevendrake.moviehub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by calebsdrake on 6/17/2018.
 */

public class MovieReviews extends AppCompatActivity {

    private ReviewsAdapter reviewsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_reviews);

        RecyclerView reviewsRecyclerView = findViewById(R.id.rv_movie_reviews_recycler);

        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);

        reviewsListAdapter = new ReviewsAdapter();

        reviewsRecyclerView.setAdapter(reviewsListAdapter);
    }
}
