package com.anthony.interstellar_x;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.anthony.interstellar_x.Levels.Level_1;

/**
 * Created by anthonyliu on 2016/4/11.
 */
public class PreambleHelper {

    public static RelativeLayout findPreamble(Activity activity){
        LayoutInflater inflater = activity.getLayoutInflater();

        if(activity instanceof Level_1){
            return (RelativeLayout) inflater.inflate(R.layout.preamble1, null);
        }

        return (RelativeLayout) inflater.inflate(R.layout.preamble2, null);

    }

}
