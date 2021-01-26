package com.syd.good.feature.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationManagerCompat;

import com.syd.good.utils.L;

/**
 * <p>
 * date: 2020/10/28 17:48
 *
 * @author syd
 * @version 1.0
 */
public class MyNotificationClickBroadCasteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        L.e("MyNotificationClickBroadCasteReceiver","收到"+intent.getIntExtra("notification_id",-1));
        int id = intent.getIntExtra("notification_id", -1);
        if (id != -1) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.cancel(id);
        }
    }
}
