package com.example.yvtc.yvtc2017111302.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by auser on 2017/11/20.
 */

public class StudentDAODBImpl implements  StudentDAO{
    ArrayList<Student> data;
    Context context;
    StudentDBHelper helper;
    SQLiteDatabase db;

    public StudentDAODBImpl (Context context){
        this.context=context;
        helper=new StudentDBHelper(context);
        db=helper.getWritableDatabase();
    }


    @Override
    public void add(Student s) {
        ContentValues cv=new ContentValues();
        cv.put("name",s.name);
        cv.put("tel",s.tel);
        cv.put("addr",s.addr);
        db.insert("students",null,cv);

    }

    @Override
    public Student[] getData() {
        ArrayList<Student> tmpList=new ArrayList<>();
        Cursor c=db.query("students",new String[] {"_id", "name", "tel", "addr"}, null, null, null, null ,null);
        if (c.moveToFirst()){
            tmpList.add(new Student(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            while(c.moveToNext()){
                tmpList.add(new Student(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }
        }
        return tmpList.toArray(new Student[tmpList.size()]);
    }

    @Override
    public void update(Student s) {
        ContentValues cv=new ContentValues();
        cv.put("name",s.name);
        cv.put("tel",s.tel);
        cv.put("addr",s.addr);
        db.update("students", cv, "_id=?", new String[] {String.valueOf(s.id)});

//        boolean b=db.update("students",cv,"_id=" + s.id,null)>0;
    }

    @Override
    public void delete(Student s) {
        db.delete("students", "_id=?", new String[] {String.valueOf(s.id)});

//        boolean b=db.delete("students","_id=" + s.id,null)>0;
    }

    @Override
    public void clear() {
        db.delete("students", null, null);//砍掉全部資料
    }

    @Override
    public Student getOneStudent(int id) {
        Cursor c = db.query("students", new String[] {"_id", "name", "tel", "addr"}, "_id=?", new String[] {String.valueOf(id)}, null, null ,null);

        if (c.moveToFirst())
        {
            Student s=new Student(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
        return s;
        }

        return null;

//        for (Student tmp : data)
//        {
//            if (tmp.id == id)
//            {
//                return tmp;
//            }
//        }
//        return null;
    }

    @Override
    public Student[] searchByName(String name) {
        ArrayList<Student> tmpList=new ArrayList<>();
        Cursor c = db.query("students", new String[] {"_id", "name", "tel", "addr"}, "name=?", new String[] {String.valueOf(name)}, null, null ,null);

        if (c.moveToFirst()){
            tmpList.add(new Student(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            while(c.moveToNext()){
                tmpList.add(new Student(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }
        }
        return tmpList.toArray(new Student[tmpList.size()]);


//        ArrayList<Student> tmpList = new ArrayList<>();
//        for (Student tmp : data)
//        {
//            if (tmp.name.equals(name))
//            {
//                tmpList.add(tmp);
//            }
//        }


    }
}
