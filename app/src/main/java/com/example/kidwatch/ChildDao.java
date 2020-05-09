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
    Child getChild(int childId);

    @Query("SELECT * FROM child WHERE childName == :childName")
    Child getChild(String childName);


    @Transaction
    @Query("SELECT * FROM child")
    LiveData<List<ChildWithCurrencies>> getChildrenWithCurrencies();



    @Query("DELETE FROM child")
    void deleteAllChildren();

    @Insert
    void insert(Child child);

    @Insert
    void insert(Currency currency);

    @Delete
    void delete(Child child);

    @Update
    void update(Child child);

}
