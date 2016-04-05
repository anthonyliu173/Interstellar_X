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
    private static int NavigationBarHeight = 0;
    private static int StatusBarHeight = 0;

    public static int getScreenWidth() {
        return ScreenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        ScreenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return ScreenHeight - NavigationBarHeight - StatusBarHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        ScreenHeight = screenHeight;
    }

    public static int getNavigationBarHeight() {
        return NavigationBarHeight;
    }

    public static void setNavigationBarHeight(int navigationBarHeight) {
        NavigationBarHeight = navigationBarHeight;
    }

    public static int getStatusBarHeight() {
        return StatusBarHeight;
    }

    public static void setStatusBarHeight(int statusBarHeight) {
        StatusBarHeight = statusBarHeight;
    }
}
