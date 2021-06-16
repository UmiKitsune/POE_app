package com.example.lastpoeapp.adapter;

import android.app.Application;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastpoeapp.MainActivity;
import com.example.lastpoeapp.R;
import com.example.lastpoeapp.database.Mission;
import com.example.lastpoeapp.database.TitleWithMissions;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<TitleWithMissions> allData;

    public NotesAdapter(List<TitleWithMissions> allData) {
        this.allData = allData;
    }

    public OnNoteClickListener onNoteClickListener;

    public interface OnNoteClickListener {
        void onNoteClick(int position);
        void onLongClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        TitleWithMissions data = allData.get(position);
        holder.textViewTitle.setText(data.getTitle().getName());

        holder.layout.removeAllViews();

        int color = holder.itemView.getResources().getColor(R.color.text);
        int green = holder.itemView.getResources().getColor(R.color.green);

        for (Mission mission : data.getMissions()) {
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText(mission.getText());
            if(mission.isClicked()) {
                textView.setTextColor(green);
            } else {
                textView.setTextColor(color);
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textView.setPadding(40, 15, 15, 15);
            textView.setId(mission.getId());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(textView.getId());
                    }
                }
            });
            holder.layout.addView(textView);

        }

    }


    @Override
    public int getItemCount() {
        return allData.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private LinearLayout layout;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            layout = itemView.findViewById(R.id.linearLayout);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    public void setAllData(List<TitleWithMissions> allData) {
        this.allData = allData;
    }

    public List<TitleWithMissions> getAllData() {
        return allData;
    }
}
