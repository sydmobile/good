package com.syd.good.feature.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {
    private static final String TAG = "BookManagerService";
    // 支持并发读写
    private final CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

//    private final CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> mRemoteListenerList = new RemoteCallbackList<>();

    private final Binder mBinder = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            Log.e(TAG,"listener:"+listener.toString());
            mRemoteListenerList.register(listener);
//            if (!mListenerList.contains(listener)){
//                mListenerList.add(listener);
//            }else {
//                Log.e(TAG,"already exists");
//            }
//            Log.e(TAG,"regsiterListener,size:"+mListenerList.size());
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if(mListenerList.contains(listener)){
//                mListenerList.remove(listener);
//                Log.e(TAG,"unregister listener succeed");
//            }else {
//                Log.e(TAG,"not found");
//            }
//            Log.e(TAG,"registerListener,size:"+mListenerList.size());
            mRemoteListenerList.unregister(listener);
        }

    };

    public BookManagerService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServiceWorker()).start();
    }

    public CopyOnWriteArrayList<Book> getmBookList(){
        return mBookList;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        final int N = mRemoteListenerList.beginBroadcast();
        for (int i=0;i<N;i++){
           IOnNewBookArrivedListener l = mRemoteListenerList.getBroadcastItem(i);
           if (l!=null){
               try {

               l.onNewBookArrived(book);
               }catch (RemoteException e){
                   e.printStackTrace();
               }
           }
        }
        mRemoteListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable{
        @Override
        public void run() {
            // do background processing here....
            while (!mIsServiceDestroyed.get()){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size()+1;
                Book book = new Book(bookId,"Book:"+bookId);
                try {
                    onNewBookArrived(book);
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        }
    }

}