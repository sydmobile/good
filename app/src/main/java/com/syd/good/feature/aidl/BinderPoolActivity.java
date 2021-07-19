package com.syd.good.feature.aidl;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.R;

public class BinderPoolActivity extends AppCompatActivity {

    private static final String TAG = "BinderPoolActivity";
    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidl_activity_binder_pool);
        new Thread() {
            @Override
            public void run() {
                super.run();
                doWork();
            }
        }.start();

    }


    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);

        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        Log.e(TAG, "visit ISecurityBinder");
        String msg = "helloworld-安卓";
        Log.e(TAG, "content:" + msg);
        try {
            String password = mSecurityCenter.encrypt(msg);
            Log.e(TAG, "encrypt:" + password);
            Log.e(TAG, "decrypt:" + mSecurityCenter.decrypy(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ICompute.Stub.asInterface(computeBinder);

        try {
            Log.e(TAG, "3+5=" + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}