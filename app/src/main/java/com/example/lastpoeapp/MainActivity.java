package com.example.lastpoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lastpoeapp.adapter.NotesAdapter;
import com.example.lastpoeapp.database.AppDao;
import com.example.lastpoeapp.database.AppDatabase;
import com.example.lastpoeapp.database.MainViewModel;
import com.example.lastpoeapp.database.Mission;
import com.example.lastpoeapp.database.Title;
import com.example.lastpoeapp.database.TitleWithMissions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private NotesAdapter adapter;

    //private AppDatabase database;
    //private AppDao dao;

    public List<Title> titles;
    public List<Mission> missions;
    private List<TitleWithMissions> allData = new ArrayList<>();

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database = AppDatabase.getInstance(this);
        //dao =database.getDao();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        titles = new AppArray().createTitles();
        missions = new AppArray().createMissions();

        if(viewModel.getTitlesCount() == 0) {
            viewModel.insertTitle(titles);
            viewModel.insertMission(missions);
        }

        //dao.insertTitles(titles);
        //dao.insertMissions(missions);
        //allData = dao.getAllTitleWithMissions();

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        adapter = new NotesAdapter(allData);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        getData();
        recyclerViewNotes.setAdapter(adapter);

        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
               TextView text = findViewById(position);
                if(viewModel.getMissions().get(position).isClicked()) {
                    viewModel.updateFalse(position);
                    //dao.updateMissionComplete(false, position);
                    text.setTextColor(getResources().getColor(R.color.text));

                } else {
                    viewModel.updateTrue(position);
                    //dao.updateMissionComplete(true, position);
                    text.setTextColor(getResources().getColor(R.color.green));

                }
                //getData();
                adapter.setAllData(allData);
            }

            @Override
            public void onLongClick(int position) {
                FragmentManager manager = getSupportFragmentManager();
                Popup popup = new Popup(position);
                popup.show(manager, "myDialog");
            }
        });

    }

    private void getData() {
        LiveData<List<TitleWithMissions>>allDataFromDB = viewModel.getAllData();
        allDataFromDB.observe(this, new Observer<List<TitleWithMissions>>() {
            @Override
            public void onChanged(List<TitleWithMissions> allDataFromLiveData) {
                allData.clear();
                allData.addAll(allDataFromLiveData);
                adapter.notifyDataSetChanged();
            }

        });
    }

    public void remove(int position) {
        Title title = allData.get(position).getTitle();
        viewModel.delete(title);
//        getData();
//        adapter.setAllData(allData);
        adapter.notifyDataSetChanged();
    }

}
