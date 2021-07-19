package com.syd.good.feature.aidl.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {
    private static final String TAG = "TCPServerService";

    private boolean mIsServiceDestroyed = false;

    private String[] mDefinedMesseages = new String[]{
            "你好啊，哈哈",
            "请问你叫什么名字啊？",
            "你在问我的名字吗？",
            "今天天气挺不错啊，蓝天白云",
            "我今天真忙，好多人找我",
            "保持微笑，爱笑的人运气不会太差！"
    };


    public TCPServerService() {
    }

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    class TcpServer implements  Runnable{

        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                Log.e(TAG,"establish tcp server failed,port:8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestroyed){
                try {
                    // 阻塞事件，知道有客户端连接后走下一步
                    final Socket client =  serverSocket.accept();
                    Log.e(TAG,"accept"+client.toString());
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void responseClient(Socket client) throws IOException{
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
        out.println("欢迎来到聊天室！");
        while (!mIsServiceDestroyed){
            // 阻塞事件，读取客户端发来的信息
            String str = in.readLine();
            Log.e(TAG,"msg from client:"+str);
            // 客户端关闭流，则 str 为 nul
            if (str == null){
                Log.e(TAG,"str为 null");
                // 客户端断开
                break;
            }
            int i = new Random().nextInt(mDefinedMesseages.length);
            out.println(mDefinedMesseages[i]);
            Log.e(TAG,"send:"+mDefinedMesseages[i]);
        }
        Log.e(TAG,"client quit.");
        // 关闭流
        out.close();
        in.close();

    }
}