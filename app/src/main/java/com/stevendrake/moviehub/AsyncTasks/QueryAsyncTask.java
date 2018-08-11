package com.stevendrake.moviehub.AsyncTasks;

import android.os.AsyncTask;

import com.stevendrake.moviehub.Database.Film;
import com.stevendrake.moviehub.MainActivity;
import com.stevendrake.moviehub.MovieData;

import java.util.List;

/**
 * Created by calebsdrake on 7/23/2018.
 */

public class QueryAsyncTask {

    public static class getOneTitleTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String selectedTitle = MainActivity.mainFilmDao.getOneFilmTitle(strings[0]);
            return selectedTitle;
        }

        protected void onPostExecute(String results){
            MovieData.setTestingString(results);
        }
    }

    public static class getDatabaseFilms extends AsyncTask<String, Void, List<Film>>{

        @Override
        protected List<Film> doInBackground(String... params) {

            String sortString = params[0];
            switch (sortString){
                case "popular":
                    return MainActivity.mainFilmDao.getPopularFilms();
                case "top_rated":
                    return MainActivity.mainFilmDao.getTopRatedFilms();
                case "favorite":
                    return MainActivity.mainFilmDao.getFavoriteFilms();
            }
            return null;
        }

        protected void onPostExecute(List<Film> results){
            MainActivity.movieGridAdapter.setMovies(results);
        }
    }
}
