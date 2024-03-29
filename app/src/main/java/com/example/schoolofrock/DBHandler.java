package com.example.schoolofrock;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // initialize constants for DB version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "schoolofrock.db";

    // initialize constants for song_vote table
    public static final String TABLE_SONG_VOTE = "song_vote";
    public static final String COLUMN_SONG_VOTE_ID = "_id";
    public static final String COLUMN_SONG_VOTE_SONG = "song";
    public static final String COLUMN_SONG_VOTE_VOTER = "voter";
    public static final String COLUMN_SONG_VOTE_VOTE = "vote";

    /**
     * Initializes a DBHandler.  Creates version of the database.
     * @param context reference to activity that initializes the DBHandler
     * @param factory null
     */
    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        // call superclass constructor
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates database table
     * @param sqLiteDatabase reference to schoolofrock database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // define create statement for student table
        String query = "CREATE TABLE " + TABLE_SONG_VOTE + "(" +
                COLUMN_SONG_VOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SONG_VOTE_SONG + " TEXT, " +
                COLUMN_SONG_VOTE_VOTER + " TEXT, " +
                COLUMN_SONG_VOTE_VOTE + "  INTEGER PRIMARY KEY AUTOINCREMENT" +
                ");";

        // execute create statement
        sqLiteDatabase.execSQL(query);
    }

    /**
     * This method gets executed when a new version of the database is initialized.
     * @param sqLiteDatabase reference to csdepartment database
     * @param oldVersion old version of database
     * @param newVersion new version of database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // execute drop statement that drops student table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG_VOTE);

        // call method that creates table
        onCreate(sqLiteDatabase);
    }

    public void likeSong (String song, String voter) {

        // get reference to csdepartment database.
        SQLiteDatabase db = getWritableDatabase();

        // initialize an empty ContentValues
        ContentValues values = new ContentValues();

        // put key-value pairs in ContentValues.  The key must be the name
        // of a column and the value is the value to be inserted in the column.
        values.put(COLUMN_SONG_VOTE_SONG, song);
        values.put(COLUMN_SONG_VOTE_VOTER, voter);

        // insert values into student table
        db.insert(TABLE_SONG_VOTE, null, values);

        // close reference to schoolofrock database
        db.close();
    }
}

