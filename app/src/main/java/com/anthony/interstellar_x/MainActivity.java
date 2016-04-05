package com.anthony.interstellar_x;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

//    private final long UPDATE_INTERVAL = 10; // Update sensor value every 100 ms

    private RelativeLayout rlBackground;
    private Spacecraft spacecraft;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spacecraft = new Spacecraft(MainActivity.this);

        rlBackground = (RelativeLayout)findViewById(R.id.rlBackground);
        rlBackground.addView(spacecraft.getImageView());

//        spacecraft.setVelocity(new Point(20, 15));

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
        spacecraft.getImageView().setX(spacecraft.getPosition().x - spacecraft.getDimension().x/2);
        spacecraft.getImageView().setY(spacecraft.getPosition().y - spacecraft.getDimension().y/2);
    }

    private void setSensor(){
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            
            spacecraft.updateVelocity(new ArrayList<PhysicalObject>(), -x, y);
            spacecraft.updatePosition();

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
