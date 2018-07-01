package com.stevendrake.moviehub.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by calebsdrake on 7/1/2018.
 */

@Database(entities = {Film.class}, version = 1)
public abstract class FilmDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();

    private static FilmDatabase INSTANCE;

    public static FilmDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (FilmDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FilmDatabase.class, "film_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
