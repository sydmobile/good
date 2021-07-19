// IOnNewBookArrivedListener.aidl
package com.syd.good.feature.aidl;
import com.syd.good.feature.aidl.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {

  void onNewBookArrived(in Book newBook);
}