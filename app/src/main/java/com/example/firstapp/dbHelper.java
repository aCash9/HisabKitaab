package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class dbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HistoryDatabase3.0";
    public static final String TRANSACTION_LIST = "TransactionList";
    public static final String KEY_AMOUNT = "keyAmount";
    public static final String KEY_NOTE = "keyNote";
    public static final String KEY_DATE = "keyDate";
    public static final String KEY_TYPE = "keyType";
    public static final String KEY_BALANCE = "KEY_BALANCE";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabase = "CREATE TABLE " + TRANSACTION_LIST + " ( " + KEY_AMOUNT + " INTEGER, " + KEY_NOTE + " TEXT, " + KEY_DATE + " TEXT, " + KEY_TYPE + " TEXT, " + KEY_BALANCE + " INTEGER )";
        db.execSQL(createDatabase);
        Log.d("mytag", "onCreate: database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addItem(TransactionList item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_AMOUNT, item.getAmount());
        cv.put(KEY_NOTE, item.getNote());
        cv.put(KEY_DATE, item.getDate());
        cv.put(KEY_TYPE, item.getType());

        String tempBalance = getLastRowData();
        Log.d("mytag", "addTransaction: got the lastrowData ");

        int newBalance;

        if(tempBalance == null) {
            if(item.getType().equals("add"))
                newBalance = item.getAmount();
            else {
                newBalance = -item.getAmount();
            }
        } else {
            if (item.getType().equals("add")) {
                newBalance = Integer.parseInt(tempBalance) + item.getAmount();
            } else
                newBalance = Integer.parseInt(tempBalance) - item.getAmount();
        }
        Log.d("mytag", "addTransaction: if condition");

        cv.put(KEY_BALANCE, newBalance);
        long insert = db.insert(TRANSACTION_LIST, null, cv);

        return insert != -1;
    }

    public List<TransactionList> getData(){
        Log.d("mytag", "getData: IN");
        List<TransactionList> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TRANSACTION_LIST;
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                TransactionList item = new TransactionList();
                item.setAmount(cursor.getInt(0));
                item.setNote(cursor.getString(1));
                item.setDate(cursor.getString(2));
                item.setType(cursor.getString(3));
                list.add(item);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.d("mytag", "getData: list generated");
        return list;
    }

    public String getLastRowData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " + KEY_BALANCE + " FROM " + TRANSACTION_LIST + " WHERE ROWID = (SELECT MAX(ROWID) FROM " + TRANSACTION_LIST + ")";
        Cursor cursor = db.rawQuery(queryString, null);

        String data = null;
        if (cursor.moveToFirst()) {
            data = cursor.getString(0);
        }
        cursor.close();
        return data;
    }
}
