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
        this.position = new Point(ScreenDimension.getScreenWidth()/2, ScreenDimension.getScreenHeight()/2);
        this.velocity = new Point(0, 0);
        this.setImage(context);
    }

    public Blackhole(Context context, int mass) {
        this.mass = mass;
        this.position = new Point(ScreenDimension.getScreenWidth()/2, ScreenDimension.getScreenHeight()/2);
        this.velocity = new Point(0, 0);
        this.setImage(context);
    }

    public Blackhole(Context context, int mass, int position_x, int position_y, int velocity_x, int velocity_y) {
        this.mass = mass;
        this.position = new Point(position_x, position_y);
        this.velocity = new Point(velocity_x, velocity_y);
        this.setImage(context);
    }

}
