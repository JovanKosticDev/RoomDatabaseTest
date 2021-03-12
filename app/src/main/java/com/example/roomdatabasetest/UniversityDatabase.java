package com.example.roomdatabasetest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class UniversityDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    private static UniversityDatabase instance;

    public static synchronized UniversityDatabase getInstance(Context context) {
        if(null == instance){
            instance = Room.databaseBuilder(context, UniversityDatabase.class, "university_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateInitialData(instance).execute();
        }
    };

    private static class PopulateInitialData extends AsyncTask<Void, Void, Void>{

        private StudentDao studentDao;

        public PopulateInitialData(UniversityDatabase db) {
            this.studentDao = db.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            studentDao.insertSingleStundent(new Student("Max", "max@gmail.com", "545476521"));
            studentDao.insertSingleStundent(new Student("Felix", "felix@gmail.com", "875220"));
            studentDao.insertSingleStundent(new Student("Nina", "nina@gmail.com", "2132424"));

            return null;
        }
    }

}
