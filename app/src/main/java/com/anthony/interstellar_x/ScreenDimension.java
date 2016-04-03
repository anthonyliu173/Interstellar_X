package com.anthony.interstellar_x;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class ScreenDimension {

    /**
     * In portrait mode width = x and height = y
     * */
    private static int ScreenWidth;
    private static int ScreenHeight;

    public static int getScreenWidth() {
        return ScreenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        ScreenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return ScreenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        ScreenHeight = screenHeight;
    }
}
