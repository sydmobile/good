package com.syd.good.feature.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.databinding.AidlActivityMessengerBinding;

import java.util.ArrayList;
import java.util.List;

public class IpcActivity extends AppCompatActivity {
    private static final String TAG = "IpcActivity";
    AidlActivityMessengerBinding binding;
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());
    // Messenger 练习使用 mConnection
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                Log.e("client:", service.toString());
                Messenger mService = new Messenger(service);
                Log.e("client222:", mService.getBinder().toString());

                Log.e("client1:", service.toString());
                Message msg = Message.obtain(null, 1);
                Bundle data = new Bundle();
                data.putString("msg", "hello ,this is client");
                msg.setData(data);
                msg.replyTo = mGetReplyMessenger;
                Log.e("client:", service.toString());
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.e("ex", e.toString());
                }
            } catch (Exception e) {
                Log.e("xx", e.toString());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ServiceConnection mConnection1 = new ServiceConnection() {
        @Override
        // 如果是跨进程的情况下， service 就是 BinderProxy 类型
        // 之所以通过 asInterface 是为了转成 Binder 的接口类型，这样才能调用里面的方法。
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("client", service.toString());
            mBookManager = IBookManager.Stub.asInterface(service);
            Log.e(TAG,"asInterface:"+mBookManager.toString());
            try {
                List<Book> list = mBookManager.getBookList();
                Log.e("client", "query book list,list type:" + list.getClass().getCanonicalName());
                Log.e("client", list.toString());
                mBookManager.addBook(new Book(3,"Android开发中"));
                Log.e("client", mBookManager.getBookList().toString());
                Log.e(TAG,"client:listener"+mListener.toString());
                mBookManager.registerListener(mListener);
                mListener.asBinder().isBinderAlive();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private IOnNewBookArrivedListener mListener = new Listener();

    class  Listener extends IOnNewBookArrivedListener.Stub{
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(1,newBook).sendToTarget();

        }
    }

    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Log.e(TAG,"receive new book:"+msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
    private IBookManager mBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AidlActivityMessengerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    public void init() {
        // Messenger 练习
        binding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IpcActivity.this, MessengerService.class);
                bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
            }
        });

        binding.btAidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IpcActivity.this, BookManagerService.class);
                bindService(intent, mConnection1, Service.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(mConnection);
        if (mBookManager!=null && mBookManager.asBinder().isBinderAlive()){
            Log.e(TAG,"unregister listener:"+mListener);
            try {
                mBookManager.unregisterListener(mListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection1);

    }


    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Log.e("client", "receive msg from Service:" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}