// ISecurityCenter.aidl
package com.syd.good.feature.aidl;

// Declare any non-default types here with import statements

interface ISecurityCenter {
  void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String encrypt(String content);
    String decrypy(String password);
}