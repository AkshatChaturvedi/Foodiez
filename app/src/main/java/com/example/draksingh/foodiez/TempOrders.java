package com.example.draksingh.foodiez;

import android.content.ContentProvider;

/**
 * Created by Dr. A K Singh on 10/5/2017.
 */
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils ;

/**
 * Created by Dr. A K Singh on 10/5/2017.
 */

public class TempOrders extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.draksingh.foodiez.a";
    //package name paste
    static final String URL = "content://" + PROVIDER_NAME + "/temporders";
    //instead of students put tbale name
    static final Uri CONTENT_URI = Uri.parse(URL);
    // columns
    static final String _REG = "_reg";
    static final String FOODNAME = "foodname";
    static final String CANTEEN = "canteen";
    static final String DATE = "date";
    static final String TIME = "time";
    static final String PRICE = "price";
    static final String TPRICE= "tprice";
    static final String QTY= "qty";

    private static HashMap<String, String> TEMPORDERS_PROJECTION_MAP;

    static final int TEMPORDERS = 1;
    static final int TEMPORDER_ID = 2;

    static final UriMatcher uriMatcher;

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "temporders", TEMPORDERS);
        uriMatcher.addURI(PROVIDER_NAME, "temporders/#", TEMPORDER_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Foodiez1";
    static final String TEMPORDERS_TABLE_NAME = "temporders";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + TEMPORDERS_TABLE_NAME +
                    " ( _reg TEXT  , " +
                    " canteen TEXT , " +
                    " foodname TEXT , " +
                    " price TEXT ," +
                    " qty TEXT , " +
                    " tprice TEXT ," +
                    " date TEXT , " +
                    " time TEXT " +
                     ");";
    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TEMPORDERS_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
        long rowID = db.insert(TEMPORDERS_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TEMPORDERS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case TEMPORDERS:
                qb.setProjectionMap(TEMPORDERS_PROJECTION_MAP);
                break;

            case TEMPORDER_ID:
                qb.appendWhere(_REG + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == "") {
            /**
             * By default sort on student names
             */
            sortOrder = FOODNAME;
        }

        Cursor c = qb.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case TEMPORDERS:
                count = db.delete(TEMPORDERS_TABLE_NAME, selection, selectionArgs);
                break;

            case TEMPORDER_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(TEMPORDERS_TABLE_NAME, _REG + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case TEMPORDERS:
                count = db.update(TEMPORDERS_TABLE_NAME, values, selection, selectionArgs);
                break;

            case TEMPORDER_ID:
                count = db.update(TEMPORDERS_TABLE_NAME, values,
                        _REG + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            /**
             * Get all student records
             */
            case TEMPORDERS:
                return "vnd.android.cursor.dir/vnd.example.temporders";
            /**
             * Get a particular student
             */
            case TEMPORDER_ID:
                return "vnd.android.cursor.item/vnd.example.temporders";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
