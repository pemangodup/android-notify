package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private TextView date;


    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_id);
        setAdapter();
    }

    //Setting an Adapter
    private void setAdapter(){
        LinearLayoutManager lilama = new LinearLayoutManager(this);
        lilama.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(lilama);
        AddNoteJDBC db = new AddNoteJDBC(this, null,null, 1);
        List<NoteModelClass> noteList = db.listFrontNote();
        NoteAdapter adapter = new NoteAdapter(noteList);
        rv.setAdapter(adapter);
    }


//    //intent for to go to addNote page (first)
     public void addNote(View view) {
        Intent intent = new Intent(this, AddNote.class);
        startActivity(intent);

    }


}
