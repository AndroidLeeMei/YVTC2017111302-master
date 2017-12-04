package com.example.yvtc.yvtc2017111302;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.yvtc.yvtc2017111302.data.Student;
import com.example.yvtc.yvtc2017111302.data.StudentDAOCloudImpl;
import com.example.yvtc.yvtc2017111302.data.StudentDAOFileImpl;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by auser on 2017/12/4.
 */
@RunWith(AndroidJUnit4.class)
public class MyDAOCloudTest {
    @Test
    public void TestDelete() throws  Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAOCloudImpl dao = new StudentDAOCloudImpl(appContext);
        dao.clear();
        dao.add(new Student("AA", "11", "aabb"));
        dao.add(new Student("BB", "22", "aabb"));
        dao.add(new Student("CC", "33", "aabb"));

        Student[] stus=dao.getData();
        Log.d("stus======",stus[0].id+"");
        Log.d("stus======",stus[1].id+"");
        Log.d("stus======",stus[2].id+"");
        System.out.println("stus[0].id=   " + stus[0].id);
//        assertEquals(stus[0].id,1);
        Student s=stus[2];
////        s.name="dd";
        dao.delete(s);
        assertEquals(stus.length,3);
        Student[] check=dao.getData();
        assertEquals(check.length,2);

    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAOCloudImpl dao = new StudentDAOCloudImpl(appContext);
        dao.clear();
        dao.add(new Student("AA", "11", "aabb"));
        dao.clear();
        dao.add(new Student("BB", "22", "aabb"));
        dao.add(new Student("CC", "33", "aabb"));
        Student[] stus = dao.getData();
        assertEquals(stus.length, 2);



//        dao.delete(new Student("BB", "22", "aabb"));
//        System.out.println("stus.length==" +stus.length);
//        Log.d("stus.length==" ,stus.length+"");
////        Log.d("stus.length==" ,stus[0].ge);
//        Log.d("stus.length==" ,stus.length+"");
//        assertEquals(stus.length, 2);

    }



    @Test
    public void TestUpdate1() throws  Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        StudentDAOCloudImpl dao = new StudentDAOCloudImpl(appContext);
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
        StudentDAOCloudImpl dao = new StudentDAOCloudImpl(appContext);
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
        StudentDAOCloudImpl dao = new StudentDAOCloudImpl(appContext);
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
