package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.Line;
import com.anthony.interstellar_x.OnBlackholeHorizonEventListener;
import com.anthony.interstellar_x.OnMeteoriteByPassListener;
import com.anthony.interstellar_x.R;
import com.anthony.interstellar_x.ScreenDimension;

import java.util.Date;
import java.util.Random;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Meteorite extends PhysicalObject {

    private OnBlackholeHorizonEventListener blackholeListener;
    private OnMeteoriteByPassListener meteoriteByPassListener;

    /**
     * imgWarning is the warning sign on screen that warns player that there's an incoming meteorite
     */
    private ImageView imgWarning;

    private Line trajectory;

    public enum METEORITE_SIZE {
        SHORT, TALL, GRANDE, VENTI;

        public static METEORITE_SIZE getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }

    }

    private METEORITE_SIZE size;

    /**
     * A new Meteorite object with random size, position and velocity
     */
    public Meteorite(Context context) {
        setMeteoriteByPassListener((GamingActivity) context);
        setBlackholeListener((GamingActivity) context);
        this.size = METEORITE_SIZE.getRandom();
        this.mass = findMass(size);
        this.dimension = new Point(findDimension(size), findDimension(size));
        this.position = Constants.getRandomPositionOnScreen(this.dimension.x, this.dimension.y);
        getRandomVelocity();

        this.setImage(context);
        this.setImgWarning(context);
    }

    /**
     * A specified Meteorite object with random position and velocity
     */
    public Meteorite(Context context, METEORITE_SIZE size) {
        setMeteoriteByPassListener((GamingActivity) context);
        setBlackholeListener((GamingActivity) context);
        this.size = size;
        this.mass = findMass(size);
        this.dimension = new Point(findDimension(size), findDimension(size));
        this.position = getRandomPosition();
        getRandomVelocity();

        this.setImage(context);
        this.setImgWarning(context);
    }

    /**
     * A specified Meteorite object with specified position and velocity
     */
    public Meteorite(Context context, METEORITE_SIZE size, int position_x, int position_y, int velocity_x, int velocity_y) {
        setMeteoriteByPassListener((GamingActivity) context);
        setBlackholeListener((GamingActivity) context);
        this.size = size;
        this.mass = findMass(size);
        this.dimension = new Point(findDimension(size), findDimension(size));

        if (position_x < this.dimension.x) {
            position_x = this.dimension.x;
        } else if (position_x > (ScreenDimension.getScreenWidth() - this.dimension.x)) {
            position_x = ScreenDimension.getScreenWidth() - this.dimension.x;
        }

        if (position_y < this.dimension.y) {
            position_y = this.dimension.y;
        } else if (position_y > (ScreenDimension.getScreenHeight() - this.dimension.y)) {
            position_y = ScreenDimension.getScreenHeight() - this.dimension.y;
        }

        this.position = new Point(position_x, position_y);
        this.velocity_x = Constants.getRandomVelocity();
        this.velocity_y = Constants.getRandomVelocity();
        this.setImage(context);
    }

    public ImageView getImgWarning() {
        return imgWarning;
    }

    public void setImgWarning(Context context) {
        this.imgWarning = new ImageView(context);
        this.imgWarning.setLayoutParams(new LinearLayout.LayoutParams(Constants.METEORITE_WARNING_DIMENSION, Constants.METEORITE_WARNING_DIMENSION));
        this.imgWarning.requestLayout();
        this.imgWarning.setBackgroundResource(R.drawable.blackhole);
        Point warningPosition = findWarningPosition();
        this.imgWarning.setX(warningPosition.x);
        this.imgWarning.setY(warningPosition.y);
    }

    private int findMass(METEORITE_SIZE size) {
        switch (size) {
            case SHORT:
                return Constants.METEORITE_MASS_SHORT;
            case TALL:
                return Constants.METEORITE_MASS_TALL;
            case GRANDE:
                return Constants.METEORITE_MASS_GRANDE;
            case VENTI:
                return Constants.METEORITE_MASS_VENTI;
            default:
                return Constants.METEORITE_MASS_SHORT;
        }
    }

    private int findDimension(METEORITE_SIZE size) {
        switch (size) {
            case SHORT:
                return Constants.METEORITE_MASS_SHORT_DIMENSION;
            case TALL:
                return Constants.METEORITE_MASS_TALL_DIMENSION;
            case GRANDE:
                return Constants.METEORITE_MASS_GRANDE_DIMENSION;
            case VENTI:
                return Constants.METEORITE_MASS_VENTI_DIMENSION;
            default:
                return Constants.METEORITE_MASS_SHORT_DIMENSION;
        }
    }

    public void blackholeCollision() {
        blackholeListener.GoodBye(this);
    }

    public OnBlackholeHorizonEventListener getBlackholeListener() {
        return blackholeListener;
    }

    public void setBlackholeListener(OnBlackholeHorizonEventListener blackholeListener) {
        this.blackholeListener = blackholeListener;
    }

    public void meteoriteGone() {
        meteoriteByPassListener.LongGone(this);
    }

    public OnMeteoriteByPassListener getMeteoriteByPassListener() {
        return meteoriteByPassListener;
    }

    public void setMeteoriteByPassListener(OnMeteoriteByPassListener meteoriteByPassListener) {
        this.meteoriteByPassListener = meteoriteByPassListener;
    }

    /**
     * getRandomPosition returns random position on specified boundary
     */
    private Point getRandomPosition() {
        long time = new Date().getTime();
        int x, y;
        if (time % 2 == 0) {
            if (time % 4 == 0) {
                x = (int) (-0.5 * ScreenDimension.getScreenWidth());
            } else {
                x = (int) (0.5 * ScreenDimension.getScreenWidth() + ScreenDimension.getScreenWidth());
            }
            y = randInt((int) (-0.5 * ScreenDimension.getScreenWidth()), ScreenDimension.getScreenHeight() + (int) (0.5 * ScreenDimension.getScreenWidth()));
        } else {
            if ((time - 1) % 4 == 0) {
                y = (int) (-0.5 * ScreenDimension.getScreenWidth());
            } else {
                y = (int) (0.5 * ScreenDimension.getScreenWidth() + ScreenDimension.getScreenHeight());
            }
            x = randInt((int) (-0.5 * ScreenDimension.getScreenWidth()), ScreenDimension.getScreenWidth() + (int) (0.5 * ScreenDimension.getScreenWidth()));
        }

        return new Point(x, y);

    }

    private void getRandomVelocity() {
        int x_pass = randInt((int) ((double) ScreenDimension.getScreenWidth() / 8), (int) ((double) 7 * ScreenDimension.getScreenWidth() / 8));
        int y_pass = randInt((int) ((double) ScreenDimension.getScreenHeight() / 8), (int) ((double) 7 * ScreenDimension.getScreenHeight() / 8));

        double speed = 1 * Constants.getRandom(Constants.RANDOM_VELOCITY_MIN, Constants.RANDOM_VELOCITY_MAX);
        double length = Math.sqrt(Math.pow((x_pass - position.x),2) + Math.pow((y_pass - position.y),2));
        this.velocity_x = speed * (x_pass - position.x) / length;
        this.velocity_y = speed * (y_pass - position.y) / length;

        getTrajectory(x_pass, y_pass);

    }

    private void getTrajectory(int x_pass, int y_pass){
        trajectory = new Line((double)(y_pass - this.position.y) / (x_pass - this.position.x), this.position.y - (this.position.x * (y_pass - this.position.y) / (x_pass - this.position.x)));
    }

    public static int randInt(int min, int max) {
        int randomNum = new Random().nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private Point findWarningPosition(){
        Point t_position = new Point();

        if(0 < trajectory.getX(0) && trajectory.getX(0) < ScreenDimension.getScreenWidth() && this.position.y < 0){
            t_position.x =  trajectory.getX(0);
            t_position.y = 0;
        }else if(0 < trajectory.getY(ScreenDimension.getScreenWidth()) && trajectory.getY(ScreenDimension.getScreenWidth()) < ScreenDimension.getScreenHeight() && this.position.x > 0){
            t_position.x = ScreenDimension.getScreenWidth() - Constants.METEORITE_WARNING_DIMENSION/2;
            t_position.y = trajectory.getY(ScreenDimension.getScreenWidth()) - Constants.METEORITE_WARNING_DIMENSION/2;
        }else if(0 < trajectory.getX(ScreenDimension.getScreenHeight()) && trajectory.getX(ScreenDimension.getScreenHeight()) < ScreenDimension.getScreenWidth() && this.position.y > 0){
            t_position.x = trajectory.getX(ScreenDimension.getScreenHeight()) - Constants.METEORITE_WARNING_DIMENSION/2;
            t_position.y = ScreenDimension.getScreenHeight() - Constants.METEORITE_WARNING_DIMENSION/2;
        }else{
            t_position.x = 0;
            t_position.y = trajectory.getY(0);
        }

        return t_position;
    }

}
