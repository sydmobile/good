package com.syd.good.carsonblog.a1socket;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.syd.good.databinding.CommonActivityBinding;
import com.syd.good.feature.a_common.base.CommonActivity;
import com.syd.good.feature.a_common.adapter.CommonAdapter;
import com.syd.good.feature.a_common.bean.CommonEntity;
import com.syd.good.feature.a_common.CommonType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Socket 客户端
 * <p>
 * date: 2021/1/26 17:08
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("NonConstantResourceId")
public class SocketClientActivity extends CommonActivity<CommonActivityBinding> {

    Socket socket;

    @Override
    protected void initBinding() {
        mBinding = CommonActivityBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void initView() {

    }

    @Override
    protected List<CommonEntity> createList() {
        List<CommonEntity> list = new ArrayList<>();
        list.add(new CommonEntity("Socket 客户端", "", CommonType.TYPE_TITLE, null));
        list.add(new CommonEntity("开始连接", "开始连接", CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));
        list.add(new CommonEntity("", "发送数据", CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));
        list.add(new CommonEntity("", "接收数据", CommonType.TYPE_CONTENT_SPAN_SIZE_1, null));
        return list;
    }

    @Override
    protected CommonAdapter.CallBack createCallBack() {
        return commonEntity -> {
            switch (commonEntity.getmContent()) {
                case "开始连接":
                    startSocketConnect();
                    break;
                case "发送数据":
                    Log.e("isClose","="+socket.isClosed());
                    if (!isConnected()) {
                        Toast.makeText(this, "Socket未连接", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sendData();
                    break;
                case "接收数据":
                    receiveData();
                    break;
                default:

            }
        };
    }

    /**
     * 创建 Socket 并建立连接
     */
    private void startSocketConnect() {
        new Thread(() -> {

            try {
                socket = new Socket("192.168.1.107", 8899);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean isConnected() {
        if (socket == null) {
            return false;
        }
        return socket.isConnected() && !socket.isClosed();
    }


    /**
     * 发送数据
     */
    private void sendData() {
        new Thread(()->{
            try {
                // 1. 从 Socket 中获得输出流对象 OutputStream
                OutputStream outputStream = socket.getOutputStream();

                // 2.写入需要发送的数据到输出流对象中
                OutputStreamWriter osw = new OutputStreamWriter(outputStream);
                osw.write("来自客户端\n");
                osw.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void receiveData(){
        new Thread(()->{
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);
                String content;
                Log.e("xxxx","wwwww");
                while ((content = br.readLine())!=null){
                    Log.e("content",content);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
