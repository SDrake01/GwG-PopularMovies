package com.stevendrake.moviehub.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.stevendrake.moviehub.MovieJson;
import com.stevendrake.moviehub.MovieNetwork;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by calebsdrake on 7/29/2018.
 */

public class ReviewsAsyncTask {

    public static class getFilmReviews extends AsyncTask<String, Void, String>{

        Context context;

        public getFilmReviews(Context context){
            this.context = context;
        }

        ProgressDialog reviewSpinner;

        @Override
        protected void onPreExecute(){
            reviewSpinner = new ProgressDialog(context);
            // turn on spinner
            reviewSpinner.setTitle("One Moment Please");
            reviewSpinner.setMessage("The Reviews are loading");
            reviewSpinner.setCancelable(false);
            reviewSpinner.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            // get reviews from API
            // Create a variable used to pass the returned json data to the parsing method
            String httpJsonResults = null;

            // Build the url using the passed in parameters
            String movieId = strings[0];
            String apiKey = strings[1];
            URL movieUrl = MovieNetwork.buildReviewsUrl(movieId, apiKey);

            // Query the Movie Database Api to get the movie json data
            try {
                httpJsonResults = MovieNetwork.getRevJsonFromHttpUrl(movieUrl);
            } catch (IOException e){
                e.printStackTrace();
            }

            // Parse the movie json data and populate the view arrays
            // only if httpJsonResults returns a value from the Api
            if (httpJsonResults != null) {
                try {
                    // parse the movie json data and populate the view
                    MovieJson.parseReviewsJson(httpJsonResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return httpJsonResults;
        }

        @Override
        protected void onPostExecute(String result){

            // turn off spinner
            reviewSpinner.dismiss();
            //MovieData.setTestingString(result);
        }
    }
}
