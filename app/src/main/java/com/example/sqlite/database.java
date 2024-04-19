package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    private static final String Database_Name = "Contact_Detail";
    private static final int Database_Version = 1;
    private static final String Table_Name = "Contact";
    private static final String Key_Id = "Id";
    private static final String Key_Name = "Name";
    private static final String Key_MobileNo = "Phone_Number";


    public database(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Table_Name + "( " +
                Key_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Key_Name + " TEXT NOT NULL, " +
                Key_MobileNo + " TEXT NOT NULL" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public void addContact(String Name, String Phone_Number){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Key_Name,Name);
        value.put(Key_MobileNo, Phone_Number);

        db.insert(Table_Name, null, value);
    }

    public ArrayList<modelcontact> fetchdata(){
        SQLiteDatabase sql = this.getReadableDatabase();
        ArrayList <modelcontact> array = new ArrayList<>();

        try {
            Cursor cursor = sql.rawQuery("SELECT * FROM " + Table_Name, null);

            while(cursor.moveToNext()){
                modelcontact md = new modelcontact();
                md.id = cursor.getInt(0);
                md.name = cursor.getString(1);
                md.phone_number = cursor.getString(2);
                array.add(md);
            }
        }
        catch (Exception e) //agar FROM ke baad space nahi hoga to error hoga
        {
            System.out.println("Ayush128 " + e);
        }
        return array;
    }

    public void update(modelcontact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Key_MobileNo,contact.phone_number);

        db.update(Table_Name,cv,Key_Id +" = " +contact.id,null);
    }

    public void delete(int i){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Table_Name,Key_Id+" = ? ", new String[]{String.valueOf(i)});

    }
}

