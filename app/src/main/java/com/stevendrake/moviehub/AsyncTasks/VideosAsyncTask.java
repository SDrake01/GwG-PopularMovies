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

public class VideosAsyncTask {

    public static class getFilmVideos extends AsyncTask<String, Void, String>{

        Context context;

        public getFilmVideos(Context context){
            this.context = context;
        }

        ProgressDialog reviewSpinner;

        @Override
        protected void onPreExecute(){
            reviewSpinner = new ProgressDialog(context);
            // turn on spinner
            reviewSpinner.setTitle("One Moment Please");
            reviewSpinner.setMessage("The Videos are loading");
            reviewSpinner.setCancelable(false);
            reviewSpinner.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            // get videos from API
            // Create a variable used to pass the returned json data to the parsing method
            String httpVidJsonResults = null;

            // Build the url using the passed in parameters
            String movieId = strings[0];
            String apiKey = strings[1];
            URL videoUrl = MovieNetwork.buildVideosUrl(movieId, apiKey);

            // Query the Movie Database Api to get the movie video json data
            try {
                httpVidJsonResults = MovieNetwork.getVidJsonFromHttpUrl(videoUrl);
            } catch (IOException e){
                e.printStackTrace();
            }

            // Parse the movie video json data and populate the view arrays
            // only if httpVidJsonResults returns a value from the Api
            if (httpVidJsonResults != null){
                try {
                    // parse the movie video json data to populate the views
                    MovieJson.parseVideoJson(httpVidJsonResults);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            return httpVidJsonResults;
        }

        @Override
        protected void onPostExecute(String result){

            // turn off spinner
            reviewSpinner.dismiss();
            //MovieData.setTestingString(result);
        }
    }
}
