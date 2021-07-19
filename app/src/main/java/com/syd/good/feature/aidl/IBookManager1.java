package com.syd.good.feature.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * <p>
 * date: 2021/6/9 17:33
 *
 * @author syd
 * @version 1.0
 */
public interface IBookManager1 extends IInterface {

    static final String DESCRIPTOR = "com.syd.good.featrue.aidl.IBookManager1";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION+0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION+1;

    public List<Book> getBookList() throws RemoteException;

    public void addBook(Book book) throws RemoteException;
}
