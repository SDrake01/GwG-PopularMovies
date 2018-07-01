package com.stevendrake.moviehub;

import java.util.List;

/**
 * Created by calebsdrake on 5/22/2018.
 */

public class MovieData {

    static final String[] movieTitles = new String[20];
    static final Long[] movieRatings = new Long[20];
    static final String[] movieReleaseDates = new String[20];
    static final String[] movieDescriptions = new String[20];
    static final String[] movieImageUrls = new String[20];
    static final String[] movieBackdropUrls = new String[20];
    static final String[] movieIdNumber = new String[20];

    // Create array lists for the trailers and reviews
    static int reviewResults = 0;
    static List<String> reviewAuthors = null;
    static List<String> reviewContents = null;

    static int videoResults = 0;
    static List<String> videoTitles = null;
    static List<String> videoUrls = null;
    static List<String> videoIds = null;

    static String testingString = null;
    public static void setTestingString(String test){
        testingString = test;
    }
    static String getTestingString(){
        return testingString;
    }
}
