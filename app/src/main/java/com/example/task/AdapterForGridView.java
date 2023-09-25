package com.example.task;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterForGridView extends BaseAdapter {
    List<String> itemsText;
    List<String> itemsCombination;


    static Activity mActivity;

    private static LayoutInflater inflater = null;

    public AdapterForGridView(Activity activity, List<String> itemsText,List<String> itemsCombination) {
        mActivity = activity;
        this.itemsText = itemsText;
        this.itemsCombination = itemsCombination;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public final int getCount() {

        return itemsCombination.size();

    }

    @Override
    public final Object getItem(int position) {
        return itemsCombination.get(position);
    }

    @Override
    public final long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = null;

        v = inflater.inflate(R.layout.grid_item, null);
        TextView textureView = v.findViewById(R.id.textView);
        textureView.setText(itemsText.get(position));
        GridView gridView = v.findViewById(R.id.grid);
        List<String> combinationButton = new ArrayList<>();
        String combination = itemsCombination.get(position);
        for (int i = 0; i < combination.length(); i++){
            combinationButton.add(String.valueOf(combination.charAt(i)));
        }
        gridView.setNumColumns(4);
        GridAdapter adapter = new GridAdapter(mActivity,combinationButton);
        gridView.setAdapter(adapter);

        return v;
    }
}
