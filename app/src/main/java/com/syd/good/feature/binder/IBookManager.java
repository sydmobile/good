package com.syd.good.feature.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.syd.good.feature.aidl.Book;

import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2022/3/11 17:38
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface IBookManager extends android.os.IInterface {


    public static abstract class Stub extends Binder implements IBookManager{
        private static final java.lang.String DESCRIPTOR = "com.syd.good.feature.binder.IBookManager";

        public Stub(){
            this.attachInterface(this,DESCRIPTOR);

        }

        public static IBookManager asInterface(IBinder obj){
            if (obj == null){
                return  null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            //.....
            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IBookManager{
            private IBinder mRemote;
            Proxy(IBinder remote){
                mRemote = remote;
            }



            @Override
            public IBinder asBinder() {
                return null;
            }


            @Override
            public List<Book> getBookList() throws RemoteException {
                return null;
            }
        }

    }

    public java.util.List<com.syd.good.feature.aidl.Book> getBookList() throws android.os.RemoteException;

}
