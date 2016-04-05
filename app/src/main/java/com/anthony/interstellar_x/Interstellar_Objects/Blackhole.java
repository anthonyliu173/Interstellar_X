package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.ScreenDimension;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Blackhole extends PhysicalObject{

    /**
     * Default blackhole whose position is fixed at the center of screen
     * */
    public Blackhole(Context context) {
        this.mass = Constants.BLACKHOLE_MASS;
        this.dimension = new Point(Constants.BLACKHOLE_WIDTH, Constants.BLACKHOLE_HEIGHT);
        this.position = new Point(ScreenDimension.getScreenWidth()/2, ScreenDimension.getScreenHeight()/2);
        this.velocity_x = 0.0;
        this.velocity_y = 0.0;
        this.setImage(context);
    }

    public Blackhole(Context context, int mass) {
        this.mass = mass;
        this.dimension = new Point(Constants.BLACKHOLE_WIDTH, Constants.BLACKHOLE_HEIGHT);
        this.position = new Point(ScreenDimension.getScreenWidth()/2, ScreenDimension.getScreenHeight()/2);
        this.velocity_x = 0.0;
        this.velocity_y = 0.0;
        this.setImage(context);
    }

    public Blackhole(Context context, int mass, int position_x, int position_y, double velocity_x, double velocity_y) {
        this.mass = mass;
        this.dimension = new Point(Constants.BLACKHOLE_WIDTH, Constants.BLACKHOLE_HEIGHT);
        this.position = new Point(position_x, position_y);
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        this.setImage(context);
    }

}
