package com.stevendrake.moviehub.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by calebsdrake on 7/1/2018.
 */

@Database(entities = {Film.class}, version = 4)
public abstract class FilmDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();


//    // Initialize the database and its members
//    private static FilmDatabase INSTANCE;
//    public static FilmDatabase getDatabase(final Context context){
//        if (INSTANCE == null){
//            synchronized (FilmDatabase.class){
//                if (INSTANCE == null){
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            FilmDatabase.class, "film_database")
//                            .fallbackToDestructiveMigration()
//                            .build();
//                }
//            }
//        }
//
//        return INSTANCE;
//    }

}
