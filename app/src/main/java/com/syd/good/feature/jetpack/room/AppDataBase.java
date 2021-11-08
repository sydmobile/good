package com.syd.good.feature.jetpack.room;

import android.content.Context;


import com.syd.good.feature.jetpack.User;
import com.syd.good.feature.jetpack.UserDao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/30 16:49
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Database(version = 1, entities = {User.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase mAppDataBase;

    public abstract UserDao userDao();

    private AppDataBase() {

    }

    public static AppDataBase getInstance(Context context) {
        if (mAppDataBase == null) {
            synchronized (AppDataBase.class) {
                if (mAppDataBase == null) {
                    mAppDataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "app_database_good")
                            .build();
                }
            }
        }
        return mAppDataBase;
    }

}
