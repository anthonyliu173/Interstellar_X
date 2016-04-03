package com.anthony.interstellar_x.Interstellar_Objects;

import android.graphics.Point;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.ScreenDimension;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Blackhole {

    private int mass;
    private Point position;
    private Point velocity;

    public Blackhole() {
        this.mass = Constants.BLACKHOLE_MASS;
        this.position = new Point(ScreenDimension.getScreenWidth()/2, ScreenDimension.getScreenHeight()/2);
        this.velocity = new Point(0, 0);
    }

    public Blackhole(int mass) {
        this.mass = mass;
        this.position = new Point(ScreenDimension.getScreenWidth()/2, ScreenDimension.getScreenHeight()/2);
        this.velocity = new Point(0, 0);
    }

    public Blackhole(int mass, int position_x, int position_y, int velocity_x, int velocity_y) {
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
