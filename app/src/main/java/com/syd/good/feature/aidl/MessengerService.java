package com.syd.good.feature.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Log.e(TAG,"receive msg from Client:"+msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null,2);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","嗯，你的消息服务端已经收到，稍后会回复你。");
                    replyMessage.setData(bundle);
                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    private final Messenger mMessenger = new Messenger(new MessengerHandler());


    public MessengerService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.xx.xx");
        if (check == PackageManager.PERMISSION_DENIED){
//            return  null;
        }
        Log.e("oBind",mMessenger.getBinder().toString());
       return mMessenger.getBinder();
    }
}