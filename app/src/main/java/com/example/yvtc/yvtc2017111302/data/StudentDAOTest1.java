package com.example.yvtc.yvtc2017111302.data;

import java.util.ArrayList;

/**
 * Created by yvtc on 2017/11/13.
 */

public class StudentDAOTest1 {
    ArrayList<Student> data = new ArrayList();
    int MaxID = 1;
    public void add(Student s)
    {
        s.id = MaxID;
        data.add(s);
        MaxID++;
    }

    public ArrayList<Student> getData()
    {
        return data;
    }
    public void update(Student s)
    {
        for (Student tmp : data)
        {
            if (tmp.id == s.id)
            {
                tmp.name = s.name;
                tmp.tel = s.tel;
                tmp.addr = s.addr;
            }
        }
    }
}
