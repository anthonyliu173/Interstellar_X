package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anthony.interstellar_x.App;
import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.R;
import com.anthony.interstellar_x.ScreenDimension;

import java.util.List;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class PhysicalObject {

    protected int mass;
    protected Point dimension;
    protected Point position;
    protected Double velocity_x = 0.0;
    protected Double velocity_y = 0.0;

    protected ImageView imageView;

    protected void setImage(Context context){
        this.imageView = new ImageView(context);
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(dimension.x, dimension.y));
        this.imageView.requestLayout();

        if(this instanceof Spacecraft){
            this.imageView.setBackgroundResource(R.drawable.spacecraft);
        }else if(this instanceof Blackhole){
            this.imageView.setBackgroundResource(R.drawable.blackhole);

        }
    }

    public void updatePosition(){

        if(this instanceof Blackhole && !App.BLACKHOLE_MOVABLE){
            return;
        }

        position.set((int)(position.x + (velocity_x * Constants.TIME_CONSTANT)), (int)(position.y + (velocity_y * Constants.TIME_CONSTANT)));
        imageView.setX(position.x - dimension.x / 2);
        imageView.setY(position.y - dimension.y / 2);

        checkBounce();

    }

    public void updateVelocity(List<PhysicalObject> visibleObjects, double sensor_x, double sensor_y){

        if(this instanceof Blackhole && !App.BLACKHOLE_MOVABLE){
            return;
        }

        double total_force_x = sensor_x * Constants.SENSOR_NORMALIZATION;
        double total_force_y = sensor_y * Constants.SENSOR_NORMALIZATION;

        for(PhysicalObject object : visibleObjects){
            Point force = getForce(object.getMass(), object.getPosition());
            total_force_x += force.x;
            total_force_y += force.y;
        }

        double acc_x = (total_force_x / mass) * Constants.TIME_CONSTANT;
        double acc_y = (total_force_y / mass) * Constants.TIME_CONSTANT;

        velocity_x = velocity_x + (acc_x * Constants.TIME_CONSTANT);
        velocity_y = velocity_y + (acc_y * Constants.TIME_CONSTANT);

        checkBounce();
    }

    /**
     * Gravity formula: F = (GMm)/r^2
     * */
    private Point getForce(int targetMass, Point targetPosition){

        int distance_x = Math.abs(targetPosition.x - position.x);
        int distance_y = Math.abs(targetPosition.y - position.y);
        double distance_scale = Math.sqrt(Math.pow(distance_x, 2) + Math.pow(distance_y, 2));

        if((int)distance_scale == 0){
            return new Point(0, 0);
        }

        int force_scale = (int)((Constants.GRAVITY_CONSTANT * targetMass * mass)/(Math.pow(distance_x , 2) + Math.pow(distance_y, 2)));

        int force_x = (int)(force_scale * distance_x / distance_scale);
        int force_y = (int)(force_scale * distance_y / distance_scale);

        if(position.x > targetPosition.x){
            force_x = -force_x;
        }

        if(position.y > targetPosition.y){
            force_y = -force_y;
        }

        return new Point(force_x, force_y);
    }

    private void checkBounce(){

        if( (position.x - dimension.x/2)< 0 || (position.x + dimension.x/2) > ScreenDimension.getScreenWidth()){
            velocity_x = (Constants.BOUNCE_COEFFICIENT * velocity_x);
        }

        if( (position.y - dimension.y/2) < 0 || (position.y + dimension.y/2) > ScreenDimension.getScreenHeight()){
            velocity_y = (Constants.BOUNCE_COEFFICIENT * velocity_y);
        }

        if( (position.x - dimension.x/2) < 0){
            position.set(dimension.x/2 + 1, position.y);
        }

        if( (position.x + dimension.x/2) > ScreenDimension.getScreenWidth()){
            position.set(ScreenDimension.getScreenWidth() - dimension.x/2 - 1, position.y);
        }

        if( (position.y - dimension.y/2) < 0){
            position.set(position.x, dimension.y/2 + 1);
        }

        if( (position.y + dimension.y)/2 > ScreenDimension.getScreenHeight()){
            position.set(position.x, ScreenDimension.getScreenHeight() - dimension.y/2 - 1);
        }

    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
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

    public Double getVelocity_x() {
        return velocity_x;
    }

    public void setVelocity_x(Double velocity_x) {
        this.velocity_x = velocity_x;
    }

    public Double getVelocity_y() {
        return velocity_y;
    }

    public void setVelocity_y(Double velocity_y) {
        this.velocity_y = velocity_y;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setImageViewAnimation(){

    }

}
