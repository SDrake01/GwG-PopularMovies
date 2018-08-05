package com.stevendrake.moviehub;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by calebsdrake on 5/23/2018.
 */

public final class MovieNetwork {

    // This construction of final String variables and the buildMovieUrl method
    // were written using the Sunshine app as a direct example.  I have changed
    // the string variables and the Uri builder process to accommodate the Movie Database API
    private static final String QUERY_BASE_URL = "https://api.themoviedb.org/3/movie";
    // The value for popular or top_rated will be provided by the settings and
    // passed into this method at this point, no key or value needed
    private static final String KEY_KEY = "api_key";
    // The value for the api_key is now entered in the preferences and passed
    // into this method from the calling activity
    // *************************************************************************
    // ************* Please put your own key in the settings *******************
    // ************************** Thank You ************************************
    // *************************************************************************
    private static final String KEY_LANGUAGE = "language";
    private static final String QUERY_LANGUAGE = "en-US";
    private static final String KEY_PAGE = "page";
    private static final int QUERY_PAGE = 1;

    // Use the above final variables to build the Uri string with the
    // value being passed in when calling this method.  This allows
    // me to change the sorting method using the preferences
    static URL buildMovieUrl(String sortValue, String apiKey){

        // Build the Api request url using the passed in values for the sort_by query and api_key query
        Uri movieUri = Uri.parse(QUERY_BASE_URL).buildUpon()
                .appendPath(sortValue)
                .appendQueryParameter(KEY_KEY, apiKey)
                .appendQueryParameter(KEY_LANGUAGE, QUERY_LANGUAGE)
                .appendQueryParameter(KEY_PAGE, Integer.toString(QUERY_PAGE))
                .build();

        // Verify the Url is properly constructed
        URL url = null;
        try {
            url = new URL(movieUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    // Take the Url given and return the full json results from the Api
    static String getJsonFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("//Q//");

            return scanner.next();
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildReviewsUrl(String movieId, String apiKey){

        // Create a string to fill in the url
        final String REVIEWS = "reviews";

        // Build the reviews url using the passed in values for movie movieId number and apiKey
        Uri reviewsUri = Uri.parse(QUERY_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendPath(REVIEWS)
                .appendQueryParameter(KEY_KEY, apiKey)
                .appendQueryParameter(KEY_LANGUAGE, QUERY_LANGUAGE)
                .appendQueryParameter(KEY_PAGE, Integer.toString(QUERY_PAGE))
                .build();

        // Verify the built Url is valid
        URL url = null;
        try {
            url = new URL(reviewsUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    // Take the movie reviews Url given and return the full json results from the Api
    public static String getRevJsonFromHttpUrl(URL url) throws IOException {
        HttpURLConnection revUrlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = revUrlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("//Q//");

            return scanner.next();
        } finally {
            revUrlConnection.disconnect();
        }
    }
}