package com.anthony.interstellar_x;

import android.graphics.Point;

import java.util.Random;

/**
 * Created by anthonyliu on 2016/4/3.
 *
 * All position and velocity measurements are in pixel, seconds and are all integers
 *
 */
public class Constants {

    public static final int GRAVITY_CONSTANT = 10;
    public static final int TIME_CONSTANT = 1;
    public static final double BOUNCE_COEFFICIENT = -0.7; // Velocity is reduced by 30% when bouncing.

    public static final int BLACKHOLE_MASS = 3000;
    public static final int BLACKHOLE_WIDTH = 25;
    public static final int BLACKHOLE_HEIGHT = 25;

    public static final int SPACECRAFT_WIDTH = 100;
    public static final int SPACECRAFT_HEIGHT = 100;
    public static final int SPACECRAFT_MASS = 10;

    public static final int METEORITE_MASS_SHORT = 2;
    public static final int METEORITE_MASS_SHORT_DIMENSION = 6;
    public static final int METEORITE_MASS_TALL = 6;
    public static final int METEORITE_MASS_TALL_DIMENSION = 14;
    public static final int METEORITE_MASS_GRANDE = 13;
    public static final int METEORITE_MASS_GRANDE_DIMENSION = 22;
    public static final int METEORITE_MASS_VENTI = 20;
    public static final int METEORITE_MASS_VENTI_DIMENSION = 28;

    public static final int RANDOM_VELOCITY_MAX = 30;

    public static Point getRandomPositionOnScreen(int objectWidth, int objectHeight){
        Point position = new Point();
        int x = getRandom(objectWidth, ScreenDimension.getScreenWidth() - objectWidth);
        int y = getRandom(objectHeight, ScreenDimension.getScreenHeight() - objectHeight);
        position.set(x, y);
        return position;
    }

    public static Point getRandomVelocity(){
        Point position = new Point();
        int x = getRandom(-RANDOM_VELOCITY_MAX, RANDOM_VELOCITY_MAX);
        int y = getRandom(-RANDOM_VELOCITY_MAX, RANDOM_VELOCITY_MAX);
        position.set(x, y);
        return position;
    }

    /**
     * getRandom returns a random number between lowerBound(exclusive) and upperBound(exclusive)
     *
     * @param lowerBound
     * @param upperBound
     * */
    private static int getRandom(int lowerBound, int upperBound){
        lowerBound = lowerBound + 1; // makes lowerBound exclusive
        return new Random().nextInt(upperBound - lowerBound) + lowerBound;
    }

}
