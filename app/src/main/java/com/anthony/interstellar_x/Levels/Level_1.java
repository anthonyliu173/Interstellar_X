package com.anthony.interstellar_x.Levels;

import android.graphics.Point;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.Interstellar_Objects.CheckPoint;
import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;
import com.anthony.interstellar_x.R;
import com.anthony.interstellar_x.ScreenDimension;

/**
 * Level 1, guide user how to control Spacecraft (by tilting the device).
 * Completion: move Spacecraft to lower right corner, upper left, lower left, and upper right
 * */
public class Level_1 extends GamingActivity {

    private CheckPoint checkPoint1;
    private CheckPoint checkPoint2;
    private CheckPoint checkPoint3;
    private CheckPoint checkPoint4;

    @Override
    protected void declarePhysicalObjects() {
        super.declarePhysicalObjects();

        physicalObjects.add(new Spacecraft(this));

        for (PhysicalObject physicalObject : physicalObjects) {
            rlBackground.addView(physicalObject.getImageView());
        }

    }

    @Override
    protected void declareCheckPoints() {
        super.declareCheckPoints();

        checkPoint1 = new CheckPoint(this, new Point(ScreenDimension.getScreenWidth() - Constants.CHECKPOINT_DIMENSION/2 - Constants.GAP, ScreenDimension.getScreenHeight() - Constants.CHECKPOINT_DIMENSION/2 - Constants.GAP));
        checkPoint1.getImageView().setColor(ContextCompat.getColor(Level_1.this, R.color.checkpoint_color1));
        checkPoint2 = new CheckPoint(this, new Point(Constants.CHECKPOINT_DIMENSION/2  + Constants.GAP, Constants.CHECKPOINT_DIMENSION/2 + Constants.GAP));
        checkPoint2.getImageView().setColor(ContextCompat.getColor(Level_1.this, R.color.checkpoint_color2));
        checkPoint3 = new CheckPoint(this, new Point(Constants.CHECKPOINT_DIMENSION/2  + Constants.GAP, ScreenDimension.getScreenHeight() - Constants.CHECKPOINT_DIMENSION/2 - Constants.GAP));
        checkPoint3.getImageView().setColor(ContextCompat.getColor(Level_1.this, R.color.checkpoint_color1));
        checkPoint4 = new CheckPoint(this, new Point(ScreenDimension.getScreenWidth() - Constants.CHECKPOINT_DIMENSION/2 - Constants.GAP, Constants.CHECKPOINT_DIMENSION/2 + Constants.GAP));
        checkPoint4.getImageView().setColor(ContextCompat.getColor(Level_1.this, R.color.checkpoint_color2));

        checkPoints.add(checkPoint1);
        checkPoints.add(checkPoint2);
        checkPoints.add(checkPoint3);
        checkPoints.add(checkPoint4);

        if(checkPoints.size() > 0){
            showNextCheckPoint();
        }

    }

    @Override
    protected void update() {
        super.update();
    }

    @Override
    public void CheckPointReached(CheckPoint checkPoint) {
        super.CheckPointReached(checkPoint);

        rlBackground.removeView(checkPoint.getImageView());
        checkPoints.remove(checkPoint);

        if(checkPoints.size() > 0){
            showNextCheckPoint();
        }else{
            Toast.makeText(this, "You have made it !!", Toast.LENGTH_LONG).show();
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
