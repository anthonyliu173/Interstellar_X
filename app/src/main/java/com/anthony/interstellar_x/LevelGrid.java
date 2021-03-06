package com.anthony.interstellar_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by anthonyliu on 2016/4/7.
 */
public class LevelGrid extends BaseAdapter {

    private Context mContext;
    private final String[] levels;
    private String[] level_names;

    public LevelGrid(Context context,String[] levels) {
        mContext = context;
        this.levels = levels;
        level_names = context.getResources().getStringArray(R.array.LevelNames);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return levels.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_level, null);
            TextView textView = (TextView) grid.findViewById(R.id.txtLevel);
            textView.setText(level_names[position]);

        } else {
            grid = (View) convertView;
        }

        return grid;
    }

}
