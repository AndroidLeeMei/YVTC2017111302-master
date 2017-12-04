package com.example.yvtc.yvtc2017111302;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.yvtc.yvtc2017111302.data.Student;
import com.example.yvtc.yvtc2017111302.data.StudentDAODBImpl;
import com.example.yvtc.yvtc2017111302.data.StudentDAOFileImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by auser on 2017/11/20.
 */
@RunWith(AndroidJUnit4.class)
public class MyDAODBTest {
    @Test
    public void TestUpdate1() throws  Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAODBImpl dao = new StudentDAODBImpl(appContext);
        dao.clear();
        dao.add(new Student("AA", "11", "aabb"));
        dao.add(new Student("BB", "22", "aabb"));
        dao.add(new Student("CC", "33", "aabb"));

        Student[] stus=dao.getData();
        Student s=stus[1];
        s.name="dd";
        dao.update(s);
        assertEquals(stus[1].name,"dd");
    }


    @Test
    public void TestSearchByName1() throws  Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAODBImpl dao = new StudentDAODBImpl(appContext);
        dao.clear();
        dao.add(new Student("AA", "11", "aabb"));
        dao.add(new Student("BB", "22", "aabb"));
        dao.add(new Student("CC", "33", "aabb"));
        dao.add(new Student("BB", "22", "aabb"));

//        Student[] stus=dao.getData();
        Student[] s=dao.searchByName("BB");

        assertEquals(s.length,2);
    }


    @Test
    public void TestgetOneStudent1() throws  Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAODBImpl dao = new StudentDAODBImpl(appContext);
        dao.clear();
        dao.add(new Student("AA", "11", "aabb"));
        dao.add(new Student("BB", "22", "aabb"));
        dao.add(new Student("CC", "33", "aabb"));
        dao.add(new Student("BB", "44", "aabb"));
        Student[] s=dao.getData();
        assertEquals(s.length,4);
//        Student[] stus=dao.getData();
//        Student[] s=dao.searchByName("BB");…
        Student s1=dao.getOneStudent(s[3].id);

        assertEquals(s1.name,"BB");
        assertEquals(s1.tel,"44");
    }

}
