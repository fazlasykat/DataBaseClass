package com.hello.isdllab.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "syudent_table";

    //assign the column name of table
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "surname";
    public static final String COL_4 = "marks";

    public DatabaseHelper(Context context) {
        super ( context , DATABASE_NAME , null , 1 );
        //    SQLiteDatabase db = this.getWritableDatabase ();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String createSQL = " CREATE TABLE " + TABLE_NAME + "( " + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " INTEGER" + " )";
            db.execSQL ( createSQL );

        } catch (Exception e) {

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {

        try {

            String dropSQL = " DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL ( dropSQL );
            onCreate ( db );

        } catch (Exception e) {

        }

    }


    public boolean insertDATA(String name , String surname , int marks) {
        SQLiteDatabase db = this.getWritableDatabase ( );

        ContentValues contentValues = new ContentValues ( );

        contentValues.put ( COL_2,name );
        contentValues.put ( COL_3,surname );
        contentValues.put ( COL_4,marks );
        long result = db.insert ( TABLE_NAME,null,contentValues );

        if ( result == -1 ){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getALLDATA(){
    SQLiteDatabase db = this.getWritableDatabase ();
    Cursor res = db.rawQuery ( "Select * from "+TABLE_NAME,null );
    return res;

    }

    public boolean updateData(int id, String name,String surname,int marks){
        SQLiteDatabase db = this.getWritableDatabase ();

        ContentValues contentValues = new ContentValues (  );

        contentValues.put ( COL_1,id );
        contentValues.put ( COL_2,name );
        contentValues.put ( COL_3,surname );
        contentValues.put ( COL_4,marks );

        db.update ( TABLE_NAME, contentValues ,"id = ? ",new String[]{String.valueOf(id)} );
        return true;

    }

    public Integer deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase ();

        return db.delete ( TABLE_NAME,"id = ? ",new String[]{String.valueOf(id)} );
    }
}