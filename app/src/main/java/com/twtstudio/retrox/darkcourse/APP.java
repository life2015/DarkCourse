package com.twtstudio.retrox.darkcourse;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by retrox on 05/05/2017.
 */

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirNext-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        Hawk.put("sid","3015204342");
    }
}
