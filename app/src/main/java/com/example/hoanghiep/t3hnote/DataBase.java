package com.example.hoanghiep.t3hnote;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class DataBase {
    private static final String DATABASE_PATH = Environment
            .getDataDirectory().getAbsolutePath() +
            "/data/com.example.hoanghiep.t3hnote/";

    private static final String DATABASE_NAME = "T3HNote.db";

    private static final String TABLE_NOTE = "Note";
    private static final String COLUM_NOTE_ID = "Id";
    private static final String COLUM_NOTE_CONTENT = "Content";
    private static final String COLUM_NOTE_DATE = "Date";
    private static final String COLUM_NOTE_COLOR = "Color";
    private SQLiteDatabase sqLiteDatabase;

    public DataBase() {

    }

    public void copyDataBase(Context context) {
        try {
            //Mở file để đọc
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("T3HNote.db");
            //Mở file để ghi
            String path = DATABASE_PATH + DATABASE_NAME;
            File file = new File(path);
            if (file.exists()) {
                return;
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int length = 0;//biến này lưu số lượng byte đọc được
            //Copy dữ liệu
            while ((length = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }
            inputStream.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME,
                    null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public long insert(Note note) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_NOTE_CONTENT, note.getContent());
        contentValues.put(COLUM_NOTE_DATE, note.getDate());
        contentValues.put(COLUM_NOTE_COLOR, note.getColor());
        long id = sqLiteDatabase.insert(TABLE_NOTE, null, contentValues);
        closeDatabase();
        return id;
    }

    public void update(Note note) {

        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_NOTE_CONTENT, note.getContent());
        contentValues.put(COLUM_NOTE_DATE, note.getDate());
        contentValues.put(COLUM_NOTE_COLOR, note.getColor());

        sqLiteDatabase.update(TABLE_NOTE, contentValues, "Id=?", new String[]{String.valueOf(note.getId())});
        closeDatabase();
    }

    public ArrayList<Note> getAll() {
        openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NOTE + " ORDER BY " + COLUM_NOTE_DATE + " ASC", null);
        if (cursor == null) {
            Log.d("DataBase","null");
            cursor.close();
            closeDatabase();
            return null;
        }
        if (cursor.getCount() == 0) {
            Log.d("DataBase","getCount");
            cursor.close();
            closeDatabase();
            return new ArrayList<>();
        }
        ArrayList<Note> list = new ArrayList<>();
        cursor.moveToFirst();
        int indexId = cursor.getColumnIndex(COLUM_NOTE_ID);
        int indexDate = cursor.getColumnIndex(COLUM_NOTE_DATE);
        int indexContent = cursor.getColumnIndex(COLUM_NOTE_CONTENT);
        int indexColor = cursor.getColumnIndex(COLUM_NOTE_COLOR);

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(indexId);
            String date = cursor.getString(indexDate);
            String content = cursor.getString(indexContent);
            int color = cursor.getInt(indexColor);
            list.add(new Note(id, date, content, color));
            Log.d("DataBase","sa");
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return list;
    }
    public void delete(int id) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_NOTE, "Id=?", new String[]{String.valueOf(id)});
        closeDatabase();
    }
}
