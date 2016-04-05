package com.anthony.interstellar_x;

import com.anthony.interstellar_x.Interstellar_Objects.Blackhole;
import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;

import java.util.Iterator;
import java.util.List;

/**
 * Created by anthonyliu on 2016/4/5.
 */
public class Collision {

    public static void collideAnalysis(List<PhysicalObject> physicalObjects){

        // Remove blackholes from collide analysis
        for (Iterator<PhysicalObject> iter = physicalObjects.listIterator(); iter.hasNext(); ) {
            PhysicalObject physicalObject = iter.next();
            if (physicalObject instanceof Blackhole) {
                iter.remove();
            }
        }

        for(int i = 0; i < physicalObjects.size(); i++){
            // All but last physicalObject is processed
            if(i < (physicalObjects.size() -1)){
                for(int j = i + 1; j < physicalObjects.size(); j++){
                    if(isColliding(physicalObjects.get(i), physicalObjects.get(j))){
                        System.out.println("COLLIDING!!!");
                        //TODO Update velocities of two physicalObjects
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

}
