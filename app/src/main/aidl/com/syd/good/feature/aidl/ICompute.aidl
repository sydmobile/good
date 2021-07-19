// ICompute.aidl
package com.syd.good.feature.aidl;

// Declare any non-default types here with import statements

interface ICompute {

  void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int add(int a,int b);
}