package com.stevendrake.moviehub;

import com.stevendrake.moviehub.Database.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calebsdrake on 5/22/2018.
 */

public class MovieData {

    static List<String> movieIdNumber = new ArrayList<String>();
    public static List<String> movieTitles = new ArrayList<String>();
    static List<Long> movieRatings = new ArrayList<Long>();
    static List<String> movieDescriptions = new ArrayList<String>();
    public static List<String> movieImageUrls = new ArrayList<String>();
    static List<String> movieBackdropUrls = new ArrayList<String>();
    static List<String> movieReleaseDates = new ArrayList<String>();

    public static List<Review> testObjects = new ArrayList<Review>();

    // Create array lists and search strings for the trailers and reviews
    static int reviewResults = 0;
    static List<String> reviewAuthors = new ArrayList<String>();
    static List<String> reviewContents = new ArrayList<String>();
    public static String reviewFilmId;
    public static void setReviewId(String review){reviewFilmId = review;}
    static String getReviewId(){return reviewFilmId;}

    static int videoResults = 0;
    static List<String> videoTitles = null;
    static List<String> videoUrls = null;
    static List<String> videoIds = null;
    public static String videoFilmId;
    public static void setVideoId(String video){videoFilmId = video;}
    static String getVideoId(){return videoFilmId;}

    private static String testingString = null;
    public static void setTestingString(String test){
        testingString = test;
    }
    static String getTestingString(){
        return testingString;
    }
}
