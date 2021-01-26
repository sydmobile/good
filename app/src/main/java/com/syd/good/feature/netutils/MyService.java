package com.syd.good.feature.netutils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.syd.good.utils.L;

/**
 * <p>
 * date: 2020/10/28 14:05
 *
 * @author syd
 * @version 1.0
 */
public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        L.e("MyService","onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.e("MyService","onBind");
        return new m();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.e("MyService","onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    class m extends Binder {

    }
}
