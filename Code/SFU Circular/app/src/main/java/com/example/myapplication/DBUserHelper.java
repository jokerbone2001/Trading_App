package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBUserHelper extends SQLiteOpenHelper {
    //This is the helper class for the "user" database table
    //.. final/constants names for easy access of SQL table and columns
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COL_LOGINID = "LOGINID";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_USERID = "USERID";

    public DBUserHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //--                                           Table Name
        String createTableStatement= "CREATE TABLE " + USER_TABLE +
                " (" + COL_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  //user ID column
                + COL_LOGINID + " TEXT, "                                   //login ID column
                + COL_PASSWORD + " TEXT, "                                  //password column
                + COL_EMAIL + " TEXT)";                                     //email column
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean add(UserClass userClass){
        //function to add new users
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //Think ContentValues as a HashMap to load Strings, ints, and etc. into one Object
        cv.put(COL_LOGINID,userClass.getLoginID());
        cv.put(COL_PASSWORD,userClass.getPassword());
        cv.put(COL_EMAIL,userClass.getEmail());

        //insert the ContentValues (cv) into USER_TABLE (user.db)
        long insert = db.insert(USER_TABLE, null, cv);

        if(insert == -1){
            //-1 failed to add data
            return false;
        }else{
            //otherwise successful
            return true;
        }
    }
}
