package com.example.nourish;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CartItems.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;
    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CartDatabase.class, "cartTable").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        }
        return instance;

    }
    public abstract CartDoa cartDoa();

}
