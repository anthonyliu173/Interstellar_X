package com.anthony.interstellar_x;

import android.graphics.Point;

/**
 * Created by anthonyliu on 16/4/18.
 * line y = mx + b
 */
public class Line {

    public final double m, b;

    public Line(double m, double b) {
        this.m = m;
        this.b = b;
    }

    public Point intersect(Line line) {
        double x = (this.b - line.b) / (this.m - line.m);
        double y = this.m * x + this.b;
        return new Point((int) x, (int) y);
    }

    public int getY(int x) {
        return (int) (this.m * x + this.b);
    }

    public int getX(int y){
        return (int)((y - this.b)/this.m);
    }

    public double get(double x) {
        return this.m * x + this.b;
    }

}
