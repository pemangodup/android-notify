package com.example.notify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AddNoteJDBC extends SQLiteOpenHelper {

   public static final int DB_VERSION = 1;
   public static final String DB_NAME = "Notify";
   public static final String TAB_NAME = "note_tab";
   public static final String COL_ID = "id";
   public static final String COL_TITLE = "title";
   public static final String COL_NOTE_DATE = "notify_date";
   public static final String IMAGE = "image";
   public static final String NOTE = "note";
   public static final String CURRENT_DATE = "current_date";

    public AddNoteJDBC(@Nullable Context context, @Nullable String name,
                       @Nullable SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DB_NAME , factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+TAB_NAME+"("+COL_ID+" integer primary key autoincrement,"
                + COL_TITLE+ " text,"
                + COL_NOTE_DATE+ " text,"
                + IMAGE+ " BLOB,"
                + NOTE +" text,"
                + CURRENT_DATE + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists "+TAB_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void saveNote(AddModel am){
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, am.getTitle());
        cv.put(COL_NOTE_DATE, am.getDate());
        cv.put(NOTE, am.getNotes());
        cv.put(IMAGE, am.getImg());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TAB_NAME, null, cv);
        db.close();
    }


    //list note in dashboard with only title and created date
    public List<NoteModelClass> listFrontNote(){
        List<NoteModelClass> listSend = new ArrayList<>();
        String query = "select "+COL_ID+","+COL_TITLE+","+COL_NOTE_DATE+" from "+ TAB_NAME;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(COL_ID));
                String title = c.getString(c.getColumnIndex(COL_TITLE));
                String date = c.getString(c.getColumnIndex(COL_NOTE_DATE));
                NoteModelClass an = new NoteModelClass(id,title, date);
                listSend.add(an);
            }while(c.moveToNext());
            c.close();
            db.close();
        }
        return listSend;
    }

    public List<NoteModelClass> listAll(int position) {
        List<NoteModelClass> allList=new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM note_tab where id =  "+position+"", null);
        //if the cursor has some data
        if (cursor.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                allList.add(new NoteModelClass(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getBlob(3)
                ));

            } while (cursor.moveToNext());
        }
        //closing the cursor
        cursor.close();
        db.close();

        //adding the adapter to listview

        return allList;
    }


    public void updateNote(int id, String title, String date, String note) {
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, title);
        cv.put(COL_NOTE_DATE, date);;
        cv.put(NOTE, note);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TAB_NAME, cv, "id=?",new String[] {String.valueOf(id)});
        db.close();
    }

    //delete the note of selected position
    public void deleteSelectedNote(int id){
        String sql = "delete from "+TAB_NAME+" where id= "+id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
}
