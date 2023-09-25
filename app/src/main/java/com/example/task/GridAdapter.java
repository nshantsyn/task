package com.example.task;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    List<String> items;

    static Activity mActivity;

    private static LayoutInflater inflater = null;

    public GridAdapter(Activity activity, List<String> tempTitle) {
        mActivity = activity;
        items = tempTitle;

        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public final int getCount() {

        return items.size();

    }

    @Override
    public final Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public final long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = null;

        v = inflater.inflate(R.layout.item, null);

        Button button = (Button) v.findViewById(R.id.button);
        button.setBackgroundColor(Color.RED);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((Button)view).getText().equals("0")){
                    view.setBackgroundColor(Color.GREEN);
                    ((Button)view).setText("1");
                }
                else {
                    view.setBackgroundColor(Color.RED);
                    ((Button)view).setText("0");
                }
            }

        });
      //  tv.setText(items.get(position));

        return v;
    }
}
