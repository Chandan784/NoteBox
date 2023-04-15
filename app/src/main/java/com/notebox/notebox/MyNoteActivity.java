package com.notebox.notebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.notebox.notebox.Classes.MyDb;
import com.notebox.notebox.Classes.MyNotesAdapter;
import com.notebox.notebox.Classes.MyNotesModel;

import java.util.ArrayList;

public class MyNoteActivity extends AppCompatActivity {
RecyclerView recyclerView ;
ArrayList<MyNotesModel> note_list = new ArrayList<>();;
MyNotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);

        recyclerView = findViewById(R.id.rv_mynotes);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MyDb myDb = new MyDb(this);

        note_list=  myDb.getNotes();
        adapter = new MyNotesAdapter(this,note_list,recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}