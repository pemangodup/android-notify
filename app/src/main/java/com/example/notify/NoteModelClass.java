package com.example.notify;

import android.graphics.Bitmap;

public class NoteModelClass {
    private int id;
    private String title;
    private String note;
    private String date;
    private byte[] image;

    public NoteModelClass(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public NoteModelClass(String title, String date, String notes, byte[] image) {
        this.title = title;
        this.date = date;
        this.note = notes;
        this.image = image;
    }





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


