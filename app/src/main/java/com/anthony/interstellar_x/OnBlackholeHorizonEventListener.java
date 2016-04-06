package com.anthony.interstellar_x;

import com.anthony.interstellar_x.Interstellar_Objects.PhysicalObject;

/**
 * Created by anthonyliu on 2016/4/6.
 *
 * When Spacecraft or Meteorite gets too close to blackhole (passing the event horizon),
 * it will be gone ...
 */
public interface OnBlackholeHorizonEventListener {

    /**
     * Say goodbye to whatever is sucked into blackhole
     *
     * @param object - the poor object
     * */
    void GoodBye(PhysicalObject object);

}
