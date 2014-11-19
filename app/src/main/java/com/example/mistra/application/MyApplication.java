package com.example.mistra.application;

import android.app.Application;

import com.example.mistra.activity.R;

/**
 * Created by MISTRA on 19/11/2014.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public boolean isTwoPane(){
        return getResources().getBoolean(R.bool.twoPane);
    }
}
