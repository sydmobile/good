package com.syd.good.feature.aidl;

import android.os.RemoteException;

import com.syd.good.MyIWindowManager;
import com.syd.good.feature.service.AIDL_Service1;

/**
 * <p>
 * date: 2021/6/1 10:20
 *
 * @author syd
 * @version 1.0
 */
public class MyWindowManagerService extends AIDL_Service1.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void test() throws RemoteException {

    }

    @Override
    public void AIDL_Service() throws RemoteException {

    }
}
