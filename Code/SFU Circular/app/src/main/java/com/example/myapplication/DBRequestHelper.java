package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class DBRequestHelper extends SQLiteOpenHelper {
    //This is the helper class for the "user" database table
    //.. final/constants names for easy access of SQL table and columns

    private SQLiteDatabase db;

    public static final String REQUEST_TABLE = "REQUEST_TABLE";
    public static final String COL_UNIQUEID = "UNIQUEID";
    public static final String COL_NAME = "NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_Contact_Info = "Contact_Info";
    public static final String COL_UserId = "UserId";
    public static final String COL_IMAGE = "IMAGE";

    public ByteArrayOutputStream imgOutStream;
    public byte[] imgInByte;

    public DBRequestHelper(Context context) {
        super(context, "request.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement1 = "CREATE TABLE " + REQUEST_TABLE +
                " (" + COL_UNIQUEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  //unique ID column
                + COL_NAME + " TEXT, "                                       //Name Column
                + COL_DESCRIPTION + " TEXT, "                                   //login ID column
                + COL_Contact_Info + " TEXT, "                                  //password column
                + COL_UserId + " TEXT, "                                     //email column
                + COL_IMAGE + " BLOB)";                                     //Image col
        db.execSQL(createTableStatement1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean add(RequestClass requestClass) {
        //function to add new users
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Bitmap imageBitmap = requestClass.getImage();
        imgOutStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,imgOutStream);
        imgInByte = imgOutStream.toByteArray();

        //Think ContentValues as a HashMap to load Strings, ints, and etc. into one Object

        cv.put(COL_NAME, requestClass.getName());
        cv.put(COL_DESCRIPTION, requestClass.getDescription());
        cv.put(COL_Contact_Info, requestClass.getContact_info());
        cv.put(COL_UserId, requestClass.getUserid());
        cv.put(COL_IMAGE,imgInByte);

        //insert the ContentValues (cv) into USER_TABLE (user.db)
        long insert = db.insert(REQUEST_TABLE, null, cv);

        if (insert == -1) {
            //-1 failed to add data
            return false;
        } else {
            //otherwise successful
            return true;
        }
    }

    public List<RequestClass> getRequestInfo(){
        List<RequestClass> infoList = new ArrayList<>();
        //"SELECT * FROM " - translate into select everything from "Database table name"
        String queryString = "SELECT * FROM "+REQUEST_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                String uniqueID = cursor.getString(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String contact_info = cursor.getString(3);
                String uid = cursor.getString(4);
                byte[] imgByte = cursor.getBlob(5);
                //convert byte[] to Bitmap
                Bitmap toBitmap = BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);
                RequestClass newRequest = new RequestClass(Integer.parseInt(uniqueID),name,description,contact_info,uid,toBitmap);

                infoList.add(newRequest); //loads all info from User Database into a List

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close(); //remember to close the Cursor
        db.close();  // and the DB
        return infoList; //returns the List to where ever this function is called

    }

    public RequestClass getLastRequest(){
        RequestClass data = new RequestClass();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(REQUEST_TABLE,null,null,null,null,null,COL_UNIQUEID+" desc","1");
        if(cursor.moveToNext()){
            data.setId(Integer.parseInt(cursor.getString(0)));
            data.setName(cursor.getString(1));
            data.setDescription(cursor.getString(2));
            data.setContact_info(cursor.getString(3));
            data.setUserid(cursor.getString(4));
        }
        return data;
    }

    public boolean deleteRequest(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(REQUEST_TABLE,COL_UNIQUEID+"=?",new String[]{id})>0;
    }
}
