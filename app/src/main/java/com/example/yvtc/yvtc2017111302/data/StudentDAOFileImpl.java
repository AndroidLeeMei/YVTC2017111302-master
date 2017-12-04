package com.example.yvtc.yvtc2017111302.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yvtc on 2017/11/15.
 */

public class StudentDAOFileImpl implements StudentDAO {
    ArrayList<Student> data;
    Context context;
    String DATA_FILE;
    public StudentDAOFileImpl(Context context)
    {
        this.context = context;
        DATA_FILE = context.getFilesDir().getAbsolutePath() + File.separator + "mydata.json";
        data = new ArrayList<>();
        loadData();
    }

    private void saveData()
    {
        try {
            FileWriter fw = new FileWriter(DATA_FILE);
            Gson gson = new Gson();
            String str = gson.toJson(data);
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int MaxID ;
    private void loadData()
    {
        try {
            FileReader fr = new FileReader(DATA_FILE);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            br.close();
            fr.close();
            System.out.println("string:" + str);
            Log.d("str=====",str);
            if (str.trim().length() > 0)
            {
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Student>>() {}.getType();
                data = gson.fromJson(str, listType);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.size() > 0)
        {
            MaxID = data.get(0).id;
        }
        for (Student s : data)
        {
            if (MaxID < s.id) //先取第一筆的id當作maxid,再逐一比對每一比資料的maxid,慢慢替換成最大的
            {
                MaxID = s.id;
            }
        }
        MaxID += 1;


    }

    @Override
    public void add(Student s) {
        s.id=MaxID;
        data.add(s);
        MaxID++;
        saveData();
    }

    @Override
    public Student[] getData() {

        return data.toArray(new Student[data.size()]);
    }

    @Override
    public void update(Student s) {
        for (Student tmp : data)
        {
            if (tmp.id == s.id)
            {
                tmp.name = s.name;
                tmp.tel = s.tel;
                tmp.addr = s.addr;
            }
        }
        saveData();
    }

    @Override
    public void delete(Student s) {
        for (int i=data.size()-1;i>=0;i--)
        {
            if (data.get(i).id == s.id)
            {
                data.remove(i);
                break;
            }
        }
        saveData();
    }

    @Override
    public void clear() {
        data.clear();
        saveData();
    }

    @Override
    public Student getOneStudent(int id) {
        for (Student tmp : data)
        {
            if (tmp.id == id)
            {
                return tmp;
            }
        }
        return null;
    }

    @Override
    public Student[] searchByName(String name) {
        ArrayList<Student> tmpList = new ArrayList<>();
        for (Student tmp : data)
        {
            if (tmp.name.equals(name))
            {
                tmpList.add(tmp);
            }
        }
        return tmpList.toArray(new Student[tmpList.size()]);
    }
}
