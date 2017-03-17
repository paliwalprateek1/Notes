package com.example.prateek.people.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahat on 10/12/15.
 */
public class PersonSave extends SQLiteOpenHelper
{
    private static final String databasename = "contacts.db";
    private static final int databaseversion = 2;

    public PersonSave(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
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
        db.execSQL( "CREATE TABLE Contact(name TEXT,phone TEXT PRIMARY KEY, path TEXT);" );
    }

    public void insertcontact(Person person)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "name" ,person.getName());
        values.put( "phone" ,person.getPhone());
        values.put("path", person.getPath());

        db.insert("Contact", null, values);
        db.close();

    }

    public void deletecontact(Person person)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("Contact", "phone = ?", new String[]{person.getPhone()});
        db.close();
    }

    Cursor cursor;

    public List<Person> getcontact()
    {
        String dbstring = "";
        SQLiteDatabase db=getWritableDatabase();
        List<Person> list = new ArrayList<>();
        cursor=db.rawQuery( " SELECT * FROM Contact " , null);

        if(cursor!=null&&cursor.getCount()>0)
        {

            cursor.moveToFirst();
            do
            {
                if(cursor.getString(cursor.getColumnIndex("name"))!=null) {
                    Person person = new Person(cursor.getString(cursor.getColumnIndex("name")),
                            cursor.getString(cursor.getColumnIndex("phone")),
                            cursor.getString(cursor.getColumnIndex("path")));
                    list.add(person);
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
