package com.anthony.interstellar_x;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

/**
 * Created by anthonyliu on 2016/4/7.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 9 levels in total, Level_P stands for practice
     * */
    private String[] Levels = {"Level_1", "Level_2", "Level_3", "Level_4", "Level_5", "Level_6", "Level_7", "Level_8", "Level_P"};

    private GridView gridView;
    private LevelGrid gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){

        getSupportActionBar().hide();
        gridView = (GridView)findViewById(R.id.gridView);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
