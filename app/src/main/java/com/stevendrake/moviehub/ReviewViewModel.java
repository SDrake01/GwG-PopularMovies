package com.stevendrake.moviehub;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.stevendrake.moviehub.Database.Review;
import com.stevendrake.moviehub.Database.ReviewRepository;

import java.util.List;

/**
 * Created by calebsdrake on 7/31/2018.
 */

public class ReviewViewModel extends AndroidViewModel {

    private ReviewRepository viewReviewRepository;
    private LiveData<List<Review>> viewSelectedReview;

    public ReviewViewModel(@NonNull Application application) {
        super(application);

        viewReviewRepository = new ReviewRepository(application);
        viewSelectedReview = viewReviewRepository.getRepoSelectedReviews();
    }

    LiveData<List<Review>> getViewSelectedReview(){
        return viewSelectedReview;
    }
}
