package com.syd.good.feature.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.syd.good.utils.L;

/**
 * <p>
 * date: 2020/10/29 16:41
 *
 * @author syd
 * @version 1.0
 */
public class SQLiteOpenHelperBaseUse extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteOpenHelperBaseUse";
    // 数据库版本
    private static int version = 1;

    public SQLiteOpenHelperBaseUse(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 当数据库第一次创建时调用
     * 作用：创建数据库表 & 初始化数据
     * @param db db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        L.e(TAG,"onCreate");
        String sql = "create table person(id integer primary key autoincrement,name varchar(64),address varchar(64))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        L.e(TAG,"onUpgrade");
        String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);
    }

}
