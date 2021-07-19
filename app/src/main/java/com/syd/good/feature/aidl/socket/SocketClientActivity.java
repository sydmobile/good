package com.syd.good.feature.aidl.socket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.databinding.AidlSocketActivitySocketClientBinding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketClientActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SocketClientActivity";
    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    AidlSocketActivitySocketClientBinding binding;
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    binding.tvReceive.setText(binding.tvReceive.getText() + (String) msg.obj);
                    Log.e(TAG, "收到");
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    binding.btSend.setEnabled(true);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AidlSocketActivitySocketClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSend.setEnabled(false);
        Intent service = new Intent(this, TCPServerService.class);
        startService(service);

        binding.btSend.setOnClickListener(this);
        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();

    }


    public void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                // 连接服务端，如果服务端还没有监听，则抛出异常
                socket = new Socket("localhost", 8688);
                Log.e(TAG, "new Socket");
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                ), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                Log.e(TAG, "connect server success");
            } catch (IOException e) {
                e.printStackTrace();
                SystemClock.sleep(1000);
                Log.e(TAG, "connect tcp server failed,retry.......");
            }
        }

        try {
            // 接收服务器端的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!SocketClientActivity.this.isFinishing()) {
                // 阻塞事件，等待读取服务端消息，如果服务端关闭，则抛出异常
                String msg = br.readLine();
                Log.e(TAG, "receive:" + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server" + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg)
                            .sendToTarget();

                }
            }
            Log.e(TAG,"quit......");
            mPrintWriter.close();
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                // 退出时 关闭当前 Socket
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onDestroy();
    }

    private String formatDateTime(long time) {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(time));
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btSend){
            final  String msg = binding.etInput.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter !=null){
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        mPrintWriter.println(msg);
                    }
                }.start();

                binding.etInput.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self"+time+":"+msg+"\n";
                binding.tvReceive.setText(binding.tvReceive.getText()+showedMsg);
            }
        }
    }

}