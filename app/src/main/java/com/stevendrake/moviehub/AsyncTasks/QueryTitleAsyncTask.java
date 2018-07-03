package com.stevendrake.moviehub.AsyncTasks;

import android.os.AsyncTask;

import com.stevendrake.moviehub.MovieData;
import com.stevendrake.moviehub.RoomDatabase.FilmDao;

/**
 * Created by calebsdrake on 7/1/2018.
 */

public class QueryTitleAsyncTask {

    public static class getOneTitleTask extends AsyncTask<String, Void, String>{

        private FilmDao asyncTaskDao;
//        public getOneTitleTask(FilmDao dao){
//            asyncTaskDao = dao;
//        }

        @Override
        protected String doInBackground(String... params){

            // Create a variable to pass the returned movie title to the requesting method
            String queryMovieTitle = "testing";

            // Run the getTitle query from FilmDao using the passed in movie id string
            queryMovieTitle = asyncTaskDao.getTitle(params[0]);
            return queryMovieTitle;
        }

        protected void onPostExecute(String result){
            MovieData.setTestingString(result);
        }
    }
}
