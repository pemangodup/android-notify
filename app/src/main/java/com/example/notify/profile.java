package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class profile extends AppCompatActivity {

    //varaible initiation for listing purpose in profile
    private TextView id;
    private TextView title;
    private TextView date;
    private TextView note;
    private ImageView imageee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        id = findViewById(R.id.id);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        note = findViewById(R.id.note);
        imageee = findViewById(R.id.img_view);
        display();
    }



    //To display in the dashboard/profile
    public void display(){
        Intent intent = getIntent();
        int position_same_database_id = intent.getIntExtra("position", 0);
        AddNoteJDBC db = new AddNoteJDBC(this, null, null, 1);
        List<NoteModelClass> listAll = db.listAll(position_same_database_id);
        String titlee = listAll.get(0).getTitle();
        String datee = listAll.get(0).getDate();
        String notee = listAll.get(0).getNote();
        byte[] image = listAll.get(0).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        String pos = Integer.toString(position_same_database_id);
        id.setText(pos);
        title.setText(titlee);
        date.setText(datee);
        note.setText(notee);
        imageee.setImageBitmap(bitmap);


    }

    //to edit the profile page
    public void editProfile(View view) {
        String ID = id.getText().toString();
        int id = Integer.parseInt(ID);
        Toast.makeText(this, "Check: "+id, Toast.LENGTH_SHORT).show();
        //AddNoteJDBC db = new AddNoteJDBC(this, null, null, 1);
        Intent intent = new Intent(this, UpdateNote.class);
        intent.putExtra("position", id);
        startActivity(intent);
    }


    public void delete(View view) {
        int local_id = Integer.parseInt(id.getText().toString());
        AddNoteJDBC db = new AddNoteJDBC(this, null, null, 1);
        db.deleteSelectedNote(local_id);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}