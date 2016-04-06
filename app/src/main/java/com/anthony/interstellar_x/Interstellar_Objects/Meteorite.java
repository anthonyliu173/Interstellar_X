package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.OnBlackholeHorizonEventListener;
import com.anthony.interstellar_x.OnMeteoriteByPassListener;
import com.anthony.interstellar_x.ScreenDimension;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class Meteorite extends PhysicalObject {

    private OnBlackholeHorizonEventListener blackholeListener;
    private OnMeteoriteByPassListener meteoriteByPassListener;

    public enum METEORITE_SIZE {
        SHORT, TALL, GRANDE, VENTI;

        public static METEORITE_SIZE getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }

    }

    private METEORITE_SIZE size;

    /**
     * A new Meteorite object with random size, position and velocity
     * */
    public Meteorite(Context context){
        setMeteoriteByPassListener((GamingActivity)context);
        setBlackholeListener((GamingActivity)context);
        this.size = METEORITE_SIZE.getRandom();
        this.mass = findMass(size);
        this.dimension = new Point(findDimension(size), findDimension(size));
        this.position = Constants.getRandomPositionOnScreen(this.dimension.x, this.dimension.y);
        this.velocity_x = Constants.getRandomVelocity();
        this.velocity_y = Constants.getRandomVelocity();
        this.setImage(context);
    }

    /**
     * A specified Meteorite object with random position and velocity
     * */
    public Meteorite(Context context, METEORITE_SIZE size) {
        setMeteoriteByPassListener((GamingActivity)context);
        setBlackholeListener((GamingActivity)context);
        this.size = size;
        this.mass = findMass(size);
        this.dimension = new Point(findDimension(size), findDimension(size));
        this.position = Constants.getRandomPositionOnScreen(this.dimension.x, this.dimension.y);
        this.velocity_x = Constants.getRandomVelocity();
        this.velocity_y = Constants.getRandomVelocity();
        this.setImage(context);
    }

    /**
     * A specified Meteorite object with specified position and velocity
     * */
    public Meteorite(Context context, METEORITE_SIZE size, int position_x, int position_y, int velocity_x, int velocity_y) {
        setMeteoriteByPassListener((GamingActivity)context);
        setBlackholeListener((GamingActivity)context);
        this.size = size;
        this.mass = findMass(size);
        this.dimension = new Point(findDimension(size), findDimension(size));

        if(position_x < this.dimension.x){
            position_x = this.dimension.x;
        }else if(position_x > (ScreenDimension.getScreenWidth() - this.dimension.x)){
            position_x = ScreenDimension.getScreenWidth() - this.dimension.x;
        }

        if(position_y < this.dimension.y){
            position_y = this.dimension.y;
        }else if(position_y > (ScreenDimension.getScreenHeight() - this.dimension.y)){
            position_y = ScreenDimension.getScreenHeight() - this.dimension.y;
        }

        this.position = new Point(position_x, position_y);
        this.velocity_x = Constants.getRandomVelocity();
        this.velocity_y = Constants.getRandomVelocity();
        this.setImage(context);
    }

    private int findMass(METEORITE_SIZE size){
        switch (size){
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

    private int findDimension(METEORITE_SIZE size){
        switch (size){
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

    public void blackholeCollision(){
        blackholeListener.GoodBye(this);
    }

    public OnBlackholeHorizonEventListener getBlackholeListener() {
        return blackholeListener;
    }

    public void setBlackholeListener(OnBlackholeHorizonEventListener blackholeListener) {
        this.blackholeListener = blackholeListener;
    }

    public void meteoriteGone(){
        meteoriteByPassListener.LongGone(this);
    }

    public OnMeteoriteByPassListener getMeteoriteByPassListener() {
        return meteoriteByPassListener;
    }

    public void setMeteoriteByPassListener(OnMeteoriteByPassListener meteoriteByPassListener) {
        this.meteoriteByPassListener = meteoriteByPassListener;
    }
}
