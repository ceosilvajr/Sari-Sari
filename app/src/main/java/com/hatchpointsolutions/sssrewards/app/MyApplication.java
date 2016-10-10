package com.hatchpointsolutions.sssrewards.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by ceosilvajr on 2/15/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
