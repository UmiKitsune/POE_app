package com.example.lastpoeapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Title.class, Mission.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;
    private static final String DB_NAME = "test.db";
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }

    //получаем интерфейс NotesDao
    public abstract AppDao getDao();
}
//.allowMainThreadQueries() - в основной поток д/теста