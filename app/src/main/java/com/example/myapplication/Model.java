package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_details")
public class Model {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    public  int id;

    @ColumnInfo(name = "order_name")
    public String name;

    @ColumnInfo(name = "order_branch")
    public String branch;

    @ColumnInfo(name = "order_duration")
    public String duration;

    public Model(String name, String branch, String duration){
        this.name = name;
        this.branch = branch;
        this.duration = duration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}