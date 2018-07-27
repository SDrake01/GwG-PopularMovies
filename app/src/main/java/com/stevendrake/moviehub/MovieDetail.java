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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by calebsdrake on 5/14/2018.
 */

public class MovieDetail extends AppCompatActivity implements View.OnClickListener {

    private Boolean isFavorite = false;
    int moviePosition;

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
        String apiKey = preferences.getString("api_key_setting","");
        // Get the movie movieId from MovieData using the moviePosition integer for the index
        String movieId = MovieData.movieIdNumber.get(moviePosition);
        //new QueryTitleAsyncTask.getOneTitleTask().execute(movieId);

        // Call the init method passing the position number to load the correct movie details
        init(moviePosition);

        // Call the getMovieReviews method from the ReviewsTask class to load data that will
        // populate the movie reviews page (hopefully before the user clicks on the 'read reviews' button
        new ReviewsTask.getMovieReviews().execute(movieId, apiKey);

        // Use this toast to verify that I can set and get data to/from the database
        Toast.makeText(getApplicationContext(), MovieData.testingString, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view){
        Button favsButton = findViewById(R.id.btn_detail_favorites);

        switch (view.getId()){
            case R.id.btn_detail_trailers:
                Intent trailersIntent = new Intent(getApplicationContext(), MovieTrailers.class);
                startActivity(trailersIntent);
                break;
            case R.id.btn_detail_reviews:
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
                } else {
                    favStar.setImageResource(android.R.drawable.btn_star_big_on);
                    favsButton.setText(getResources().getText(R.string.removeFavs));
                    isFavorite = true;
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

        // Verify the MovieData arrays are not null before trying to populate the view data
        // using the movieTitles array location 15 as a reference to eliminate null pointer exceptions
//        if (MovieData.movieTitles[15] != null) {
//            // Update view elements with selected movie data
//            title.setText(MovieData.movieTitles[moviePosition]);
//            releaseDate.setText(String.format(getResources().getString(R.string.release_date), MovieData.movieReleaseDates[moviePosition]));
//            rating.setRating(MovieData.movieRatings[moviePosition]);
//            description.setText(MovieData.movieDescriptions[moviePosition]);
//            Picasso.with(this).load(MovieData.movieBackdropUrls[moviePosition]).into(poster);
//        }
        title.setText(MovieData.movieTitles.get(moviePosition));
        releaseDate.setText(String.format(getResources().getString(R.string.release_date),
                MovieData.movieReleaseDates.get(moviePosition)));
        rating.setRating(MovieData.movieRatings.get(moviePosition));
        description.setText(MovieData.movieDescriptions.get(moviePosition));
        Picasso.with(this).load(MovieData.movieBackdropUrls.get(moviePosition)).into(poster);
    }
}
