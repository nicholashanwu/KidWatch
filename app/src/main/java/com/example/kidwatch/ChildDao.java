package com.example.kidwatch;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChildDao {
    @Query("SELECT * FROM child")
    List<Child> getChildren();
    //query used to return a list of all courses from the course table

    @Query("SELECT * FROM child WHERE id == :childId")
    Child getChildren(int childId);
    //query used to return a single course that matches the id specified, used for the detail activity




    //TODO Implement a Room query that return a course with a specified id

    //TODO Implement a Room query that returns all courses from a specified school

}
