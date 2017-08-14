package com.qkahmadzai.mtn.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiyamuddin Ahmadzai on 8/3/2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "mtn_packages.db";
    public static final String PK_TABLE_NAME = "internetbundle";

    public static final String PK_COLUMN_ID = "id";
    public static final String PK_COLUMN_BUNDLE_TYPE = "bundleType";
    public static final String PK_COLUMN_VOLUME = "volume";
    public static final String PK_COLUMN_VALIDATION = "validation";
    public static final String PK_COLUMN_PRICE = "price";
    public static final String PK_COLUMN_SUB_METHOD = "subMethod";

    //private static final String ST_CREATE_TABLE_IN = ;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        Log.i("DB", "context CALLED");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS internetbundle " +
                        "(id integer primary key, bundleType text, volume text, validation text, price text, subMethod text)"
        );

        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + PK_TABLE_NAME+
                "("+PK_COLUMN_ID+" integer primary key, "+PK_COLUMN_BUNDLE_TYPE+" text, "+PK_COLUMN_VOLUME+" text,"+
                PK_COLUMN_VALIDATION+" text"+PK_COLUMN_PRICE+" text "+ PK_COLUMN_SUB_METHOD+" text)"
                );*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+PK_TABLE_NAME);
        onCreate(db);
    }




    public long insertInternetBundle (Packages pk) {
        Log.i("DB", "InsertInternet Bundle");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PK_COLUMN_ID, pk.getId());
        contentValues.put(PK_COLUMN_BUNDLE_TYPE, pk.getBundleType());
        contentValues.put(PK_COLUMN_VOLUME, pk.getVolume());
        contentValues.put(PK_COLUMN_VALIDATION, pk.getValidation());
        Log.i("DB", "Before Price"+ PK_COLUMN_PRICE);
        contentValues.put(PK_COLUMN_PRICE, pk.getPrice());
        Log.i("DB", "After Price"+ PK_COLUMN_PRICE);
        contentValues.put(PK_COLUMN_SUB_METHOD, pk.getSubMethod());

        long pk_id = db.insert(PK_TABLE_NAME, null, contentValues);
      //  Log.i("DB", " pk_id"+ pk_id);
        return pk_id;
    }


    // Getting all SensorData
    public List<Packages> getAllPackages() {
        List<Packages> pkDatas = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + PK_TABLE_NAME;

        Log.e("DB", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if(c.moveToFirst()) {
            do {

                Packages pk = new Packages();
                pk.setId(c.getInt(c.getColumnIndex(PK_COLUMN_ID)));
                pk.setBundleType(c.getString(c.getColumnIndex(PK_COLUMN_BUNDLE_TYPE)));
                pk.setVolume(c.getString(c.getColumnIndex(PK_COLUMN_VOLUME)));
                pk.setValidation(c.getString(c.getColumnIndex(PK_COLUMN_VALIDATION)));
                pk.setPrice(c.getString(c.getColumnIndex(PK_COLUMN_PRICE)));
                pk.setSubMethod(c.getString(c.getColumnIndex(PK_COLUMN_SUB_METHOD)));


                // adding to list
                pkDatas.add(pk);
            } while (c.moveToNext());
        }
        return pkDatas;
    }


    public int numberOfInternetPackages(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PK_TABLE_NAME);
        return numRows;
    }




}
