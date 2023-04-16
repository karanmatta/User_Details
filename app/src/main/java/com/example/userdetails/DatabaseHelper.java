package com.example.userdetails;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userdetails.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_NAME = "userdetails";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String CONTACT = "contact";
    public static final String ADDRESS = "address";
    public static final String DOB = "dob";

    // Create table query
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME + " TEXT NOT NULL," +
                    EMAIL + " TEXT NOT NULL," +
                    CONTACT + " TEXT NOT NULL," +
                    ADDRESS + " TEXT NOT NULL," +
                    DOB + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
