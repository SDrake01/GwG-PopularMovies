package com.stevendrake.moviehub.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.stevendrake.moviehub.MovieData;

import java.util.List;

/**
 * Created by calebsdrake on 7/30/2018.
 */

public class ReviewRepository {

    private ReviewsDao repoReviewsDao;
    private LiveData<List<Review>> repoSelectedReviews;
    String requestId;

    public ReviewRepository(Application application){
        //requestId = MovieData.reviewFilmId;
        requestId = MovieData.reviewFilmId;
        FilmDatabase reviewsRepoDb = FilmDatabase.getDatabase(application);
        repoReviewsDao = reviewsRepoDb.reviewsDao();
        repoSelectedReviews = repoReviewsDao.getSelectedReviews(requestId);
        //repoSelectedReviews = repoReviewsDao.getAllReviews();
        //repoSelectedReviews = repoReviewsDao.getSelectedReviews("Gimly");
    }

    public LiveData<List<Review>> getRepoSelectedReviews(){
        return repoSelectedReviews;
    }
}
