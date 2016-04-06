package com.anthony.interstellar_x;

import com.anthony.interstellar_x.Interstellar_Objects.Meteorite;

/**
 * Created by anthonyliu on 2016/4/6.
 *
 * When a meteorite travels off screen, this listener will ask
 * activity to remove the meteorite
 */
public interface OnMeteoriteByPassListener {

    /**
     * Say farewell to meteorite that is beyond our scope
     *
     * @param meteorite - the lonely interstellar traveler
     * */
    void LongGone(Meteorite meteorite);

}
