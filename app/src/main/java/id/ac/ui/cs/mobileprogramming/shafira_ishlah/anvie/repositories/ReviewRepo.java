package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.CategoryReview;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.models.Reviews;

@Database(entities = {CategoryReview.class, Reviews.class}, version = 2)
public abstract class ReviewRepo extends RoomDatabase {

    public abstract DaoClass daoClass();

    private static ReviewRepo INSTANCE;

    // Membuat method return untuk membuat database
    public static ReviewRepo createDatabase(Context context){
        synchronized (ReviewRepo.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, ReviewRepo.class, "db_siswa")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}