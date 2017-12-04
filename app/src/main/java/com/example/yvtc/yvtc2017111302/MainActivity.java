package com.example.yvtc.yvtc2017111302;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.yvtc.yvtc2017111302.data.DAOType;
import com.example.yvtc.yvtc2017111302.data.OnCloudReceivedListener;
import com.example.yvtc.yvtc2017111302.data.Student;
import com.example.yvtc.yvtc2017111302.data.StudentDAO;
import com.example.yvtc.yvtc2017111302.data.StudentDAOFactory;
import com.example.yvtc.yvtc2017111302.data.StudentDAOFileImpl;
import com.example.yvtc.yvtc2017111302.data.StudentDAOMemoryImpl;
import com.example.yvtc.yvtc2017111302.data.StudentDAOTest1;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener, OnCloudReceivedListener {
    public static StudentDAO t;//有實作StudentDAOh  ,用物件內容工廠來轉變類別,
//    final DAOType type=DAOType.DB;   //是一種Enum類別,放在Data裏
    final DAOType type = DAOType.CLOUD;

//    final  DAOType type=DAOType.FILE;   //是一種Enum類別,放在Data裏
//    public static StudentDAOFileImpl t;
    public static RecyclerView mRecyclerView;
    public static RecyclerView.Adapter<MyAdapter.ViewHolder> mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    GestureDetector mGD;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = StudentDAOFactory.getStudentDAO(type,this);
        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // t.add(new Student("Bob", "123", "123"));
        // t.add(new Student("Mary", "123", "123"));
        mGD = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener()
            {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            }
        );
        mRecyclerView.addOnItemTouchListener(this);


    }

    @Override
    protected void onResume() {
        Log.d("StudentDAOCloudImpl2", "onResume start: ");
        super.onResume();
        mAdapter = new MyAdapter(MainActivity.this, t.getData());
        mRecyclerView.setAdapter(mAdapter);
        Log.d("StudentDAOCloudImpl2", "Value is end: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("ADD");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(MainActivity.this, AddActivity.class);
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View v = rv.findChildViewUnder(e.getX(), e.getY());
        Log.d("Touch", "onInterceptTouchEvent");
        if (mGD.onTouchEvent(e))
        {
            Log.d("Touch", "Single Tap up");
            int position = rv.getChildLayoutPosition(v);
            // Toast.makeText(MainActivity.this, "posi:" + position, Toast.LENGTH_SHORT).show();
            if (position >= 0)
            {

                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("id", t.getData()[position].id);
                startActivity(it);
            }
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onReceivedEvent() {
        mAdapter = new MyAdapter(MainActivity.this, t.getData());
        mRecyclerView.setAdapter(mAdapter);
    }
}
