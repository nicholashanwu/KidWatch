package com.example.kidwatch;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Child.class}, version = 1)
public abstract class ChildDatabase extends RoomDatabase {
    public abstract ChildDao childDao();
}
