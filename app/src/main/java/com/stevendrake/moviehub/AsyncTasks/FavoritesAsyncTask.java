package com.stevendrake.moviehub.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.stevendrake.moviehub.Database.Film;
import com.stevendrake.moviehub.Database.FilmDao;
import com.stevendrake.moviehub.MainActivity;

/**
 * Created by calebsdrake on 8/8/2018.
 */

public class FavoritesAsyncTask {

    public static class addToFavorites extends AsyncTask<Film, Void, String>{

        Context context;
        public addToFavorites(Context context){
            this.context = context;
        }

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String addFav = preferences.getString("sort_setting","");
        String addFav = "favorite";

        FilmDao favFilmDao = MainActivity.mainFilmDao;

        private Film addFavFilm;
        private Film favFilmBuilder = new Film();


        @Override
        protected String doInBackground(Film... films) {
            addFavFilm = films[0];
            favFilmBuilder.setId(addFavFilm.getId());
            favFilmBuilder.setTitle(addFavFilm.getTitle());
            favFilmBuilder.setRating(addFavFilm.getRating());
            favFilmBuilder.setPopularity(addFavFilm.getPopularity());
            favFilmBuilder.setDescription(addFavFilm.getDescription());
            favFilmBuilder.setPoster(addFavFilm.getPoster());
            favFilmBuilder.setBackdrop(addFavFilm.getBackdrop());
            favFilmBuilder.setReleased(addFavFilm.getReleased());
            favFilmBuilder.setSort(addFavFilm.getSort());
            favFilmBuilder.setFavorite(addFav);
            favFilmDao.updateOneFilm(favFilmBuilder);
            String returnFilmTitle = addFavFilm.getTitle();
            return returnFilmTitle;
        }

        @Override
        protected void onPostExecute(String resultTitle){
            Toast.makeText(context, resultTitle + " added to My Favorites", Toast.LENGTH_SHORT).show();
        }
    }

    public static class removeFromFavorites extends AsyncTask<Film, Void, String>{

        Context context;
        public removeFromFavorites(Context context){
            this.context = context;
        }

        String removeFav = "";
        FilmDao favFilmDao = MainActivity.mainFilmDao;
        private Film removeFavFilm;
        private Film favFilmBuilder = new Film();


        @Override
        protected String doInBackground(Film... films) {
            removeFavFilm = films[0];
            favFilmBuilder.setId(removeFavFilm.getId());
            favFilmBuilder.setTitle(removeFavFilm.getTitle());
            favFilmBuilder.setRating(removeFavFilm.getRating());
            favFilmBuilder.setPopularity(removeFavFilm.getPopularity());
            favFilmBuilder.setDescription(removeFavFilm.getDescription());
            favFilmBuilder.setPoster(removeFavFilm.getPoster());
            favFilmBuilder.setBackdrop(removeFavFilm.getBackdrop());
            favFilmBuilder.setReleased(removeFavFilm.getReleased());
            favFilmBuilder.setSort(removeFavFilm.getSort());
            favFilmBuilder.setFavorite(removeFav);
            favFilmDao.updateOneFilm(favFilmBuilder);
            String returnFilmTitle = removeFavFilm.getTitle();
            return returnFilmTitle;
        }

        @Override
        protected void onPostExecute(String resultTitle){
            Toast.makeText(context, resultTitle + " removed from My Favorites", Toast.LENGTH_LONG).show();
        }
    }
}
