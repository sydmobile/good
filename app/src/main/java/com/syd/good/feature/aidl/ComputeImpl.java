package com.syd.good.feature.aidl;

import android.os.RemoteException;

/**
 * <p>
 * date: 2021/6/15 17:53
 *
 * @author syd
 * @version 1.0
 */
public class ComputeImpl extends ICompute.Stub{
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
