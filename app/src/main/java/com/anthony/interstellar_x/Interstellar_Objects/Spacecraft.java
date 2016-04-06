package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.MainActivity;
import com.anthony.interstellar_x.OnBlackholeHorizonEvent;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Spacecraft extends PhysicalObject{

    private OnBlackholeHorizonEvent blackholeListener;

    /**
     * Default spacecraft object with position at origin, no velocity and default mass.
     *
     * */
    public Spacecraft(Context context) {
        setBlackholeListener((MainActivity)context);
        this.mass = Constants.SPACECRAFT_MASS;
        this.dimension = new Point(Constants.SPACECRAFT_WIDTH, Constants.SPACECRAFT_HEIGHT);
        this.position = new Point(Constants.SPACECRAFT_WIDTH/2, Constants.SPACECRAFT_HEIGHT/2);
        this.velocity_x = 0.0;
        this.velocity_y = 0.0;
        this.setImage(context);
    }

    public Spacecraft(Context context, int mass) {
        setBlackholeListener((MainActivity)context);
        this.mass = mass;
        this.dimension = new Point(Constants.SPACECRAFT_WIDTH, Constants.SPACECRAFT_HEIGHT);
        this.position = new Point(Constants.SPACECRAFT_WIDTH/2, Constants.SPACECRAFT_HEIGHT/2);
        this.velocity_x = 0.0;
        this.velocity_y = 0.0;
        this.setImage(context);
    }

    public Spacecraft(Context context, int mass, int position_x, int position_y, int velocity_x, int velocity_y) {
        setBlackholeListener((MainActivity)context);
        this.mass = mass;
        this.dimension = new Point(Constants.SPACECRAFT_WIDTH, Constants.SPACECRAFT_HEIGHT);
        this.position = new Point(position_x, position_y);
        this.velocity_x = 0.0;
        this.velocity_y = 0.0;
        this.setImage(context);
    }

    public void blackholeCollision(){
        blackholeListener.Goodbye(this);
    }

    public OnBlackholeHorizonEvent getBlackholeListener() {
        return blackholeListener;
    }

    public void setBlackholeListener(OnBlackholeHorizonEvent blackholeListener) {
        this.blackholeListener = blackholeListener;
    }
}
