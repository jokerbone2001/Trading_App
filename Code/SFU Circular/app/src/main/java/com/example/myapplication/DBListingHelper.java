package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBListingHelper extends SQLiteOpenHelper {

    public static final String LISTING_TABLE = "LISTING_TABLE";
    public static final String COL_UNIQUEID = "UNIQUEID";
    public static final String COL_NAME = "NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_USERID = "UserId";
    public static final String COL_IMAGE = "IMAGE";

    public ByteArrayOutputStream imgOutStream;
    public byte[] imgInByte;

    public DBListingHelper(@Nullable Context context) {

        //database called Listing
        super(context, "Listing.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement1 = "CREATE TABLE " + LISTING_TABLE +
                " ("
                + COL_UNIQUEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "         //unique ID column
                + COL_NAME + " TEXT, "                                          //name column
                + COL_DESCRIPTION + " TEXT, "                                   //description column
                + COL_PHONE + " TEXT, "                                         //phone column
                + COL_EMAIL + " TEXT, "                                         //email column
                + COL_USERID + " TEXT, "                                        //User ID column
                + COL_IMAGE + " BLOB)";                                         //Image column

        //run the above sql query
        db.execSQL(createTableStatement1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * function to add new users
     * @param listing: the listing to be added
     * @return true if listing was added false otherwise
     */
    public boolean add(ListingClass listing) {

        SQLiteDatabase ldb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Bitmap image = listing.getImage();
        imgOutStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,imgOutStream);
        imgInByte = imgOutStream.toByteArray();

        //Think ContentValues as a HashMap to load Strings, ints, and etc. into one Object
        cv.put(COL_NAME, listing.getItemName());
        cv.put(COL_DESCRIPTION, listing.getDescription());
        cv.put(COL_PHONE, listing.getPhone());
        cv.put(COL_EMAIL, listing.getEmail());
        cv.put(COL_USERID, listing.getId());
        cv.put(COL_IMAGE,imgInByte);

        //return true if insert was successful and false otherwise
        return ldb.insert(LISTING_TABLE, null, cv) != -1;
    }

    /**
     *
     * @return ArrayList with all the listings
     */
    public ArrayList<ListingClass> getListingInfo(){

        ArrayList<ListingClass> infoList = new ArrayList<>();

        //"SELECT * FROM "
        String queryString = "SELECT * FROM " + LISTING_TABLE;
        SQLiteDatabase listing = this.getReadableDatabase();

        Cursor cursor = listing.rawQuery(queryString,null);
        if(cursor.moveToFirst())
            do{
                String uniqueID = cursor.getString(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String phone = cursor.getString(3);
                String email = cursor.getString(4);
                String uid = cursor.getString(5);
                byte[] imgByte = cursor.getBlob(6);

                //convert byte[] to Bitmap type
                Bitmap image = BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);

                //create a listing to append to the list
                ListingClass info = new ListingClass(Integer.parseInt(uniqueID), name, description, phone, email,
                        Integer.parseInt(uid),image);

                //add the above created listing to the ArrayList
                infoList.add(info);

            }while(cursor.moveToNext());

        cursor.close();
        listing.close();
        return infoList;
    }

    /**
     *
     * @return the most recent listing
     */
    public ListingClass getLastListing(){

        ListingClass lastListing = new ListingClass();
        SQLiteDatabase ldb = this.getReadableDatabase();

        Cursor cursor = ldb.query(LISTING_TABLE,null,null,null,null,null,COL_UNIQUEID+" desc","1");

        if(cursor.moveToNext()){
            lastListing.setId(Integer.parseInt(cursor.getString(0)));
            lastListing.setItemName(cursor.getString(1));
            lastListing.setDescription(cursor.getString(2));
            lastListing.setPhone(cursor.getString(3));
            lastListing.setEmail(cursor.getString(4));
            lastListing.setItemId(Integer.parseInt(cursor.getString(5)));
        }

        cursor.close();
        return lastListing;
    }

    /**
     *
     * @param id ID
     * @return true if delete was successful false otherwise
     */
    public boolean deleteListing(String id) {

        return getReadableDatabase().delete(LISTING_TABLE, COL_UNIQUEID + "=?", new String[]{id}) > 0;
    }
}
