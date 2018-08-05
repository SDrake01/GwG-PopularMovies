package com.stevendrake.moviehub.AsyncTasks;

import android.os.AsyncTask;

/**
 * Created by calebsdrake on 7/29/2018.
 */

public class VideosAsyncTask {

    public static class getFilmVideos extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute(){

            // turn on spinner
        }

        @Override
        protected String doInBackground(String... strings) {

            // get video links from API

            return null;
        }

        @Override
        protected void onPostExecute(String results){

            // turn off spinner
        }

    }
}
