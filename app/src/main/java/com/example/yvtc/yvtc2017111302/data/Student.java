package com.example.yvtc.yvtc2017111302.data;

/**
 * Created by yvtc on 2017/11/13.
 */

public class Student {
    public int id;
    public String name;
    public String tel;
    public String addr;

    public Student(String name, String tel, String addr)
    {
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }
    public Student(int id,String name, String tel, String addr)
    {
        this.id=id;
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }
    @Override
    public String toString()
    {
        return id + "," + name + "," + tel + "," + addr;
    }
}
