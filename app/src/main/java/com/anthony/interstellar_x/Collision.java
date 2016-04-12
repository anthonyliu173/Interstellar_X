package com.anthony.interstellar_x;

import com.anthony.interstellar_x.Interstellar_Objects.Blackhole;
import com.anthony.interstellar_x.Interstellar_Objects.CheckPoint;
import com.anthony.interstellar_x.Interstellar_Objects.Meteorite;
import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;

import java.util.List;

/**
 * Created by anthonyliu on 2016/4/5.
 */
public class Collision {

    public static void collideAnalysis(List<PhysicalObject> physicalObjects) {

        for (int i = 0; i < physicalObjects.size(); i++) {
            // All but last physicalObject is processed
            if (i < (physicalObjects.size() - 1)) {
                for (int j = i + 1; j < physicalObjects.size(); j++) {
                    if (isColliding(physicalObjects.get(i), physicalObjects.get(j))) {
                        // PhysicalObject is sucked into a blackhole 啾咪～
                        if (((physicalObjects.get(i) instanceof Spacecraft || physicalObjects.get(i) instanceof Meteorite) && physicalObjects.get(j) instanceof Blackhole) ||
                                ((physicalObjects.get(j) instanceof Spacecraft || physicalObjects.get(j) instanceof Meteorite) && physicalObjects.get(i) instanceof Blackhole)) {
                            if (physicalObjects.get(i) instanceof Spacecraft || physicalObjects.get(j) instanceof Spacecraft) {
                                ((Spacecraft) physicalObjects.get(i)).blackholeCollision();
                            } else if (physicalObjects.get(i) instanceof Meteorite || physicalObjects.get(j) instanceof Meteorite) {
                                ((Meteorite) physicalObjects.get(i)).blackholeCollision();
                            }
                        } else {
                            processCollision(physicalObjects.get(i), physicalObjects.get(j));
                        }
                    }
                }
            }
        }

    }

    public static void checkPointAnalysis(Spacecraft spacecraft, CheckPoint checkPoint) {
        if (isColliding(spacecraft, checkPoint)) {
            (checkPoint).reachCheckPoint();
        }
    }

    /**
     * Check if two objects are colliding
     */
    private static boolean isColliding(SpaceDimension object1, SpaceDimension object2) {
        boolean isColliding = false;
        boolean isBackhole = false; // if one object is blackhole, different boundary is used

        if (object1 instanceof Blackhole || object2 instanceof Blackhole) {
            isBackhole = true;
        }

        double distance = Math.sqrt(Math.pow(object1.getPosition().x - object2.getPosition().x, 2)
                + Math.pow(object1.getPosition().y - object2.getPosition().y, 2));
        double sumOfRadius;

        if (isBackhole) {
            if (object1 instanceof Blackhole) {
                sumOfRadius = object1.getDimension().x / 4 + object2.getDimension().x / 2;
            } else {
                sumOfRadius = object1.getDimension().x / 2 + object2.getDimension().x / 4;
            }
        } else {
            sumOfRadius = object1.getDimension().x / 2 + object2.getDimension().x / 2;
        }

        if (distance <= sumOfRadius) {
            isColliding = true;
        }

        return isColliding;
    }

    // collision formula can be found here http://www.real-world-physics-problems.com/elastic-collision.html
    private static void processCollision(PhysicalObject object1, PhysicalObject object2) {
        double v1_x_knot = object1.getVelocity_x();
        double v1_y_knot = object1.getVelocity_y();
        double v2_x_knot = object2.getVelocity_x();
        double v2_y_knot = object2.getVelocity_y();
        double v1_x_prime = velocityExchange(object1, object2, v1_x_knot, v2_x_knot);
        double v1_y_prime = velocityExchange(object1, object2, v1_y_knot, v2_y_knot);
        double v2_x_prime = velocityExchange(object2, object1, v2_x_knot, v1_x_knot);
        double v2_y_prime = velocityExchange(object2, object1, v2_y_knot, v1_y_knot);
        object1.setVelocity_x(Constants.COLLIDE_COEFFICIENT * v1_x_prime);
        object1.setVelocity_y(Constants.COLLIDE_COEFFICIENT * v1_y_prime);
        object2.setVelocity_x(Constants.COLLIDE_COEFFICIENT * v2_x_prime);
        object2.setVelocity_y(Constants.COLLIDE_COEFFICIENT * v2_y_prime);
    }

    private static double velocityExchange(PhysicalObject subject, PhysicalObject target, double subject_v, double target_v) {
        return (getM1(subject.getMass(), target.getMass()) * subject_v) + (getM2(subject.getMass(), target.getMass()) * target_v);
    }

    private static double getM1(int mass_1, int mass_2) {
        return ((double) (mass_1 - mass_2)) / (mass_1 + mass_2);
    }

    private static double getM2(int mass_1, int mass_2) {
        return ((double) (2 * mass_2)) / (mass_1 + mass_2);
    }

}
