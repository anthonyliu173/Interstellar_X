package com.anthony.interstellar_x;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by anthonyliu on 2016/4/3.
 *
 * All position and velocity measurements are in pixel, seconds and are all integers
 *
 */
public class App extends Application {

    public static final boolean BLACKHOLE_MOVABLE = false;

    @Override
    public void onCreate() {
        super.onCreate();

        setScreenDimension();

    }

    private void setScreenDimension(){

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        ScreenDimension.setScreenWidth(screenSize.x);
        ScreenDimension.setScreenHeight(screenSize.y);

    }

}
