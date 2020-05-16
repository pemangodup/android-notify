package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UpdateNote extends AppCompatActivity {
    private TextView idd;
    private TextView title;
    private TextView date;
    private TextView note;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        idd = findViewById(R.id.edit_id);
        title = findViewById(R.id.edit_title);
        date = findViewById(R.id.edit_date);
        note = findViewById(R.id.edit_note);
        image = findViewById(R.id.edit_img);
        update();
    }
    public void update(){
        int id = getIntent().getIntExtra("position",0);
        AddNoteJDBC db = new AddNoteJDBC(this, null,null,1);
        List<NoteModelClass> list = db.listAll(id);
        idd.setText(Integer.toString(id));
        title.setText(list.get(0).getTitle());
        date.setText(list.get(0).getDate());
        note.setText(list.get(0).getNote());
        byte[] imag = list.get(0).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imag, 0, imag.length);
        image.setImageBitmap(bitmap);
    }

    public void updateNow(View view){
        String id = idd.getText().toString();
        int idtypeChange = Integer.parseInt(id);
        String titlee = title.getText().toString();
        String datee = date.getText().toString();
        String notee = note.getText().toString();

        AddNoteJDBC db = new AddNoteJDBC(this, null, null, 1);
        db.updateNote(idtypeChange, titlee, datee, notee);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
