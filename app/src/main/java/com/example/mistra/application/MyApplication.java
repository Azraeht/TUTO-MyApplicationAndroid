package com.example.mistra.application;

import android.app.Application;

import com.example.mistra.activity.R;
import com.squareup.otto.Bus;

/**
 * Created by MISTRA on 19/11/2014.
 */
public class MyApplication extends Application {
    Bus eventBus;

    private static MyApplication instance;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        eventBus = new Bus();

    }


    public boolean isTwoPane(){
        return getResources().getBoolean(R.bool.twoPane);
    }

    public Bus getEventBus(){
        return eventBus;
    }
}
