package com.syd.good.feature.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.NotificationManagerCompat;
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
public class ServiceTestActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;

    private Intent intent;

    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 标准化流程
        List<CommonEntity> datas = new ArrayList<>();

        datas.add(new CommonEntity("标题", "启动前台服务", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "停止服务", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("标题", "启动通知", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {
                switch (commonEntity.getmContent()) {
                    case "启动前台服务":
                        intent = new Intent(ServiceTestActivity.this, MyForegroundService.class);
                        startForegroundService(intent);
                        break;
                    case "停止服务":
                        stopService(intent);
                        break;
                    case "启动通知":
                        startBaseNotification();
                        L.e(TAG,"启动通知");
                        break;
                    default:
                }


            }
        };
        actionInit(datas, callBack);
        Intent intent = new Intent(this,TestService.class);
        bindService(intent,new MyServiceConnection(), Service.BIND_AUTO_CREATE);

    }

    public void startBaseNotification() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        Intent intent = new Intent(this, MyNotificationClickBroadCasteReceiver.class);
        intent.setAction("cancel_id");
        intent.putExtra("notification_id", 8);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this, "channelId2");
        Notification notification = builder.setContentText("contentText")
                .setContentTitle("title")
                .setSmallIcon(R.drawable.ic_add_black_24dp)
                .setContentIntent(pendingIntent)
                .build();
        NotificationChannel notificationChannel = new NotificationChannel("channelId2", "渠别", NotificationManager.IMPORTANCE_HIGH);
        notificationManagerCompat.createNotificationChannel(notificationChannel);
        notificationManagerCompat.notify(8, notification);
    }

    @Override
    protected void onPause() {
        super.onPause();

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

}
