package com.anthony.interstellar_x;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anthony.interstellar_x.Interstellar_Objects.Blackhole;
import com.anthony.interstellar_x.Interstellar_Objects.CheckPoint;
import com.anthony.interstellar_x.Interstellar_Objects.Meteorite;
import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyliu on 2016/4/6.
 */
public class GamingActivity extends AppCompatActivity implements SensorEventListener, OnBlackholeHorizonEventListener, OnMeteoriteByPassListener, CheckPointReachedListener {

    public FrameLayout rlBackground;
    public List<PhysicalObject> physicalObjects = new ArrayList<>();
    public List<CheckPoint> checkPoints = new ArrayList<>();
    public List<PhysicalObject> gravityList;

    public SensorManager senSensorManager;
    public Sensor senAccelerometer;

    private RelativeLayout rlLoading;

    private RelativeLayout rlPreamble;
    private FrameLayout flPreamble;

    private TextView txtSpeed;
    private TextView txtMaxSpeed;

    /**
     * PhysicalObjects that will be removed after each sensor update
     */
    private List<PhysicalObject> removingObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);

        initView();
        showLoading();
        extentScreen();
        addPhysicalObjectsToView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        extentScreen();
        if (senSensorManager != null && senAccelerometer != null) {
            senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    private void extentScreen() {
        getSupportActionBar().hide();
        rlBackground.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void initView() {
        rlBackground = (FrameLayout) findViewById(R.id.rlBackground);
        rlLoading = (RelativeLayout) findViewById(R.id.rlLoading);
        rlPreamble = (RelativeLayout) findViewById(R.id.rlPreamble);
        flPreamble = (FrameLayout) findViewById(R.id.flPreamble);
        txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        txtMaxSpeed = (TextView) findViewById(R.id.txtMaxSpeed);
    }

    protected void declareCheckPoints() {

    }

    protected void declarePhysicalObjects() {

    }

    private void addPhysicalObjectsToView() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                declarePhysicalObjects();
                declareCheckPoints();
                for (PhysicalObject physicalObject : physicalObjects) {
                    rlBackground.addView(physicalObject.getImageView());
                }
                if(checkPoints.size() > 0){
                    rlBackground.addView(checkPoints.get(0).getImageView());
                    checkPoints.get(0).getImageView().setVisibility(View.VISIBLE);
                }
                updateInitialPosition();
                extractBlackholes();
                hideLoading();
                showPreamble();
            }
        }, Constants.LOADING_TIME);

    }

    private void updateInitialPosition() {
        for (PhysicalObject physicalObject : physicalObjects) {
            physicalObject.getImageView().setX(physicalObject.getPosition().x - physicalObject.getDimension().x / 2);
            physicalObject.getImageView().setY(physicalObject.getPosition().y - physicalObject.getDimension().y / 2);
        }
        for(CheckPoint checkPoint : checkPoints){
            checkPoint.getImageView().setX(checkPoint.getPosition().x - checkPoint.getDimension().x / 2);
            checkPoint.getImageView().setY(checkPoint.getPosition().y - checkPoint.getDimension().y / 2);
        }
    }

    private void setSensor() {
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    private void showLoading() {
        rlLoading.setVisibility(View.VISIBLE);
        ImageView imgSpacecraft = ((ImageView) findViewById(R.id.imgSpacecraft));
        imgSpacecraft.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_fast));
    }

    private void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

    private void showPreamble() {

        rlPreamble.setVisibility(View.VISIBLE);
        flPreamble.setVisibility(View.VISIBLE);
        flPreamble.addView(PreambleHelper.findPreamble(this));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rlPreamble.setVisibility(View.GONE);
                flPreamble.setVisibility(View.GONE);
                setSensor();
            }
        }, Constants.PREAMBLE_TIME);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(senSensorManager != null) {
            senSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];

            removingObject = new ArrayList<>();

            Collision.collideAnalysis(physicalObjects);

            for (PhysicalObject physicalObject : physicalObjects) {
                if (physicalObject instanceof Spacecraft) {
                    physicalObject.updateVelocity(gravityList, -x, y);
                    txtSpeed.setText(String.format(getResources().getString(R.string.speed), String.valueOf(physicalObject.getSpeed())));
                    txtMaxSpeed.setText(String.format(getResources().getString(R.string.max_speed), String.valueOf(physicalObject.getMaxSpeed())));
                    if(checkPoints.size() > 0){
                        Collision.checkPointAnalysis((Spacecraft)physicalObject, checkPoints.get(0));
                    }
                }
                if (physicalObject instanceof Meteorite) {
                    physicalObject.updateVelocity(gravityList, 0, 0);
                }
            }

            for (PhysicalObject physicalObject : physicalObjects) {
                if (physicalObject instanceof Spacecraft || physicalObject instanceof Meteorite) {
                    physicalObject.updatePosition();
                }
            }

            for (PhysicalObject physicalObject : physicalObjects) {
                if (physicalObject instanceof Meteorite) {
                    physicalObject.isInBound();
                }
            }

            for (PhysicalObject physicalObject : removingObject) {
                if (physicalObject instanceof Spacecraft) {
                    gameOver();
                    break;
                }
                if (physicalObject instanceof Meteorite) {
                    physicalObjects.remove(physicalObject);
                    rlBackground.removeView(physicalObject.getImageView());
                }
            }

            update();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * update() is for Level activities purposes
     * */
    protected void update(){

    }

    private void extractBlackholes() {
        List<PhysicalObject> blackholes = new ArrayList<>();
        for (PhysicalObject physicalObject : physicalObjects) {
            if (physicalObject instanceof Blackhole) {
                blackholes.add(physicalObject);
            }
        }
        gravityList = blackholes;
    }

    @Override
    public void GoodBye(PhysicalObject object) {
        removingObject.add(object);
    }

    @Override
    public void LongGone(Meteorite meteorite) {
        removingObject.add(meteorite);
    }

    private void gameOver() {
        senSensorManager.unregisterListener(this);
        Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, Constants.GAMEOVER_TIME);
    }

    @Override
    public void CheckPointReached(CheckPoint checkPoint) {

    }
}
