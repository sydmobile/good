// IBinderPool.aidl
package com.syd.good.feature.aidl;

// Declare any non-default types here with import statements

interface IBinderPool {

  IBinder queryBinder(int binderCode);
}