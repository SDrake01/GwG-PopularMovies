package com.stevendrake.moviehub;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by calebsdrake on 6/26/2018.
 */

public class ReviewsTask {

    public static class getMovieReviews extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            // Create a variable to pass the returned json data to the parsing method
            String reviewsJsonResults = null;

            // Build the URL using passed in parameters
            String movieId = params[0];
            String apiKey = params[1];
            URL reviewsUrl = MovieNetwork.buildReviewsUrl(movieId, apiKey);

            if (reviewsUrl != null) {
                // Query the movie database to get the reviews json data
                try {
                    reviewsJsonResults = MovieNetwork.getJsonFromHttpUrl(reviewsUrl);
                    return reviewsJsonResults;
                } catch (IOException e){
                    e.printStackTrace();
                }

                // Parse the reviews json data if reviewsJsonResults returns a value from the api
                if (reviewsJsonResults != null){
                    try {
                        MovieJson.parseReviewsJson(reviewsJsonResults);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            return apiKey;
        }

        protected void onPostExecute(String results){
            MovieData.setTestingString(results);
        }
    }
}
