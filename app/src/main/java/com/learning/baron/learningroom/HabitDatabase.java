package com.learning.baron.learningroom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Habit.class}, version = 1)
public abstract class HabitDatabase extends RoomDatabase {

    public abstract HabitDAO habitDAO();

    //SINGLETON
    private static HabitDatabase INSTANCE;

    static HabitDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HabitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HabitDatabase.class, "habit_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final HabitDAO mDao;

        PopulateDbAsync(HabitDatabase db) {
            mDao = db.habitDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Habit word = new Habit("Hello");
            mDao.insert(word);
            word = new Habit("World");
            mDao.insert(word);
            word = new Habit("Baron");
            mDao.insert(word);
            word = new Habit("!");
            mDao.insert(word);
            return null;
        }
    }
}
