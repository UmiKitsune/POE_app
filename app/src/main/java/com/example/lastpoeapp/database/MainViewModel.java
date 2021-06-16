package com.example.lastpoeapp.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static AppDatabase database;
    //будем хранить тут все заметки
    private LiveData<List<TitleWithMissions>> allData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(getApplication());
        allData = database.getDao().getAllTitleWithMissions();
    }

    //геттер
    public LiveData<List<TitleWithMissions>> getAllData() {
        return allData;
    }


    //метод для вставки элемента
    public void insertTitle(List<Title> titles) {
        for (Title title : titles) {
            new InsertTitleTask().execute(title);
        }
    }

    public void insertMission(List<Mission> missions) {
        for (Mission mission : missions) {
            new InsertMissionTask().execute(mission);
        }
    }

    //для удаления
    public void delete(Title title) {
        new DeleteTask().execute(title);
    }

    public void deleteAll() {
        new DeleteAllTask().execute();
    }


    public int getTitlesCount() {
        GetTitleTask t = new GetTitleTask();
        int i = 0;
        try {
            i = t.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return i;
    }

    private static class GetTitleTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... titles) {
            return database.getDao().getTitles().size();

        }
    }

    public List<Mission> getMissions() {
        GetMissionTask t = new GetMissionTask();
        List<Mission> m = new ArrayList<>();
        try {
            m = t.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return m;
    }

    private static class GetMissionTask extends AsyncTask<Void, Void, List<Mission>> {
        @Override
        protected List<Mission> doInBackground(Void... titles) {
            return database.getDao().getMissions();

        }
    }
    //Updates
    public void updateFalse(int missionId) {
        new UpdateFalseMissionTask().execute(missionId);
    }

    private static class UpdateFalseMissionTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            database.getDao().updateMissionComplete(false, integers[0]);
            return null;
        }
    }

    public void updateTrue(int missionId) {
        new UpdateTrueMissionTask().execute(missionId);
    }

    private static class UpdateTrueMissionTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            database.getDao().updateMissionComplete(true, integers[0]);
            return null;
        }
    }

    //для выполнения в другом потоке
    private static class InsertTitleTask extends AsyncTask<Title, Void, Void> {
        @Override
        protected Void doInBackground(Title... titles) {
            if (titles != null && titles.length > 0) {
                database.getDao().insertTitles(Arrays.asList(titles));
            }
            return null;
        }
    }

    private static class InsertMissionTask extends AsyncTask<Mission, Void, Void> {
        @Override
        protected Void doInBackground(Mission... missions) {
            if (missions != null && missions.length > 0) {
                database.getDao().insertMissions(Arrays.asList(missions));
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Title, Void, Void> {
        @Override
        protected Void doInBackground(Title... titles) {
            if (titles != null && titles.length > 0) {
                database.getDao().delete(titles[0]);
            }
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... titles) {
            database.getDao().deleteAllTitles();
            database.getDao().deleteAllMissions();
            return null;
        }
    }


}
