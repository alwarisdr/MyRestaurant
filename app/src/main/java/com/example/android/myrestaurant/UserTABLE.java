package com.example.android.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alwaris on 5/22/17.
 */

public class UserTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_USER = "userTABLE";
    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_OFFICER = "Officer";

    public UserTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
    }//Constructor

    public long addValueToUser(String strUser, String strPassword, String strOfficer) {

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_USER, strUser);
        objContentValue.put(COLUMN_PASSWORD, strPassword );
        objContentValue.put(COLUMN_OFFICER, strOfficer);

        return writeSQLite.insert(TABLE_USER,null,objContentValue);
    }//addValueToUser

}//UserTABLE
