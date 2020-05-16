package com.example.notify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNote extends AppCompatActivity {
    //initializing date variables
    private TextView dateView;
    private TextView datePicker;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0;

    //initializing image variables
    private static final int PICK_IMAGE = 1;
    private ImageView profileImg;
    Uri imageUri;

    //initializing variables for saving
    private EditText title;
    private EditText date;
    private EditText notes;
    AddNoteJDBC db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        dateView = findViewById(R.id.optional_date);
        showDialogOnButtonClick();


        //communicating for saving with fields in xml
        title = findViewById(R.id.title_id);
        date = findViewById(R.id.optional_date);
        notes = findViewById(R.id.note_id);
        profileImg = findViewById(R.id.img);

    }

    //for image
    // goes into the gallery and picks up the image
    public void addImage(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
    }

    //image will be displayed in the image view by this overrided method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImg.setImageBitmap(bitmap);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
    //end for image part

    public void showDialogOnButtonClick(){
        datePicker = findViewById(R.id.optional_date);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DILOG_ID){
            return new DatePickerDialog(this, datepickerlistener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datepickerlistener =
            new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear +1;
            day_x = dayOfMonth;
            dateView.setText(year_x+" /"+month_x+" /"+day_x);
        }
    };

    //save note in the database
    public void saveNote(View view){
        //Bitmap image=((BitmapDrawable)profileImg.getDrawable()).getBitmap();
        BitmapDrawable bd = (BitmapDrawable) profileImg.getDrawable();
        Bitmap bm = bd.getBitmap();
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        db = new AddNoteJDBC(this, null, null, 1);
        AddModel am = new AddModel(title.getText().toString(), date.getText().toString(), notes.getText().toString(), byteArray);
        db.saveNote(am);
        Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
