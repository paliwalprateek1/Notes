package com.example.prateek.people.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 16/5/16.
 */
public class NoteSaver extends SQLiteOpenHelper
{
    private static final String databasename = "notes.db";
    private static final int databaseversion = 1;

    public NoteSaver(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,databasename,factory,databaseversion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL( "CREATE TABLE NOTES(head TEXT,data TEXT PRIMARY KEY);" );
    }

    public void insertNotes(Notes notes)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "head" ,notes.getHead());
        values.put( "data" ,notes.getData());
        db.insert("Notes", null, values);
        db.close();

    }

    public void deleteNotes(Notes notes)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Notes", "data = ?", new String[]{notes.getData()});
        db.close();
    }

    Cursor cursor;

    public List<Notes> getNotes()
    {
        String dbstring = "";
        SQLiteDatabase db=getWritableDatabase();
        List<Notes> list = new ArrayList<>();
        cursor=db.rawQuery( " SELECT * FROM Notes " , null);

        if(cursor!=null&&cursor.getCount()>0)
        {

            cursor.moveToFirst();
            do
            {
                if(cursor.getString(cursor.getColumnIndex("head"))!=null) {
                    String str = cursor.getString(cursor.getColumnIndex("data"));
                    int n = str.length();
                    String s;
                    if(n<=30) {
                         s = str;
                    }
                    else
                    {
                         s = str.substring(0, 30);
                    }

                    Notes notes = new Notes(cursor.getString(cursor.getColumnIndex("head")),cursor.getString(cursor.getColumnIndex("data")));
                    list.add(notes);
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
