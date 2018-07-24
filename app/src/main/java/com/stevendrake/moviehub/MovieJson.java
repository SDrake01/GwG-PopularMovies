package com.stevendrake.moviehub;

import android.content.Context;

import com.stevendrake.moviehub.Database.Film;
import com.stevendrake.moviehub.Database.FilmDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by calebsdrake on 5/20/2018.
 */

final class MovieJson {

    public static void parseMovieJsonData(String json) throws JSONException{

        // Create json object and array that hold the necessary json data
        JSONObject outerJson = new JSONObject(json);
        JSONArray fullArray = outerJson.getJSONArray("results");

        // Create the strings that will be used to build the urls for picasso
        String posterUrl = "https://image.tmdb.org/t/p/w185";
        String backdropUrl = "https://image.tmdb.org/t/p/w780";
        // Iterate through the json array to build the view arrays
        if (fullArray != null) {
            for (int i = 0; i < fullArray.length(); i++) {
                JSONObject titleJson = fullArray.getJSONObject(i);
                MovieData.movieTitles[i] = titleJson.getString("title");
                MovieData.movieRatings[i] = titleJson.getLong("vote_average");
                MovieData.movieReleaseDates[i] = titleJson.getString("release_date");
                MovieData.movieDescriptions[i] = titleJson.getString("overview");
                MovieData.movieImageUrls[i] = posterUrl + titleJson.getString("poster_path");
                MovieData.movieBackdropUrls[i] = backdropUrl + titleJson.getString("backdrop_path");
                MovieData.movieIdNumber[i] = titleJson.getString("id");
            }
        }
    }

    public static void parseMovieJsonToDatabase(String json, String sortBy) throws JSONException{

        FilmDao jsonFilmDao = MainActivity.filmDao;
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
                filmBuilder.setPoster(jsonFilmData.getString("poster_path"));
                filmBuilder.setBackdrop(jsonFilmData.getString("backdrop_path"));
                filmBuilder.setReleased(jsonFilmData.getString("release_date"));
                filmBuilder.setSort(sortBy);
                jsonFilmDao.insertFilm(filmBuilder);
            }
        }

    }

    public static void parseReviewsJson(String rJson) throws JSONException {

        // Clear the moviedata.review* lists before repopulating them to avoid
        // having any previous movie's reviews show
        if (MovieData.reviewAuthors != null) {
            MovieData.reviewAuthors.clear();
        }
        if (MovieData.reviewContents != null) {
            MovieData.reviewContents.clear();
        }

        // Create the json object and array that holds the reviews data
        JSONObject reviewsObject = new JSONObject(rJson);
        JSONArray reviewsArray = reviewsObject.getJSONArray("results");

        // Get the number of reviews to use in the loop when parsing the individual reviews
        MovieData.reviewResults = reviewsObject.getInt("total_results");

        if (MovieData.reviewResults > 0){
            for (int i = 0; i < MovieData.reviewResults; i++){
                JSONObject eachReviewObject = reviewsArray.getJSONObject(i);
                MovieData.reviewAuthors.add(eachReviewObject.getString("author"));
                MovieData.reviewContents.add(eachReviewObject.getString("content"));
            }
        }

    }
}
