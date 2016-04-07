package com.anthony.interstellar_x.Levels;

import com.anthony.interstellar_x.Constants;
import com.anthony.interstellar_x.GamingActivity;
import com.anthony.interstellar_x.Interstellar_Objects.Blackhole;
import com.anthony.interstellar_x.Interstellar_Objects.Meteorite;
import com.anthony.interstellar_x.Interstellar_Objects.Spacecraft;
import com.anthony.interstellar_x.ScreenDimension;

/**
 * Created by anthonyliu on 2016/4/7.
 */
public class Level_8 extends GamingActivity {

    @Override
    protected void declarePhysicalObjects() {
        super.declarePhysicalObjects();

        physicalObjects.add(new Spacecraft(this));
        physicalObjects.add(new Meteorite(this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Meteorite(this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Meteorite(this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Meteorite(this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Meteorite(this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Meteorite(this, Meteorite.METEORITE_SIZE.TALL));
        physicalObjects.add(new Blackhole(this, Constants.BLACKHOLE_MASS, ScreenDimension.getScreenWidth() * 3 / 4,
                ScreenDimension.getScreenHeight() * 3 / 4, 0.0, 0.0));
        physicalObjects.add(new Blackhole(this, Constants.BLACKHOLE_MASS, ScreenDimension.getScreenWidth() / 4,
                ScreenDimension.getScreenHeight() / 4, 0.0, 0.0));

    }
}