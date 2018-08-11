package com.stevendrake.moviehub;

import com.stevendrake.moviehub.Database.Film;
import com.stevendrake.moviehub.Database.FilmDao;
import com.stevendrake.moviehub.Database.Review;
import com.stevendrake.moviehub.Database.ReviewsDao;
import com.stevendrake.moviehub.Database.Video;
import com.stevendrake.moviehub.Database.VideoDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by calebsdrake on 5/20/2018.
 */

public final class MovieJson {

    public static void parseMovieJsonToDatabase(String json, String sortBy) throws JSONException{

        FilmDao jsonFilmDao = MainActivity.mainFilmDao;

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

    public static void parseVideoJson(String vJson) throws JSONException{

        Video vidBuilder = new Video();
        VideoDao jsonVidDao = MainActivity.mainVideoDao;

        // Create the json object and array that hold the videos data
        JSONObject videosObject = new JSONObject(vJson);
        JSONArray videosArray = videosObject.getJSONArray("results");

        // Get the number of videos to use in the loop when parsing the individual videos
        for (int i = 0; i < videosArray.length(); i++){
            JSONObject eachVideoObject = videosArray.getJSONObject(i);
            vidBuilder.setVideoMovieId(videosObject.getString("id"));
            vidBuilder.setVideoId(eachVideoObject.getString("id"));
            vidBuilder.setName(eachVideoObject.getString("name"));
            vidBuilder.setType(eachVideoObject.getString("type"));
            vidBuilder.setVidkey(eachVideoObject.getString("key"));
            vidBuilder.setSite(eachVideoObject.getString("site"));
            jsonVidDao.insertVideo(vidBuilder);
        }
    }
}
