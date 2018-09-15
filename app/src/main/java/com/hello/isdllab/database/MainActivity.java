package com.hello.isdllab.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText stuId, stuName, surName, marks;
    private Button add,view, update, delete;
    private  DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        stuId = findViewById ( R.id.etId );
        stuName = findViewById ( R.id.etFName );
        surName = findViewById ( R.id.etLName );
        marks = findViewById ( R.id.etMarks );
        add = findViewById ( R.id.btnSave );
        view = findViewById ( R.id.btnView );
        update = findViewById ( R.id.btnUpdate);
        delete = findViewById ( R.id.btnDelete );

        databaseHelper=new DatabaseHelper(this);
        
        
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=databaseHelper.insertDATA(stuName.getText().toString(),surName.getText().toString(),
                        Integer.valueOf(marks.getText().toString()));
                if (isInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error ,Could not Insert Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=databaseHelper.getALLDATA();
                if (res.getCount()==0){
                    showMsg("Empty","No data found");
                }
                else {
                    StringBuffer buffer=new StringBuffer();

                    while (res.moveToNext()){
                        buffer.append("id :"+res.getString(0)+"\n");
                        buffer.append("Name :"+res.getString(1)+"\n");
                        buffer.append("SurName :"+res.getString(2)+"\n");
                        buffer.append("Marks :"+res.getString(3)+"\n\n");
                    }
                    showMsg("Data",buffer.toString());
                }

            }
        });
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=databaseHelper.updateData(Integer.valueOf(stuId.getText().toString()),stuName.getText().toString(),surName.getText().toString(),
                        Integer.valueOf(marks.getText().toString()));
                if (isUpdated){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error ,Could not Insert Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stuId.getText().toString().equals(null)){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else {
                    Integer deleteRows=databaseHelper.deleteData(Integer.valueOf(stuId.getText().toString()));
                    
                    if (deleteRows>0){
                        Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Data is not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    private  void  showMsg(String title,String msg){

        AlertDialog.Builder myDialog =new AlertDialog.Builder(this);
        myDialog.setCancelable(true);
        myDialog.setTitle(title);
        myDialog.setMessage(msg);
        myDialog.show();
    }
}
