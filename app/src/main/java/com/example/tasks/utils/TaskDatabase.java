package com.example.tasks.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TaskDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TaskDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "task_table";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK = "task";

    public TaskDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK, task);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result==-1) {
            Toast.makeText(context, "failed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "added!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllTask() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK, taskName);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if(result==-1) {
            Toast.makeText(context, "failed to update!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if(result==-1) {
            Toast.makeText(context, "failed to delete!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "deleted!", Toast.LENGTH_SHORT).show();
        }
    }

}
