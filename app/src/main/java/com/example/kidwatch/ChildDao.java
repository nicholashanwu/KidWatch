package com.example.kidwatch;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChildDao {

    @Query("SELECT * FROM child WHERE childId == :childId")
    Child getChildren(int childId);


    @Transaction
    @Query("SELECT * FROM child")
    LiveData<List<ChildWithCurrencies>> getChildrenWithCurrencies();

    @Query("DELETE FROM child")
    void deleteAllChildren();

    @Insert
    void insert(Child child);

    @Delete
    void delete(Child child);

    @Update
    void update(Child child);

}
