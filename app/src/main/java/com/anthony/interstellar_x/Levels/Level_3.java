package com.anthony.interstellar_x.Levels;

import android.graphics.Point;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.Interstellar_Objects.Blackhole;
import com.anthony.interstellar_x.Interstellar_Objects.CheckPoint;
import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;
import com.anthony.interstellar_x.R;
import com.anthony.interstellar_x.ScreenDimension;

/**
 * Created by anthonyliu on 2016/4/7.
 *
 * Level 3, guide user to accelerate via gravity from blackhole
 * Completion: get speed > THRESHOLD and make it to the checkpoint
 */
public class Level_3 extends GamingActivity {

    private final double SPEED_THRESHOLD = 13.0;
    private CheckPoint checkPoint;

    private boolean isCheckPointAdded = false;

    @Override
    protected void declarePhysicalObjects() {
        super.declarePhysicalObjects();

        physicalObjects.add(new Spacecraft(this));
        physicalObjects.add(new Blackhole(this));

        for (PhysicalObject physicalObject : physicalObjects) {
            rlBackground.addView(physicalObject.getImageView());
        }

    }

    @Override
    protected void declareCheckPoints() {
        super.declareCheckPoints();

        checkPoint = new CheckPoint(this, new Point(ScreenDimension.getScreenWidth() - Constants.CHECKPOINT_DIMENSION/2 - Constants.GAP, Constants.CHECKPOINT_DIMENSION/2 + Constants.GAP));
        checkPoint.getImageView().setColor(ContextCompat.getColor(Level_3.this, R.color.checkpoint_color1));
        checkPoints.add(checkPoint);

    }

    @Override
    protected void update() {
        super.update();

        // Speed reached
        if(((Spacecraft)physicalObjects.get(0)).getMaxSpeed() > SPEED_THRESHOLD && !isCheckPointAdded){
            showNextCheckPoint();
            isCheckPointAdded = true;
        }

    }

    @Override
    public void CheckPointReached(CheckPoint checkPoint) {
        super.CheckPointReached(checkPoint);

        rlBackground.removeView(checkPoint.getImageView());
        checkPoints.remove(checkPoint);
        // Show next checkpoint
        if(checkPoints.size() > 0){
            showNextCheckPoint();
        }else{
            Toast.makeText(this, "You made it!", Toast.LENGTH_LONG).show();
            senSensorManager.unregisterListener(this);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, Constants.GAMEOVER_TIME);
        }

    }

}