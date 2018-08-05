package com.stevendrake.moviehub;

import android.content.Context;

import com.stevendrake.moviehub.Database.Film;
import com.stevendrake.moviehub.Database.FilmDao;
import com.stevendrake.moviehub.Database.Review;
import com.stevendrake.moviehub.Database.ReviewsDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by calebsdrake on 5/20/2018.
 */

public final class MovieJson {

    public static void parseMovieJsonData(String json) throws JSONException{

        // Create json object and array that hold the necessary json data
        JSONObject outerJson = new JSONObject(json);
        JSONArray fullArray = outerJson.getJSONArray("results");

        // Create the strings that will be used to build the urls for picasso
        String posterUrl = "https://image.tmdb.org/t/p/w185";
        String backdropUrl = "https://image.tmdb.org/t/p/w780";
        // Clear the MovieData arraylists before (re)populating them
        MovieData.movieTitles.clear();
        MovieData.movieRatings.clear();
        MovieData.movieReleaseDates.clear();
        MovieData.movieDescriptions.clear();
        MovieData.movieImageUrls.clear();
        MovieData.movieBackdropUrls.clear();
        MovieData.movieIdNumber.clear();
        // Iterate through the json array to build the view arrays
        if (fullArray != null) {
            for (int i = 0; i < fullArray.length(); i++) {
                JSONObject titleJson = fullArray.getJSONObject(i);
                MovieData.movieTitles.add(titleJson.getString("title"));
                MovieData.movieRatings.add(titleJson.getLong("vote_average"));
                MovieData.movieReleaseDates.add(titleJson.getString("release_date"));
                MovieData.movieDescriptions.add(titleJson.getString("overview"));
                MovieData.movieImageUrls.add(posterUrl + titleJson.getString("poster_path"));
                MovieData.movieBackdropUrls.add(backdropUrl + titleJson.getString("backdrop_path"));
                MovieData.movieIdNumber.add(titleJson.getString("id"));
            }
        }
    }

    public static void parseMovieJsonToDatabase(String json, String sortBy) throws JSONException{

        FilmDao jsonFilmDao = MainActivity.mainFilmDao;
        Context context = null;

        // Create json object and array that hold the json datak
        JSONObject outerJson = new JSONObject(json);
        JSONArray fullArray = outerJson.getJSONArray("results");

        // Create the strings that will be used to build the full urls
        String posterUrl = "https://image.tmdb.org/t/p/w185";
        String backdropUrl = "https://image.tmdb.org/t/p/w780";

        // Create an instance of Film to add to the database
        Film filmBuilder = new Film();

        // Iterate through the json array to build the object and pass it into the database
        // checking that the fullArray contains data (is not null)
        if (fullArray != null){
            for (int i = 0; i < fullArray.length(); i++){
                JSONObject jsonFilmData = fullArray.getJSONObject(i);

                filmBuilder.setId(jsonFilmData.getString("id"));
                filmBuilder.setTitle(jsonFilmData.getString("title"));
                filmBuilder.setRating(jsonFilmData.getLong("vote_average"));
                filmBuilder.setPopularity(jsonFilmData.getLong("popularity"));
                filmBuilder.setDescription(jsonFilmData.getString("overview"));
                filmBuilder.setPoster(posterUrl + jsonFilmData.getString("poster_path"));
                filmBuilder.setBackdrop(backdropUrl + jsonFilmData.getString("backdrop_path"));
                filmBuilder.setReleased(jsonFilmData.getString("release_date"));
                filmBuilder.setSort(sortBy);
                jsonFilmDao.insertFilm(filmBuilder);
            }
        }

    }

    public static void parseReviewsJson(String rJson) throws JSONException {

        Review reviewBuilder = new Review();
        ReviewsDao jsonReviewsDao = MainActivity.mainReviewsDao;
        int reviewResults;

        // Create the json object and array that holds the reviews data
        JSONObject reviewsObject = new JSONObject(rJson);
        JSONArray reviewsArray = reviewsObject.getJSONArray("results");

        // Get the number of reviews to use in the loop when parsing the individual reviews
        reviewResults = reviewsObject.getInt("total_results");

        if (reviewResults > 0){
            for (int i = 0; i < reviewsArray.length(); i++){
                JSONObject eachReviewObject = reviewsArray.getJSONObject(i);
                reviewBuilder.setReviewMovieId(reviewsObject.getString("id"));
                reviewBuilder.setId(eachReviewObject.getString("id"));
                reviewBuilder.setResults(reviewResults);
                reviewBuilder.setAuthor(eachReviewObject.getString("author"));
                reviewBuilder.setContents(eachReviewObject.getString("content"));
                jsonReviewsDao.insertReview(reviewBuilder);

                MovieData.reviewAuthors.add(eachReviewObject.getString("author"));
                MovieData.reviewContents.add(eachReviewObject.getString("content"));
            }
        }
    }
}
