package com.example.kidwatch;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ChildDao {
    @Query("SELECT * FROM child")
    List<Child> getChildren();
    //query used to return a list of all courses from the course table

    @Query("SELECT * FROM child WHERE childId == :childId")
    Child getChildren(int childId);
    //query used to return a single course that matches the id specified, used for the detail activity

    @Transaction
    @Query("SELECT * FROM child")
    public List<ChildWithCurrencies> getChildrenWithCurrencies();


}
