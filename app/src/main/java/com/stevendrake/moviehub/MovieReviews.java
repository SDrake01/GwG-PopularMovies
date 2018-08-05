package com.stevendrake.moviehub;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.stevendrake.moviehub.Database.Review;

import java.util.List;

/**
 * Created by calebsdrake on 6/17/2018.
 */

public class MovieReviews extends AppCompatActivity {

    private ReviewViewModel reviewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_reviews);

        RecyclerView reviewsRecyclerView = findViewById(R.id.rv_movie_reviews_recycler);
        final ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        reviewViewModel.getViewSelectedReview().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable final List<Review> reviews){
                reviewsAdapter.setReviews(reviews);
            }
        });

        // Use this toast to verify that I can set and get data to/from the database
        Toast.makeText(getApplicationContext(), MovieData.reviewFilmId, Toast.LENGTH_LONG).show();
    }
}
