package com.anthony.interstellar_x;

import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;

import java.util.List;

/**
 * Created by anthonyliu on 2016/4/5.
 */
public class Collision {

    public static void collideAnalysis(List<PhysicalObject> physicalObjects){

//        // Remove blackholes from collide analysis
//        for (Iterator<PhysicalObject> iter = physicalObjects.listIterator(); iter.hasNext(); ) {
//            PhysicalObject physicalObject = iter.next();
//            if (physicalObject instanceof Blackhole) {
//                iter.remove();
//            }
//        }

        for(int i = 0; i < physicalObjects.size(); i++){
            // All but last physicalObject is processed
            if(i < (physicalObjects.size() -1)){
                for(int j = i + 1; j < physicalObjects.size(); j++){
                    if(isColliding(physicalObjects.get(i), physicalObjects.get(j))){
                        System.out.println("COLLIDING!!!");
                        //TODO Update velocities of two physicalObjects
                        velocityExchange(physicalObjects.get(i), physicalObjects.get(j));
                    }
                }
            }
        }

    }

    /**
     * Check if two objects are colliding
     * */
    private static boolean isColliding(PhysicalObject object1, PhysicalObject object2){
        boolean isColliding = false;

        double distance = Math.sqrt(Math.pow(object1.getPosition().x - object2.getPosition().x, 2)
                + Math.pow(object1.getPosition().y - object2.getPosition().y, 2));
        double sumOfRadius = object1.getDimension().x / 2 + object2.getDimension().x / 2;

        if(distance <= sumOfRadius){
            isColliding = true;
        }

        return isColliding;
    }

    private static void velocityExchange(PhysicalObject object1, PhysicalObject object2){
        double M1 = getM1(object1.getMass(), object2.getMass());
        double M2 = getM2(object1.getMass(), object2.getMass());
        double v1_x = object1.getVelocity_x();
        double v1_y = object1.getVelocity_y();
        double v2_x = object2.getVelocity_x();
        double v2_y = object2.getVelocity_y();
        object1.setVelocity_x(M2*v1_x + M1*v2_x);
        object1.setVelocity_y(M2 * v1_y + M1 * v2_y);
        object2.setVelocity_x(M1*v1_x - M2*v2_x);
        object2.setVelocity_y(M1*v1_y - M2*v2_y);
    }

    private static double getM1(int mass_1, int mass_2){
        return ((double)2*mass_1)/(mass_1 + mass_2);
    }

    private static double getM2(int mass_1, int mass_2){
        return ((double)(mass_1 - mass_2))/(mass_1 + mass_2);
    }

}
