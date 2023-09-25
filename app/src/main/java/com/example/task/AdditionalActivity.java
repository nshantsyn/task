package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class AdditionalActivity extends AppCompatActivity {

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);
        database = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        GridView gridView = findViewById(R.id.grid);
        gridView.setNumColumns(1);

        Cursor query = database.rawQuery("SELECT * FROM combinations;", null);

        List<String> itemsId =  new ArrayList<>();
        List<String> itemsCombination =  new ArrayList<>();
        while(query.moveToNext()){
            int id = query.getInt(0);
            String combination = query.getString(1);
            itemsId.add(String.valueOf(id));
            itemsCombination.add(combination);
        }
        AdapterForGridView adapter = new AdapterForGridView(AdditionalActivity.this,itemsId,itemsCombination);

        gridView.setAdapter(adapter);
        query.close();
    }
}