package com.example.android.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alwaris on 5/22/17.
 */

public class OrderTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    private static final String TABLE_ORDER = "orderTABLE";
    private static final String COLUMN_ID_ORDER = "_id";
    private static final String COLUMN_OFFICER = "Officer";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_FOODORDER = "FoodOrder";
    private static final String COLUMN_ITEM = "Item";

    public OrderTABLE(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();
    }//Constructor

    public long addValueToOrder(String strOfficer, String strDate, String strFoodOrder, int intItem) {
        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_OFFICER, strOfficer);
        objContentValue.put(COLUMN_DATE, strDate);
        objContentValue.put(COLUMN_FOODORDER, strFoodOrder);
        objContentValue.put(COLUMN_ITEM, intItem);

        return writeSQLite.insert(TABLE_ORDER,null,objContentValue);
    }//AddValueToOrder
}//Main Class
