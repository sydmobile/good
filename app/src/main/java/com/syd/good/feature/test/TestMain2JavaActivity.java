package com.syd.good.feature.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syd.good.R;
import com.syd.good.utils.ScreenUtil;
import com.syd.good.utils.UnitConvertUtil;

/**
 * <p>
 * date: 2021/6/29 17:16
 *
 * @author syd
 * @version 1.0
 */
public class TestMain2JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        float density = ScreenUtil.getDensity();
        Log.e("density", "=" + density+"=="+ScreenUtil.getWidthPixels()+"xx"+ScreenUtil.getXdpi()+"=="+ScreenUtil.getSMDP());
        Log.e("xx", UnitConvertUtil.dp2pxByDensity(this,1)+"px");
        TextView textView = findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("xx",textView.getText().toString());
            }
        });
    }
}
