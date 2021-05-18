package com.syd.good.carsonblog.a2eventdispatcher;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.carsonblog.a2eventdispatcher.view.MyLinearLayout;

import butterknife.BindView;

/**
 * 事件分发
 * <p>
 * date: 2021/1/28 9:43
 *
 * @author syd
 * @version 1.0
 */
public class EventDispatcherActivity extends BaseActivity {


    @BindView(R.id.bt_one)
    Button btOne;
    @BindView(R.id.bt_two)
    Button btTwo;
    @BindView(R.id.myll)
    MyLinearLayout myll;

    @Override
    protected int layoutId() {
        return R.layout.eventdispatcher_activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        testIntercept();
    }

    public void testIntercept() {
        btOne.setOnClickListener(v -> {
            Log.e("click", "我是子View1");
        });


        btTwo.setOnClickListener(v -> {
            Log.e("click", "我是子View2");
        });

        myll.setOnClickListener(v -> {
            Log.e("myll", "我自己来处理");

        });
    }
}
