package com.syd.good.feature.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * <p>
 * date: 2020/11/12 9:29
 *
 * @author syd
 * @version 1.0
 */
public class MyServiceConnection implements ServiceConnection {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

}
