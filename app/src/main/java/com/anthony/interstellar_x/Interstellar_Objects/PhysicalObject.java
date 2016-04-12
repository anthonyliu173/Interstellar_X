package com.anthony.interstellar_x.Interstellar_Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anthony.interstellar_x.App;
import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.R;
import com.anthony.interstellar_x.ScreenDimension;
import com.anthony.interstellar_x.SpaceDimension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by anthonyliu on 2016/4/3.
 */
public class PhysicalObject extends SpaceDimension{

    protected int mass;
    protected Double velocity_x = 0.0;
    protected Double velocity_y = 0.0;
    protected Double speed = 0.0;
    protected Double maxSpeed = 0.0;

    protected ImageView imageView;

    protected void setImage(Context context){
        this.imageView = new ImageView(context);
        this.imageView.setLayoutParams(new LinearLayout.LayoutParams(dimension.x, dimension.y));
        this.imageView.requestLayout();

        if(this instanceof Spacecraft){
            this.imageView.setBackgroundResource(R.drawable.spacecraft);
        }else if(this instanceof Blackhole){
            this.imageView.setBackgroundResource(R.drawable.blackhole);
        }else if(this instanceof Meteorite){
            this.imageView.setBackgroundResource(R.drawable.spacecraft);
        }
    }

    public void updatePosition(){

        if(this instanceof Blackhole && !App.BLACKHOLE_MOVABLE){
            return;
        }

        position.set((int) (position.x + (velocity_x * Constants.TIME_CONSTANT)), (int) (position.y + (velocity_y * Constants.TIME_CONSTANT)));
        imageView.setX(position.x - dimension.x / 2);
        imageView.setY(position.y - dimension.y / 2);

    }

    public void updateVelocity(List<Blackhole> visibleObjects, double sensor_x, double sensor_y){

        if(this instanceof Blackhole && !App.BLACKHOLE_MOVABLE){
            return;
        }

        double total_force_x = sensor_x * Constants.SENSOR_NORMALIZATION;
        double total_force_y = sensor_y * Constants.SENSOR_NORMALIZATION;

        for(PhysicalObject object : visibleObjects){
            double[] force = getForce(object.getMass(), object.getPosition());
            total_force_x += force[0];
            total_force_y += force[1];
        }

        double acc_x = (total_force_x / mass) * Constants.TIME_CONSTANT;
        double acc_y = (total_force_y / mass) * Constants.TIME_CONSTANT;

        velocity_x = velocity_x + (acc_x * Constants.TIME_CONSTANT);
        velocity_y = velocity_y + (acc_y * Constants.TIME_CONSTANT);

        if(velocity_x > Constants.MAX_VECTOR_VELOCITY){
            velocity_x = Constants.MAX_VECTOR_VELOCITY;
        }

        if(velocity_y > Constants.MAX_VECTOR_VELOCITY){
            velocity_y = Constants.MAX_VECTOR_VELOCITY;
        }

        speed = round(Math.sqrt(Math.pow(velocity_x, 2) + Math.pow(velocity_y, 2)), 2);
        if(speed > maxSpeed){
            maxSpeed = speed;
        }

        // Meteorites and Blackholes do not bounce
        if(this instanceof Spacecraft){
            checkBounce();
        }
    }

    public void isInBound(){
        if(this instanceof Meteorite){
            if(this.getPosition().x + 3*this.getDimension().x < 0){
                ((Meteorite)this).meteoriteGone();
                return;
            }
            if(this.getPosition().y + 3*this.getDimension().y < 0){
                ((Meteorite)this).meteoriteGone();
                return;
            }
            if(this.getPosition().x - 2*this.getDimension().x > ScreenDimension.getScreenWidth()){
                ((Meteorite)this).meteoriteGone();
                return;
            }
            if(this.getPosition().y - 2*this.getDimension().y > ScreenDimension.getScreenHeight()){
                ((Meteorite)this).meteoriteGone();
                return;
            }
        }
    }

    /**
     * Gravity formula: F = (GMm)/r^2
     * */
    private double[] getForce(int targetMass, Point targetPosition){

        double[] force = {0.0, 0.0};

        int distance_x = Math.abs(targetPosition.x - position.x);
        int distance_y = Math.abs(targetPosition.y - position.y);
        double distance_scale = Math.sqrt(Math.pow(distance_x, 2) + Math.pow(distance_y, 2));

        if((int)distance_scale == 0){
            return force;
        }

        double force_scale = (Constants.GRAVITY_CONSTANT * targetMass * mass)/(Math.pow(distance_x , 2) + Math.pow(distance_y, 2));

        double force_x = force_scale * distance_x / distance_scale;
        double force_y = force_scale * distance_y / distance_scale;

        if(position.x > targetPosition.x){
            force_x = -force_x;
        }

        if(position.y > targetPosition.y){
            force_y = -force_y;
        }

        force[0] = force_x;
        force[1] = force_y;

        return force;
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

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
