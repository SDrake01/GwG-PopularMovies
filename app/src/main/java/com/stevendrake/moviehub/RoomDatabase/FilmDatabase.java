package com.stevendrake.moviehub.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by calebsdrake on 7/1/2018.
 */

@Database(entities = {Film.class}, version = 4)
public abstract class FilmDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();
}
