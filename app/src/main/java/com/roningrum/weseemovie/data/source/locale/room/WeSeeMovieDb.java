package com.roningrum.weseemovie.data.source.locale.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;

@Database(entities = {MovieEntity.class, TVShowEntity.class}, version = 4,
        exportSchema = false)
public abstract class WeSeeMovieDb extends RoomDatabase {
    private static final Object sLock = new Object();
    private static WeSeeMovieDb INSTANCE;

    public static WeSeeMovieDb getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WeSeeMovieDb.class, "WeSeeMovieDb.db")
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract WeSeeMovieDao weSeeMovieDao();
}
