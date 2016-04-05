package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anthony.interstellar_x.App;
import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.R;

import java.util.List;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class PhysicalObject {

    protected int mass;
    protected Point dimension;
    protected Point position;
    protected Point velocity;

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
        position.set(position.x + (velocity.x * Constants.TIME_CONSTANT), position.y + (velocity.y * Constants.TIME_CONSTANT));
    }

    public void updateVelocity(List<PhysicalObject> visibleObjects){

        if(this instanceof Blackhole && !App.BLACKHOLE_MOVABLE){
            return;
        }

        int total_force_x = 0;
        int total_force_y = 0;

        for(PhysicalObject object : visibleObjects){
            Point force = getForce(object.getMass(), object.getPosition());
            total_force_x += force.x;
            total_force_y += force.y;
        }

        int acc_x = (total_force_x / mass) * Constants.TIME_CONSTANT;
        int acc_y = (total_force_y / mass) * Constants.TIME_CONSTANT;

        velocity.set(velocity.x + (acc_x * Constants.TIME_CONSTANT), velocity.y + (acc_y * Constants.TIME_CONSTANT));

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

        if(position.x < targetPosition.x){
            force_x = -force_x;
        }

        if(position.y < targetPosition.y){
            force_y = -force_y;
        }

        return new Point(force_x, force_y);
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

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
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
