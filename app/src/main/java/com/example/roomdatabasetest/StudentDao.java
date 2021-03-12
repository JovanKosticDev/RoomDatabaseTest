package com.example.roomdatabasetest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertSingleStundent(Student student);

    @Delete
    void delteSingleStudent(Student student);

    @Update
    void updateSingleStudent(Student student);

    @Query("SELECT * FROM students")
    List<Student> getAllStudents();

    @Query("SELECT * FROM students WHERE name = :name")
    List<Student> getStudentsByName(String name);

    @Query("SELECT * FROM students")
    LiveData<List<Student>> getAllStudentsLiveData();
}
