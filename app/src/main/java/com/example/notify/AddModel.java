package com.example.notify;

public class AddModel {
    private int id;
    private String title;
    private String date;
    private byte[] img;
    private String notes;
    private String create_date;

    public AddModel(int id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public AddModel(String title, String date, String notes) {
        this.title = title;
        this.date = date;
        this.notes = notes;
    }

    public AddModel(String title, String date, String notes, byte[] image) {
        this.title = title;
        this.date = date;
        this.img = image;
        this.notes = notes;
        this.create_date = create_date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
