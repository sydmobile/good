package com.syd.good.feature.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.syd.good.utils.L;

/**
 * <p>
 * date: 2020/10/29 11:24
 *
 * @author syd
 * @version 1.0
 */
public class MyRemoteService extends Service {
    private static final String TAG = "MyRemoteService";
    AIDL_Service1.Stub mBinder = new AIDL_Service1.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void test() throws RemoteException {

        }

        @Override
        public void AIDL_Service() throws RemoteException {
            L.e("MyRemoteService", "AIDL_Service():客户端通过 AIDL 与 远程后台成功通信");
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        L.e(TAG, "onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy()");
    }


}
