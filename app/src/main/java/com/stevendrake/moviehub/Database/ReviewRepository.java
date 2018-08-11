package com.stevendrake.moviehub.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.stevendrake.moviehub.MovieData;

import java.util.List;

/**
 * Created by calebsdrake on 7/30/2018.
 */

public class ReviewRepository {

    private LiveData<List<Review>> repoSelectedReviews;

    public ReviewRepository(Application application){
        String requestId = MovieData.reviewFilmId;
        FilmDatabase reviewsRepoDb = FilmDatabase.getDatabase(application);
        ReviewsDao repoReviewsDao = reviewsRepoDb.reviewsDao();
        repoSelectedReviews = repoReviewsDao.getSelectedReviews(requestId);
    }

    public LiveData<List<Review>> getRepoSelectedReviews(){
        return repoSelectedReviews;
    }
}
