package com.example.tolotranet.livecampus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tolotra Samuel on 16/08/2016.
 */
public class Sign_DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS_CHECK = "passcheck";
    private static final String COLUMN_PASS = "pass";
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
            "name text  null , email text  null , uname text not null, pass text not null, passcheck boolean null);";

    SQLiteDatabase db;

    public Sign_DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    public  void insertContact(Sign_User_Object c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_PASS_CHECK, c.getPassCheck());
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();
    };


    public  void cleanTable() {
        db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    public  void updateUser(Sign_User_Object c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_PASS_CHECK, c.getPassCheck());
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

        db.update(TABLE_NAME,values, null, null);
        db.close();
    };


    public  void switch_sign_check()
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean x = check_pass_requirement();

       boolean newpass = true;
        if(x){
            Log.d("hello swicth", "to no");
            newpass = false;
        }
        if(!x){
            Log.d("hello swicth", "to yes");
            newpass = true;
        }

        values.put(COLUMN_PASS_CHECK, newpass);
        db.update(TABLE_NAME,values, null, null);
        db.close();
    };

    public  void Update_user_ID(int id)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, id);
        db.update(TABLE_NAME,values, null, null);
        db.close();
    };

    public boolean check_log_exist(){
        db = this.getReadableDatabase();
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        if(count == 1) {
            Log.d("passcheck", "log exists so true because count == 1");
            return true;
        }else{
            Log.d("passcheck", "database virgin, or log superior to one, so mistake so false");
            return  false;
        }
    };

    public  String getUserEmail(){
        Log.d("hello", "0");
        db = this.getReadableDatabase();
        String query = "select email,pass from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String emailDB = "username@example.com";
        Log.d("hello", "1");
        if(cursor.moveToFirst()) {
            Log.d("hello", "2");
            do {
                emailDB = cursor.getString(0);
                Log.d("hello", "3");
                return emailDB;
            }
            while (cursor.moveToNext());
        }
        Log.d("hello", "4");
        return emailDB;
    };

    public  int getUserId(){
        Log.d("hello", "0");
        db = this.getReadableDatabase();
        String query = "select id from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int userid = 999998;
        Log.d("hello", "1");
        if(cursor.moveToFirst()) {
            Log.d("hello", "2");
            do {
                userid = cursor.getInt(0);
                Log.d("hello", "3");
                return userid;
            }
            while (cursor.moveToNext());
        }
        Log.d("hello", "4");
        return userid;
    };


    public boolean check_pass_requirement() {
        Log.d("passcheck requirement", "1");
        db = this.getReadableDatabase();
        String query = "select passcheck from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("passcheck requirement", "11");
        int a;
        if(cursor.moveToFirst()) {
            do {
                a= cursor.getInt(0);
                Log.d("passcheck requirement", "111");
                if(a == 1){
                    Log.d("passcheck requirement", "passcheck equals true, go to sign in, password required");
                    return true;
                }
                if(a == 0){
                    Log.d("passcheck requirement", "passcheck equals false, go to appselect");
                    return false;
                }
            }
            while (cursor.moveToFirst());
        }
        Log.d("passcheck requirement", "no passcheck found, so true, go to sign up");
        return true;
    }

    public String  searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select email, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                b = cursor.getString(1);

                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
