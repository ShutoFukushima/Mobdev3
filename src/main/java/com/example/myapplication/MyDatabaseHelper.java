package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "rrem.db";
    private static final int DATABASE_VERSION = 2;
    //Table Products and Fields
    private static final String TABLE_NAME_PROD = "product_tbl";
    private static final String COLUMN_PROD1 = "prod_id";
    private static final String COLUMN_PROD2 = "prod_name";
    private static final String COLUMN_PROD3 = "prod_category";
    private static final String COLUMN_PROD4 = "prod_description";
    private static final String COLUMN_PROD5 = "prod_price";
    private static final String COLUMN_PROD6 = "prod_status";
    private static final String COLUMN_PROD7 = "prod_qty";


    //Table Receive and Fields
    private static final String TABLE_NAME_RCV = "receive_tbl";
    private static final String COLUMN_REC1 = "rec_id";
    private static final String COLUMN_REC2 = "rec_supplier";
    private static final String COLUMN_REC3 = "rec_contact";

    private static final String COLUMN_REC4 = "rec_amount";
    private static final String COLUMN_REC5 = "rec_qty";
    private static final String COLUMN_REC6 = "rec_date_added";
    private static final String COLUMN_REC7 = "rec_time_added";
    private static final String COLUMN_REC8 = "rec_saved";
    private static final String COLUMN_REC9 = "rec_status";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryProduct =
                "CREATE TABLE " + TABLE_NAME_PROD + " (" +
                        COLUMN_PROD1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Product ID, Auto_Increment
                        COLUMN_PROD2 + " TEXT NOT NULL, " + // Product Name
                        COLUMN_PROD3 + " TEXT NOT NULL, " + // Product Category
                        COLUMN_PROD4 + " TEXT NOT NULL, " + // Product Description
                        COLUMN_PROD5 + " DECIMAL(10,2), " + // Product Price
                        COLUMN_PROD6 + " INTEGER DEFAULT 0, " + // Product Status
                        COLUMN_PROD7 + " INTEGER NOT NULL)";
        db.execSQL(queryProduct);
        String queryReceive =
                "CREATE TABLE " + TABLE_NAME_RCV + " (" +
                        COLUMN_REC1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Placeholder for rec_id 0
                        COLUMN_REC2 + " TEXT, " + // Placeholder for rec_supplier 1
                        COLUMN_REC3 + " TEXT, " + // Placeholder for rec_contact 2
                        COLUMN_REC4 + " INTEGER, " + // Placeholder for rec_qty 3
                        COLUMN_REC5 + " INTEGER, " + // Placeholder for rec_amount 4
                        COLUMN_REC6 + " TEXT, " + // Placeholder for rec_date_added 5
                        COLUMN_REC7 + " TEXT, " + // Placeholder for rec_time_added 6
                        COLUMN_REC8 + " INTEGER DEFAULT 0, " + // Placeholder for rec_saved 7
                        COLUMN_REC9 + " INTEGER DEFAULT 0)"; // Placeholder for rec_status 8
        db.execSQL(queryReceive);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROD);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RCV);
            onCreate(db);
        }
    }
    void AddProduct(String name, String category, String description, String price, String qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PROD2, name);
        cv.put(COLUMN_PROD3, category);
        cv.put(COLUMN_PROD4, description);
        cv.put(COLUMN_PROD5, price);
        cv.put(COLUMN_PROD7, qty);

        long result = db.insert(TABLE_NAME_PROD, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void AddSupplier(String supplier, String contact, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REC2, supplier);
        cv.put(COLUMN_REC3, contact);
        cv.put(COLUMN_REC6, date);
        long result = db.insert(TABLE_NAME_RCV, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllDataRCV(){
        String query = "SELECT * FROM " + TABLE_NAME_RCV;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllDataPROD(){
        String query = "SELECT * FROM " + TABLE_NAME_PROD;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}