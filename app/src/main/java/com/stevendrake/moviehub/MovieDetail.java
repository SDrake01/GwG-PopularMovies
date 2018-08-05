package com.stevendrake.moviehub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stevendrake.moviehub.AsyncTasks.ReviewsAsyncTask;
import com.stevendrake.moviehub.Database.ReviewsDao;

/**
 * Created by calebsdrake on 5/14/2018.
 */

public class MovieDetail extends AppCompatActivity implements View.OnClickListener {

    private Boolean isFavorite = false;
    int moviePosition;
    String apiKey;
    String movieId;
    ReviewsDao detailReviewsDao = MainActivity.mainReviewsDao;

    // Create an instance of Shared Preferences to save the api key
    // to pass into the methods that query the movie database and return
    // reviews and trailers
    SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        // Get the grid position number from the main activity so it can be sent to
        // the init() method to load the page
        moviePosition = getIntent().getIntExtra("movieNumber", 0);

        // Get the user's api key from the Shared Preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        apiKey = preferences.getString("api_key_setting","");
        // Get the movie movieId from MovieData using the moviePosition integer for the index
        movieId = MovieAdapter.showMovies.get(moviePosition).getId();
        MovieData.reviewFilmId = movieId;
        //new QueryAsyncTask.getOneTitleTask().execute(movieId);

        // Call the init method passing the position number to load the correct movie details
        init(moviePosition);

        // Use this toast to verify that I can set and get data to/from the database
        //Toast.makeText(getApplicationContext(), MovieData.reviewFilmId, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view){
        Button favsButton = findViewById(R.id.btn_detail_favorites);
        String movieId = MovieAdapter.showMovies.get(moviePosition).getId();

        switch (view.getId()){
            case R.id.btn_detail_trailers:
                // Run VideosAsyncTask to query the API for the videos data
                Intent trailersIntent = new Intent(getApplicationContext(), MovieTrailers.class);
                startActivity(trailersIntent);
                break;
            case R.id.btn_detail_reviews:
                new ReviewsAsyncTask.getFilmReviews(this).execute(movieId, apiKey);
                Intent reviewsIntent = new Intent(getApplicationContext(), MovieReviews.class);
                startActivity(reviewsIntent);
                break;
            case R.id.btn_detail_favorites:

                // Toggle favorite star icon on/off, then add data to the database
                ImageView favStar = findViewById(R.id.iv_detail_favorites_icon);

                if (isFavorite) {
                    favStar.setImageResource(android.R.drawable.btn_star_big_off);
                    favsButton.setText(getResources().getText(R.string.favorites));
                    isFavorite = false;
                    // Delete this film from the favorites table
                } else {
                    favStar.setImageResource(android.R.drawable.btn_star_big_on);
                    favsButton.setText(getResources().getText(R.string.removeFavs));
                    isFavorite = true;
                    // Add this film to the favorites table
                }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("position", moviePosition);
    }

    protected void init(int moviePosition){

        // Create the view elements in java so they can be updated with movie data
        ImageView poster = findViewById(R.id.iv_detail_movie_image);
        TextView title = findViewById(R.id.tv_detail_movie_title);
        RatingBar rating = findViewById(R.id.rat_detail_movie_rating);
        TextView releaseDate = findViewById(R.id.tv_detail_movie_release_date);
        TextView description = findViewById(R.id.tv_detail_movie_description);

        // Create the buttons and favorites star in java so they can be used and updated
        Button btnTrailers = findViewById(R.id.btn_detail_trailers);
        Button btnReviews = findViewById(R.id.btn_detail_reviews);
        Button btnFavs = findViewById(R.id.btn_detail_favorites);
        // Add OnClickListeners to each button
        btnTrailers.setOnClickListener(this);
        btnReviews.setOnClickListener(this);
        btnFavs.setOnClickListener(this);

        // Get and display the data from the MovieAdapter cached data using the passed in position number
        // to reference the index of the cached arraylist
        title.setText(MovieAdapter.showMovies.get(moviePosition).getTitle());
        releaseDate.setText(String.format(getResources().getString(R.string.release_date),
                MovieAdapter.showMovies.get(moviePosition).getReleased()));
        rating.setRating(MovieAdapter.showMovies.get(moviePosition).getRating());
        description.setText(MovieAdapter.showMovies.get(moviePosition).getDescription());
        Picasso.with(this).load(MovieAdapter.showMovies.get(moviePosition).getBackdrop()).into(poster);
    }
}
