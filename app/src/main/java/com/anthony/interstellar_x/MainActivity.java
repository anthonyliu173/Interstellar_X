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
    private List<PhysicalObject> physicalObjects = new ArrayList<>();
    private List<PhysicalObject> gravityList;
    private List<PhysicalObject> collisionList;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHideHandler.post(mHideRunnable);
        mHideHandler.post(mHidePart2Runnable);

        physicalObjects.add(new Spacecraft(MainActivity.this));
        physicalObjects.add(new Meteorite(MainActivity.this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Blackhole(MainActivity.this, Constants.BLACKHOLE_MASS, ScreenDimension.getScreenWidth() * 3 / 4,
                ScreenDimension.getScreenHeight() * 3 / 4, 0.0, 0.0));
        physicalObjects.add(new Blackhole(MainActivity.this, Constants.BLACKHOLE_MASS, ScreenDimension.getScreenWidth() / 4,
                ScreenDimension.getScreenHeight() / 4, 0.0, 0.0));
        
        gravityList = extractBlackholes();
        collisionList = extraCollisionList();

        rlBackground = (FrameLayout)findViewById(R.id.rlBackground);

        addPhysicalObjectsToView();
        updateInitialPosition();

        setSensor();

    }

    @Override
    protected void onResume() {
        super.onResume();

        mHideHandler.post(mHideRunnable);
        mHideHandler.post(mHidePart2Runnable);

        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();

        senSensorManager.unregisterListener(this);

    }

    private void addPhysicalObjectsToView(){
        for(PhysicalObject physicalObject : physicalObjects){
            rlBackground.addView(physicalObject.getImageView());
        }
    }

    private void updateInitialPosition(){
        for(PhysicalObject physicalObject : physicalObjects){
            physicalObject.getImageView().setX(physicalObject.getPosition().x - physicalObject.getDimension().x / 2);
            physicalObject.getImageView().setY(physicalObject.getPosition().y - physicalObject.getDimension().y / 2);
        }
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

            for(PhysicalObject physicalObject : physicalObjects){
                if(physicalObject instanceof Spacecraft){
                    physicalObject.updateVelocity(gravityList, -x, y);
                }
                if(physicalObject instanceof Meteorite){
                    physicalObject.updateVelocity(gravityList, 0, 0);
                }
            }

            for(PhysicalObject physicalObject : physicalObjects){
                if(physicalObject instanceof Spacecraft || physicalObject instanceof Meteorite){
                    physicalObject.updatePosition();
                }
            }

            Collision.collideAnalysis(collisionList);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private List<PhysicalObject> extractBlackholes(){
        List<PhysicalObject> blackholes = new ArrayList<>();
        for(PhysicalObject physicalObject : physicalObjects){
            if (physicalObject instanceof Blackhole){
                blackholes.add(physicalObject);
            }
        }
        return blackholes;
    }

    private List<PhysicalObject> extraCollisionList(){
        List<PhysicalObject> collisions = new ArrayList<>();
        for(PhysicalObject physicalObject : physicalObjects){
            if (!(physicalObject instanceof Blackhole)){
                collisions.add(physicalObject);
            }
        }
        return collisions;
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
