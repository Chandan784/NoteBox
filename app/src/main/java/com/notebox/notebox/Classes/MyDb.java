package com.notebox.notebox.Classes;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.sql.Types.NULL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "NoteDb";
    private static final String TABLE_NAME= "Notes";
    private static final String KEY_ID= "id";
    private static final String NOTE_TITLE= "title";
    private static final String NOTE_DETAILS= "details";
    private static final String NOTE_DATE= "date";



    private static final int DATABASE_VERSION= 1;

    public MyDb(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

     db.execSQL("CREATE TABLE " + TABLE_NAME +
             "("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_TITLE + " TEXT, " + NOTE_DETAILS + " TEXT, " + NOTE_DATE + " TEXT " + ")");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addNote(String title, String details, String date){
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(NOTE_TITLE,title);
        contentValues.put(NOTE_DETAILS,details);
        contentValues.put(NOTE_DATE,date);
       db.insert(TABLE_NAME,null,contentValues);


    }
    public ArrayList<MyNotesModel> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("select * from " + TABLE_NAME + "",null);
        ArrayList<MyNotesModel> mydata = new ArrayList<>();
       while (cursor.moveToNext()){

           mydata.add(new MyNotesModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));

       }
       return mydata;
    }

   public boolean deleteRow( int id){
        SQLiteDatabase db = this.getWritableDatabase();

     return    db.delete( TABLE_NAME ,""+KEY_ID+" = "+id+"",null ) > 0;

    }
    public  void updateRow(int id,String title,String details,String date){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_TITLE,title);
        contentValues.put(NOTE_DETAILS,details);
        contentValues.put(NOTE_DATE,date);
        db.update(TABLE_NAME,contentValues,""+KEY_ID+" = "+id+"",null);

    }

}
