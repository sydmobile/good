package com.syd.good;

import android.app.Application;

import com.syd.good.utils.SPUtils;

/**
 * @author syd
 */
public class App extends Application {
    private static final String TAG = "App";
    private static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SPUtils.init();
    }


}
