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

        @Query("UPDATE table_details SET order_name = :name, order_branch = :branch, order_duration = :duration WHERE order_id = :id")
        void update(String name, String branch, String duration, int id);

        @Query("Delete from table_details where order_id = :id")
        void delete(int id);

        @Query("DELETE FROM table_details")
        void deleteAlldetails();

        @Query("SELECT * FROM table_details ORDER BY order_branch ASC")
        List<Model> getAlldetails();

}
