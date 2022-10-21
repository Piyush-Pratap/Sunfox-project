package com.example.myapplication;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

        @Insert
        void insert(Model model);

        @Update
        void update(Model model);

        @Delete
        void delete(Model model);

        @Query("DELETE FROM table_details")
        void deleteAlldetails();

        @Query("SELECT * FROM table_details ORDER BY order_branch ASC")
        List<Model> getAlldetails();

}
