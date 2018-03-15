package com.appestan.moodsaroundme;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

public class BuddyRepo {
    private static final String TAG = "BuddyRepo";
    DBHelper dbHelper;
    private SQLiteDatabase database;
    public final static String ID_ = "_id";
    public final static String NAME_ = "name";
    public final static String BIRTHDAY = "birthday";
    public final static String BIRTHDAY_DAY = "birthdayDay";
    public final static String BIRTHDAY_MONTH = "birthdayMonth";
    public final static String BIRTHDAY_YEAR = "birthdayYear";

    public final static String BUDDYTABLE = "BuddyRepo";

    public BuddyRepo(DBHelper dbHelper) {
        Log.d(TAG, "BuddyRepo: ");
        this.dbHelper = dbHelper;
        this.database = dbHelper.getWritableDatabase();
    }

    public void save(Friend friend) {
        Log.d(TAG, "save: " + friend.getName());
        ContentValues values = new ContentValues();
        values.put(NAME_, friend.getName());
        values.put(BIRTHDAY, friend.getBirthday().toString());
        values.put(BIRTHDAY_DAY, friend.getDay_().toString());
        values.put(BIRTHDAY_MONTH, friend.getMonth_().toString());
        values.put(BIRTHDAY_YEAR, friend.getYear_().toString());
        database.insert(BUDDYTABLE, null, values);
    }

    public Cursor loadAllBuddies() {
        String[] cols = new String[]{NAME_, BIRTHDAY_DAY, BIRTHDAY_MONTH, BIRTHDAY_YEAR};
        Cursor mCursor = database.query(true, BUDDYTABLE, cols, null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
}
