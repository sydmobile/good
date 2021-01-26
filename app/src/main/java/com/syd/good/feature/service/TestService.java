package com.syd.good.feature.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.syd.good.utils.L;

/**
 * <p>
 * date: 2020/11/11 15:33
 *
 * @author syd
 * @version 1.0
 */
public class TestService extends Service {
    private static final String TAG = "TestService";

    @Override
    public void onCreate() {
        super.onCreate();
        L.e(TAG,"onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(TAG,"onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e(TAG,"onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.e(TAG, "onBind");
        return new MyBinder();
    }


    class MyBinder extends Binder {


    }
}
