package com.syd.good.feature.jetpack.lifecycles;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 10:46
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyObserver implements LifecycleObserver {
    private static final String TAG = "MyObserver";


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void activityStart(){
        Log.e(TAG,"activityStart()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void activityStop(){
        Log.e(TAG,"activityStop()");
    }
}
