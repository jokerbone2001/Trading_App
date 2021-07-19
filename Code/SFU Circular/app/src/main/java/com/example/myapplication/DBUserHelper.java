package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBUserHelper extends SQLiteOpenHelper {
    //This is the helper class for the "user" database table
    //.. final/constants names for easy access of SQL table and columns

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COL_UNIQUEID = "UNIQUEID";
    public static final String COL_NAME = "NAME";
    public static final String COL_LOGINID = "LOGINID";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_EMAIL = "EMAIL";

    public static final String REQUEST_TABLE = "REQUEST_TABLE";
    public static final String COL_REQUEST_UNIQUEID = "UNIQUEID";
    public static final String COL_REQUEST_NAME = "NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_Contact_Info="Contact_Info";
    public static final String COL_UserId="UserId";


    public DBUserHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //--                                           Table Name
        String createTableStatement= "CREATE TABLE " + USER_TABLE +
                " (" + COL_UNIQUEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  //unique ID column
                + COL_NAME + " TEXT, "                                       //Name Column
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
        cv.put(COL_NAME,userClass.getName());
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
    public List<UserClass> getUserInfo(){
        List<UserClass> infoList = new ArrayList<>();
        //"SELECT * FROM " - translate into select everything from "Database table name"
        String queryString = "SELECT * FROM "+USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                /*
                all String variables
                col 0 = UNIQUEID
                col 1 = NAME
                col 2 = LOGINID (this is the person's sfu ID)
                col 3 = PASSWORD
                col 4 = EMAIL
                 */
                String uniqueID = cursor.getString(0);
                String name = cursor.getString(1);
                String loginID = cursor.getString(2);
                String password = cursor.getString(3);
                String email = cursor.getString(4);
                UserClass newUser = new UserClass(Integer.parseInt(uniqueID),name,loginID,password,email);

                infoList.add(newUser); //loads all info from User Database into a List

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close(); //remember to close the Cursor
        db.close();  // and the DB
        return infoList; //returns the List to where ever this function is called

    }

}
