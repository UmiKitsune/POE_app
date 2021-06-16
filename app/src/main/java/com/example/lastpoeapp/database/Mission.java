package com.example.lastpoeapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Mission {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "text")
    private String text;
    @ColumnInfo(name = "isClicked")
    private boolean isClicked;
    @ColumnInfo(name = "title_id")
    private int titleId;

    @Ignore
    public Mission() {
    }

    public Mission(int id, String text, int titleId) {
        this.id = id;
        this.text = text;
        this.isClicked = false;
        this.titleId = titleId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isClicked=" + isClicked +
                ", titleId=" + titleId +
                '}';
    }
}
