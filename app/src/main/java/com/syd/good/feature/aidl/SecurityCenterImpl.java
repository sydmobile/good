package com.syd.good.feature.aidl;

import android.os.RemoteException;

/**
 * <p>
 * date: 2021/6/15 17:43
 *
 * @author syd
 * @version 1.0
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub{

    private static final char SECRET_CODE = '^';

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i =0;i<chars.length;i++){
            chars[i] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypy(String password) throws RemoteException {
        return encrypt(password);
    }
}
