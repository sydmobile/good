package com.syd.good.feature.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

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
import java.util.Random;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 8:59
 * SQLite 学习
 *
 * @author syd
 * @version 1.0
 */
public class SQLiteBaseActivity extends BaseActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    SQLiteOpenHelperBaseUse dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Random random;

    @Override
    protected int layoutId() {
        return R.layout.common_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // wwww
        // 标准化流程
        List<CommonEntity> datas =

                new ArrayList<>();

        datas.add(new CommonEntity("数据库", "SQLite使用", CommonType.TYPE_TITLE, null));
        datas.add(new CommonEntity("数据库", "SQLiteOpenHelper 初始化", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("数据库", "打开数据库", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("数据库", "插入数据", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("数据库", "插入数据_id自动生成", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("数据库", "更新数据", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("数据库", "删除数据", CommonType.TYPE_CONTENT_COMMON, null));
        datas.add(new CommonEntity("数据库", "查询", CommonType.TYPE_CONTENT_COMMON, null));
        CommonAdapter.CallBack callBack = new CommonAdapter.CallBack() {
            @Override
            public void onClick(CommonEntity commonEntity) {

                switch (commonEntity.getmContent()) {
                    case "SQLiteOpenHelper 初始化":
                        initSqlLiteOpenHelper();
                        break;
                    case "打开数据库":
                        openSqlLite();
                        break;
                    case "插入数据":
                        insert();
                        break;
                    case "插入数据_id自动生成":
                        insert1();
                        break;
                    case "更新数据":
                        update();
                        break;
                    case "删除数据":
                        delete();
                        break;
                    case "查询":
                        query();
                        break;
                    default:


                }
            }
        };
        actionInit(datas, callBack);


    }

    public void initSqlLiteOpenHelper() {
        dbHelper = new SQLiteOpenHelperBaseUse(this, "person_sql", null, 1);
        random = new Random();
    }

    /**
     * 创建 or 打开数据库
     */
    public void openSqlLite() {

        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void insert() {
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("name", "sun");
        sqLiteDatabase.insert("person", null, values);

        String sql = "insert into person(id,name) values(2,'sunyi')";
        sqLiteDatabase.execSQL(sql);
    }

    public void insert1() {
        ContentValues values = new ContentValues();
        values.put("name", "sun" + random.nextInt());
        values.put("address", "地址：" + random.nextInt());
        sqLiteDatabase.insert("person",null,values);
    }

    public void update() {
        ContentValues values = new ContentValues();
        values.put("name", "sunyue");
        sqLiteDatabase.update("person", values, "id = ?", new String[]{"1"});
    }

    public void delete() {
        sqLiteDatabase.delete("person", "id=?", new String[]{"1"});
    }

    public void query() {
        Cursor c = sqLiteDatabase.rawQuery("select * from person where id =?", new String[]{"2"});
        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndex("name"));
            L.e(TAG, "name:" + name);
        }
        c.close();
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
