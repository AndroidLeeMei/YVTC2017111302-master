package com.example.yvtc.yvtc2017111302.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by auser on 2017/11/20.
 */

public class StudentDBHelper extends SQLiteOpenHelper {
    static String FILENAME="stu";
    static int version=1;

    //預設的建構式
//    public StudentDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//    自己方便用的建構式
    public StudentDBHelper(Context context){
        super(context, FILENAME, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE  TABLE \"main\".\"students\" (\"_id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"name\" VARCHAR, \"tel\" VARCHAR, \"addr\" VARCHAR)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
