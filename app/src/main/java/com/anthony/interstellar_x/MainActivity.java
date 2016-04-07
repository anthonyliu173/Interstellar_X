package com.anthony.interstellar_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.anthony.interstellar_x.Levels.Level_1;
import com.anthony.interstellar_x.Levels.Level_2;
import com.anthony.interstellar_x.Levels.Level_3;
import com.anthony.interstellar_x.Levels.Level_4;
import com.anthony.interstellar_x.Levels.Level_5;
import com.anthony.interstellar_x.Levels.Level_6;
import com.anthony.interstellar_x.Levels.Level_7;
import com.anthony.interstellar_x.Levels.Level_8;
import com.anthony.interstellar_x.Levels.Practice;

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
        initVar();
        setView();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){

        getSupportActionBar().hide();
        gridView = (GridView)findViewById(R.id.gridView);

    }

    private void initVar(){

        gridAdapter = new LevelGrid(MainActivity.this, Levels);

    }

    private void setView(){

        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(MainActivity.this, Level_1.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Level_2.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, Level_3.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, Level_4.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, Level_5.class);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, Level_6.class);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, Level_7.class);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, Level_8.class);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, Practice.class);
                        break;
                    default:
                        intent = new Intent(MainActivity.this, Practice.class);
                        break;
                }
                startActivity(intent);
            }
        });

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
