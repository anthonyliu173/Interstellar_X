package com.anthony.interstellar_x.Levels;

import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;

public class Level_1 extends GamingActivity {

    @Override
    protected void declarePhysicalObjects() {
        super.declarePhysicalObjects();

        physicalObjects.add(new Spacecraft(this));

    }
}
