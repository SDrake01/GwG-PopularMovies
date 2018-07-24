package com.stevendrake.moviehub.AsyncTasks;

import android.os.AsyncTask;

import com.stevendrake.moviehub.MainActivity;
import com.stevendrake.moviehub.MovieData;

/**
 * Created by calebsdrake on 7/23/2018.
 */

public class QueryTitleAsyncTask {

    public static class getOneTitleTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String selectedTitle = MainActivity.filmDao.getOneFilmTitle(strings[0]);
            return selectedTitle;
        }

        protected void onPostExecute(String results){
            MovieData.setTestingString(results);
        }
    }
}
