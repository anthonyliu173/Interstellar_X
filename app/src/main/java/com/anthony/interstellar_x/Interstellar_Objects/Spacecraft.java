package com.anthony.interstellar_x.Interstellar_Objects;

import android.graphics.Point;

import com.anthony.interstellar_x.Constants;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Spacecraft extends PhysicalObject{

    /**
     * Default spacecraft object with position at origin, no velocity and default mass.
     *
     * */
    public Spacecraft() {
        this.mass = Constants.SPACECRAFT_MASS;
        this.dimension = new Point(Constants.SPACECRAFT_WIDTH, Constants.SPACECRAFT_HEIGHT);
        this.position = new Point(Constants.SPACECRAFT_WIDTH/2, Constants.SPACECRAFT_HEIGHT/2);
        this.velocity = new Point(0, 0);
    }

    public Spacecraft(int mass) {
        this.mass = mass;
        this.dimension = new Point(Constants.SPACECRAFT_WIDTH, Constants.SPACECRAFT_HEIGHT);
        this.position = new Point(Constants.SPACECRAFT_WIDTH/2, Constants.SPACECRAFT_HEIGHT/2);
        this.velocity = new Point(0, 0);
    }

    public Spacecraft(int mass, int position_x, int position_y, int velocity_x, int velocity_y) {
        this.mass = mass;
        this.dimension = new Point(Constants.SPACECRAFT_WIDTH, Constants.SPACECRAFT_HEIGHT);
        this.position = new Point(position_x, position_y);
        this.velocity = new Point(velocity_x, velocity_y);
    }

}
