package com.twtstudio.retrox.darkcourse.base;


import android.graphics.Color;

import com.twtstudio.retrox.darkcourse.R;

/**
 * Created by retrox on 08/05/2017.
 */

public class ColorPalette {
    static int[] colors = {
            R.color.schedule_blue,
            R.color.schedule_blue2,
//            R.color.schedule_gray,
            R.color.schedule_green,
            R.color.schedule_green2,
            R.color.schedule_green3,
            R.color.schedule_green4,
            R.color.schedule_orange,
            R.color.schedule_pink,
            R.color.schedule_purple,
            R.color.schedule_purple2
    };

    public static int getColor(int postion){
        return colors[postion%colors.length];
    }

    public static int[] getColors() {
        return colors;
    }
}
