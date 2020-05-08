package com.example.kidwatch;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Child.class, Currency.class}, version = 1)
public abstract class ChildDatabase extends RoomDatabase {

    private static ChildDatabase instance;

    public abstract ChildDao childDao();

    public static synchronized ChildDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ChildDatabase.class, "child_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ChildDao childDao;

        private PopulateDbAsyncTask(ChildDatabase db) {
            childDao = db.childDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            childDao.insert(new Child("nick"));
            childDao.insert(new Child("ollie"));
            childDao.insert(new Child("marina"));
            return null;
        }
    }
}
