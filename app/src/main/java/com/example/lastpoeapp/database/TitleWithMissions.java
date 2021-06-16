package com.example.lastpoeapp.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TitleWithMissions {
    @Embedded
    private Title title;
    @Relation(parentColumn = "id", entityColumn = "title_id")
    private List<Mission> missions;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "TitleMissionLink{" +
                "title=" + title +
                ", missions=" + missions +
                '}';
    }
}
