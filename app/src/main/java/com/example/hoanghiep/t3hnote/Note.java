package com.example.hoanghiep.t3hnote;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class Note {
    private int id;
    private String date;
    private String content;
    private int color;

    public Note(String date, String content, int color) {
        this.date = date;
        this.content = content;
        this.color = color;
    }
    public Note(int id,String date, String content, int color) {
        this.date = date;
        this.id=id;
        this.content = content;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
