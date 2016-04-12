package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.LinearLayout;

import com.anthony.interstellar_x.CheckPointReachedListener;
import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.SpaceDimension;
import com.anthony.interstellar_x.Views.CircularView;

/**
 * Created by anthonyliu on 2016/4/11.
 */
public class CheckPoint extends SpaceDimension{

    protected CheckPointReachedListener listener;
    protected CircularView imageView;

    public CheckPoint(Context context, Point position) {
        this.setListener((GamingActivity)context);
        this.position = position;
        this.dimension = new Point(Constants.CHECKPOINT_DIMENSION, Constants.CHECKPOINT_DIMENSION);
        this.setImage(context);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getDimension() {
        return dimension;
    }

    public void setDimension(Point dimension) {
        this.dimension = dimension;
    }

    public CircularView getImageView() {
        return imageView;
    }

    public void setImageView(CircularView imageView) {
        this.imageView = imageView;
    }

    protected void setImage(Context context){
        this.imageView = new CircularView(context);
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(dimension.x, dimension.y));
        this.imageView.requestLayout();
        this.imageView.setVisibility(View.GONE);
    }

    public CheckPointReachedListener getListener() {
        return listener;
    }

    public void setListener(CheckPointReachedListener listener) {
        this.listener = listener;
    }

    public void reachCheckPoint(){
        listener.CheckPointReached(this);
    }
}
