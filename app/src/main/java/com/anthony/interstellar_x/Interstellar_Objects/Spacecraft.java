package com.anthony.interstellar_x.Interstellar_Objects;

import android.graphics.Point;

import com.anthony.interstellar_x.Constants;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Spacecraft {

    private int mass;
    private Point position;
    private Point velocity;

    /**
     * Default spacecraft object with position at origin, no velocity and default mass.
     *
     * */
    public Spacecraft() {
        this.mass = Constants.SPACECRAFT_MASS;
        this.position = new Point(Constants.SPACECRAFT_WIDTH/2, Constants.SPACECRAFT_HEIGHT/2);
        this.velocity = new Point(0, 0);
    }

    public Spacecraft(int mass) {
        this.mass = mass;
        this.position = new Point(Constants.SPACECRAFT_WIDTH/2, Constants.SPACECRAFT_HEIGHT/2);
        this.velocity = new Point(0, 0);
    }

    public Spacecraft(int mass, int position_x, int position_y, int velocity_x, int velocity_y) {
        this.mass = mass;
        this.position = new Point(position_x, position_y);
        this.velocity = new Point(velocity_x, velocity_y);
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

}
