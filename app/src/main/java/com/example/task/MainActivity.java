package com.example.task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> buttons =  new ArrayList<>();
    GridView gridView;
    GridAdapter adapter;
    SQLiteDatabase db;
    int id;
    public void showDialog(String text,String title){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.show();
    }
    protected void createDB(){
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE combinations ");
        db.execSQL("CREATE TABLE IF NOT EXISTS combinations (id integer primary key autoincrement,combination text)");

    }
    protected void insertCombination(String value){
        if (!isCombinationExist(value))
        {
            System.out.println("INSERT INTO combinations(combination) VALUES(" + value + " )");
            db.execSQL("INSERT INTO combinations(combination) VALUES('" + value + "')");
            showDialog("Insert complete","Insert");
        }
        else {
            showDialog("Already exist number:"+id,"Yes");
        }
    }
    protected boolean isCombinationExist(String value){
        Cursor query = db.rawQuery("SELECT * FROM combinations;", null);

        System.out.println(value);
        while(query.moveToNext()){
            int id = query.getInt(0);
            String combination = query.getString(1);

            System.out.println(id+" "+ combination);
            if (combination.equals(value)) {
                this.id = id;
                return true;
            }
        }
        query.close();

        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(4);

        for (int i = 0; i < 12; i++) {
            buttons.add("0");
        }
        adapter = new GridAdapter(this, buttons);
        gridView.setAdapter(adapter);

        Button button = findViewById(R.id.buttonMirror);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMirror();
            }
        });
        Button button1 = findViewById(R.id.buttonSave);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCombination(getCombination());
            }
        });
        Button button2 = findViewById(R.id.buttonCheck);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCombinationExist(getCombination())){
                    showDialog("Already exist number:"+id,"Yes");
                }
                else {
                    showDialog("Not exist","No");
                }
            }
        });
        //////////
        Button button3 = findViewById(R.id.buttonAll);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AdditionalActivity.class);
                startActivity(intent);
            }
        });
        createDB();
    }
    protected String getCombination(){
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < gridView.getCount(); i++) {
            Button btn = (Button) gridView.getChildAt(i);
            stringBuilder.append(btn.getText().toString());
        }
        return stringBuilder.toString();
    }
    protected void createMirror(){
        buttons.clear();

        List<String> listTm = new ArrayList<>();
        for (int i = 0; i < gridView.getCount(); i++) {
            Button btn = (Button) gridView.getChildAt(i);
           listTm.add(btn.getText().toString());
            if(listTm.size() == 4){
                Collections.reverse(listTm);
                buttons.addAll(listTm);
                listTm.clear();

            }

        }

        for (int i = 0; i < gridView.getCount(); i++) {
           Button btn = (Button) gridView.getChildAt(i);
            if(btn.getText().toString() != buttons.get(i)){
                btn.callOnClick();
            }
        }

    }
}