package com.syd.good.feature.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.syd.good.R;
import com.syd.good.utils.L;

/**
 * <p>
 * date: 2020/10/28 15:51
 *
 * @author syd
 * @version 1.0
 */
public class MyForegroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("MyForegroundService", "onCreate");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Intent intent = new Intent(this, MyNotificationClickBroadCasteReceiver.class);
            intent.setAction("cancel_id");
            intent.putExtra("notification_id", 1);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            Notification.Builder builder = new Notification.Builder(this, "channelId1");
            Notification notification = builder.setContentText("contentText")
                    .setContentTitle("title")
                    .setSmallIcon(R.drawable.ic_add_black_24dp)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationChannel notificationChannel = new NotificationChannel("channelId1", "渠道类别", NotificationManager.IMPORTANCE_HIGH);
            notificationManagerCompat.createNotificationChannel(notificationChannel);
            startForeground(1, notification);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e("MyForegroundService", "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
