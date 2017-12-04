package com.example.yvtc.yvtc2017111302;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yvtc.yvtc2017111302.data.Student;

public class DetailActivity extends AppCompatActivity {
    TextView tv2, tv3, tv4, tv5;
    Student stu;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);

        id = getIntent().getIntExtra("id", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        stu = MainActivity.t.getOneStudent(id);
        tv2.setText(String.valueOf(stu.id));
        tv3.setText(stu.name);
        tv4.setText(stu.tel);
        tv5.setText(stu.addr);
    }

    public void clickDelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delele_confirm);
        builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.t.delete(stu);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void clickEdit(View v)
    {
        Intent it = new Intent(DetailActivity.this, EditActivity.class);
        it.putExtra("id", id);
        startActivity(it);
    }

    public void clickBack(View v)
    {
        finish();
    }
}
