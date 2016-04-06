package com.anthony.interstellar_x;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.anthony.interstellar_x.Interstellar_Objects.Blackhole;
import com.anthony.interstellar_x.Interstellar_Objects.Meteorite;
import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private FrameLayout rlBackground;
    private Spacecraft spacecraft;
    private Meteorite meteorite;
    private Blackhole blackhole;
    private Blackhole blackhole1;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHideHandler.post(mHideRunnable);
        mHideHandler.post(mHidePart2Runnable);

        spacecraft = new Spacecraft(MainActivity.this);
        meteorite = new Meteorite(MainActivity.this, Meteorite.METEORITE_SIZE.TALL);
        blackhole = new Blackhole(MainActivity.this, Constants.BLACKHOLE_MASS, ScreenDimension.getScreenWidth()*3/4,
                ScreenDimension.getScreenHeight()*3/4, 0.0, 0.0);
        blackhole1 = new Blackhole(MainActivity.this, Constants.BLACKHOLE_MASS, ScreenDimension.getScreenWidth()/4,
                ScreenDimension.getScreenHeight()/4, 0.0, 0.0);

        rlBackground = (FrameLayout)findViewById(R.id.rlBackground);
        rlBackground.addView(spacecraft.getImageView());
        rlBackground.addView(meteorite.getImageView());
        rlBackground.addView(blackhole.getImageView());
        rlBackground.addView(blackhole1.getImageView());

        updateInitialPosition();

        setSensor();

    }

    @Override
    protected void onResume() {
        super.onResume();

        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();

        senSensorManager.unregisterListener(this);

    }

    private void updateInitialPosition(){
        spacecraft.getImageView().setX(spacecraft.getPosition().x - spacecraft.getDimension().x / 2);
        spacecraft.getImageView().setY(spacecraft.getPosition().y - spacecraft.getDimension().y / 2);
        blackhole.getImageView().setX(blackhole.getPosition().x - blackhole.getDimension().x / 2);
        blackhole.getImageView().setY(blackhole.getPosition().y - blackhole.getDimension().y / 2);
        blackhole1.getImageView().setX(blackhole1.getPosition().x - blackhole1.getDimension().x / 2);
        blackhole1.getImageView().setY(blackhole1.getPosition().y - blackhole1.getDimension().y / 2);
        meteorite.getImageView().setX(meteorite.getPosition().x - meteorite.getDimension().x / 2);
        meteorite.getImageView().setY(meteorite.getPosition().y - meteorite.getDimension().y / 2);
    }

    private void setSensor(){
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];

            List<PhysicalObject> gravityList = new ArrayList<>();
            gravityList.add(blackhole);
            gravityList.add(blackhole1);

            List<PhysicalObject> collisionList = new ArrayList<>();
            collisionList.add(spacecraft);
            collisionList.add(meteorite);

            spacecraft.updateVelocity(gravityList, -x, y);
            meteorite.updateVelocity(gravityList, 0, 0);
            spacecraft.updatePosition();
            meteorite.updatePosition();

            Collision.collideAnalysis(collisionList);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private final Handler mHideHandler = new Handler();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            rlBackground.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

}
