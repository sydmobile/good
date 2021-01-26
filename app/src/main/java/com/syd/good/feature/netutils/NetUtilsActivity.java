package com.syd.good.feature.netutils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.common.CommonAdapter;
import com.syd.good.feature.common.CommonEntity;
import com.syd.good.feature.common.CommonType;
import com.syd.good.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 *
 * @author syd
 * @version 1.0
 */
public class NetUtilsActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    NetStateBroadcastReceiver receiver;

    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();

        datas.add(new CommonEntity("标题", "内容", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                bindService(new Intent(NetUtilsActivity.this, MyService.class), new conn(), Service.BIND_AUTO_CREATE);
                L.e(TAG,"onClick");



            }
        };
        actionInit(datas, callBack);



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (receiver == null) {
            receiver = new NetStateBroadcastReceiver();
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    /**
     * 基本内容
     *
     * @param datas    数据
     * @param callBack 回调
     */
    private void actionInit(List<CommonEntity> datas, CommonAdapter.CallBack callBack) {
        CommonAdapter adapter = new CommonAdapter(this, callBack);
        adapter.setDatas(datas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getDatas().get(position).getmType()) {
                    case CommonType.TYPE_TITLE:
                        return 4;
                    case CommonType.TYPE_CONTENT1:
                        return 2;
                    default:
                        return 1;

                }
            }
        });
        rlv.setLayoutManager(gridLayoutManager);
        rlv.setAdapter(adapter);
    }

    public void checkState_23() {
        // 步骤1 首先获取 ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // 步骤2 获取 ConnectivityManager 对象对应的 NetworkInfo 对象
        // NetworkInfo 对象包含了网络连接的所有信息
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConnected = networkInfo.isConnected();

        // 获取移动数据连接的信息
        NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConnected = networkInfo1.isConnected();
        L.e("wifi是否连接：" + isWifiConnected + "===移动数据是否连接：" + isMobileConnected);
    }

    public void checkState23OrNew() {
        // 获取 ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取所有网络连接的信息
        Network[] networks = connectivityManager.getAllNetworks();
        // 将所有信息打印出来
        StringBuilder sb = new StringBuilder();
        for (Network network : networks) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            sb.append(networkInfo.getTypeName() + "是否连接" + networkInfo.isConnected() + "\r\n");
        }
        L.e(TAG, sb.toString());

    }

    static class NetStateBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo dataInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                L.e("wifi是否连接：" + wifiInfo.isConnected() + "===移动数据是否连接：" + dataInfo.isConnected());
            } else {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                Network[] networks = connectivityManager.getAllNetworks();
                if (networks == null || networks.length == 0) {
                    L.e("NetStateBroadcastReceiver", "无网络");
                    return;
                }
                for (Network network : networks) {
                    L.e("NetStateBroadcastReceiver", "下载：" + connectivityManager.getNetworkCapabilities(network).getLinkDownstreamBandwidthKbps());
                    L.e("NetStateBroadcastReceiver", "名字：" + connectivityManager.getLinkProperties(network).getInterfaceName());
                }
            }

        }
    }



    static class conn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
