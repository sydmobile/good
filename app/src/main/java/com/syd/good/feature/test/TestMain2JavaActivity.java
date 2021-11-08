package com.syd.good.feature.test;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.R;
import com.syd.good.feature.customview.TestView;
import com.syd.good.utils.ScreenUtil;
import com.syd.good.utils.UnitConvertUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 * date: 2021/6/29 17:16
 *
 * @author syd
 * @version 1.0
 */
public class TestMain2JavaActivity extends AppCompatActivity {
    TestView mTestView;
    private static final String TAG = "TestMain2JavaActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test_activity_main);
        setContentView(R.layout.test_activity_ecg1);
        mTestView = findViewById(R.id.test_view);
//        float density = ScreenUtil.getDensity();
//        Log.e("density", "=" + density+"=="+ScreenUtil.getWidthPixels()+"xx"+ScreenUtil.getXdpi()+"=="+ScreenUtil.getSMDP());
//        Log.e("xx", UnitConvertUtil.dp2pxByDensity(this,1)+"px");
//        TextView textView = findViewById(R.id.tv);
//        textView.setOnClickListener(v -> Log.e("xx",textView.getText().toString()));

//        getWindow().addFlags();
//        getWindow().getDecorView().setSystemUiVisibility();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e(TAG,"执行啦");
                mTestView.postInvalidate();
            }
        },1000,100);
    }
}
