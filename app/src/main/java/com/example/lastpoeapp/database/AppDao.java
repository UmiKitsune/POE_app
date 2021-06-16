package com.example.lastpoeapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
    //вставляем данные
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTitles(List<Title> titles);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMissions(List<Mission> missions);

    //получаем данные
    @Query("SELECT * FROM title")
    List<Title> getTitles();

    @Query("SELECT * FROM mission")
    List<Mission> getMissions();

    //удалить данные
    @Delete
    void delete(Title title);

    @Query("DELETE FROM title")
    void deleteAllTitles();

    @Query("DELETE FROM mission")
    void deleteAllMissions();

    @Query("DELETE FROM title WHERE id = :titleId " )
    void deleteTitle(int titleId);
    @Query("DELETE FROM mission WHERE title_id = :titleId " )
    void deleteMission(int titleId);

    //объединение
//    @Transaction
//    @Query("SELECT * FROM title WHERE id = :titleId")
//    List<TitleWithMissions> getTitleWithMissions(int titleId);

    @Transaction
    @Query("SELECT * FROM title")
    LiveData<List<TitleWithMissions>> getAllTitleWithMissions();

//    @Transaction
//    @Query("SELECT * FROM title")
//    List<TitleWithMissions> getAllTitleWithMissions();

    @Query("UPDATE mission SET isClicked = :isClicked WHERE id = :missionId")
    void updateMissionComplete(boolean isClicked, int missionId);
}
