package com.example.lastpoeapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.lastpoeapp.database.AppDatabase;

public class Popup extends AppCompatDialogFragment {
    private int position;
    public Popup(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Удаление";
        String message = "Хотите удалить это задание?";
        String button1String = "Да";
        String button2String = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               ((MainActivity) getActivity()).remove(position);
                Toast.makeText(getActivity(), "Удаленно",
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Рано избавляться от этого", Toast.LENGTH_LONG)
                        .show();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}
