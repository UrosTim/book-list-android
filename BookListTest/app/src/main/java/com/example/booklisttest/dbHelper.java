package com.example.booklisttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class dbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "ReadingList.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "books";
    private static final String COLUMN_ID = "books_id";
    private static final String COLUMN_TITLE = "books_title";
    private static final String COLUMN_AUTHOR = "books_author";
    private static final String COLUMN_PAGES = "books_pages";


    dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String title, String author, int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_PAGES, pages);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context, "Failed to add this book.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Book added successfully.", Toast.LENGTH_LONG).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_PAGES, pages);

        long result = db.update(TABLE_NAME, contentValues, "books_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to update this book.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Book updated successfully.", Toast.LENGTH_LONG).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "books_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete this book.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Book deleted successfully.", Toast.LENGTH_LONG).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
