package com.example.lastpoeapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Title {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    public Title() {
    }

    public Title(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
