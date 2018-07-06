package com.stevendrake.moviehub;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.stevendrake.moviehub.RoomDatabase.FilmDao;
import com.stevendrake.moviehub.RoomDatabase.FilmDatabase;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {

    // The TEST_LIST_LENGTH is to verify design properties and should be removed before release
    // it will be replaced by the database length when it is included so the app will work offline
    private static final int TEST_LIST_LENGTH = 20;
    private MovieAdapter movieGridAdapter;
    private int gridNumber = 3;

    // Create an instance of SharedPreferences and PreferenceChangeListener
    SharedPreferences prefs;
    private PreferenceChangeListener prefChanges = null;
    public static FilmDao movieFilmDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // try once again to build the database
        movieFilmDao = Room.databaseBuilder(this.getApplicationContext(), FilmDatabase.class, "movies_database")
                .build()
                .filmDao();

        // Set the default filter_prefs values
        PreferenceManager.setDefaultValues(this, R.xml.filter_prefs, false);

        // Set and register the preference change listener to receive change notifications
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefChanges = new PreferenceChangeListener();
        prefs.registerOnSharedPreferenceChangeListener(prefChanges);

        // Define the RecyclerView instance to match the layout
        RecyclerView movieRecyclerView = findViewById(R.id.rv_movie_hub_recycler_view);

        // Create and assign the GridLayoutManager for the RecyclerView
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE){
            gridNumber = 5;
        } else {
            gridNumber = 3;
        }
        GridLayoutManager movieGridLayoutManager = new GridLayoutManager(this, gridNumber);
        movieRecyclerView.setLayoutManager(movieGridLayoutManager);

        // Set the MovieAdapter instance with the number of items
        movieGridAdapter = new MovieAdapter(TEST_LIST_LENGTH);

        // Set the adapter to the RecyclerView
        movieRecyclerView.setAdapter(movieGridAdapter);

        // Get the value for movie filter and the user's api key from Shared Preferences
        String sortFilter = prefs.getString("sort_setting", "");
        String apiPref = prefs.getString("api_key_setting", "");

        // Create a connectivity manager that will be used to verify an internet connection
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Verify the device has an active internet connection
        // before trying to get data from the Api using the connectivity manager
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean activeConnection = (activeNetwork != null && activeNetwork.isConnectedOrConnecting());

        // Call the AsyncTask to load the movie data if the connection is active or connecting only
        if (activeConnection) {
            new getMovieRawJson().execute(sortFilter, apiPref);
        } else {
            Toast.makeText(this, "No active network connection", Toast.LENGTH_LONG).show();
        }
    }

    // Register the preference change listener on Resume to avoid garbage collection
    @Override
    public void onResume(){
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(prefChanges);
    }

    // Unregister the preference change listener on destroy as good practice
    @Override
    public void onDestroy(){
        super.onDestroy();
        prefs.unregisterOnSharedPreferenceChangeListener(prefChanges);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    public class getMovieRawJson extends AsyncTask<String, Void, String> {

        // Create a progress spinner for the onPreExecute method
        ProgressDialog spinner = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute(){
            //spinner = new ProgressDialog();
            spinner.setTitle("Please wait...");
            spinner.setMessage("Loading movie data");
            spinner.setCancelable(false);
            spinner.show();
        }

        @Override
        protected String doInBackground(String... params){

            // Create a variable used to pass the returned json data to the parsing method
            String httpJsonResults = null;

            // Build the url using the passed in parameters
            String movieSort = params[0];
            String apiKey = params[1];
            URL movieUrl = MovieNetwork.buildMovieUrl(movieSort, apiKey);

            // Query the Movie Database Api to get the movie json data
            try {
                httpJsonResults = MovieNetwork.getJsonFromHttpUrl(movieUrl);
            } catch (IOException e){
                e.printStackTrace();
            }

            // Parse the movie json data and populate the view arrays
            // only if httpJsonResults returns a value from the Api
            if (httpJsonResults != null) {
                try {
                    MovieJson.parseMovieJsonData(httpJsonResults);
                    // parse the movie json data and populate the database
                    MovieJson.parseMovieJsonToDatabase(httpJsonResults, movieSort);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        protected void onPostExecute(String httpResults){
            // If I change the async task to return a Void, the onPostExecute doesn't run
            // I won't use the httpResults string for anything other than bug testing, but
            // things break if I eliminate it
            //
            // Dismiss the spinner and notify the adapter so it will refresh the recycler view
            spinner.dismiss();
            movieGridAdapter.notifyDataSetChanged();

        }
    }

    // Create a menu to open from the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moviehub_menu, menu);

        return true;
    }

    // Set the menu "Settings" item to open the settings activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.app_settings){
            Intent intent = new Intent(this, MoviePreferencesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class PreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener{

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("sort_setting")){
                // Get the new preference value
                SharedPreferences newPref = getDefaultSharedPreferences(MainActivity.this);
                String pref = newPref.getString("sort_setting", "");
                String apiPref = newPref.getString("api_key_setting", "");
                // Run the Api query and update the recycler view with the new preference value
                new getMovieRawJson().execute(pref, apiPref);
            }
        }
    }
}
