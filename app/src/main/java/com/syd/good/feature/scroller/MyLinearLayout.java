package com.syd.good.feature.scroller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.syd.good.R;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/10/22 10:00
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyLinearLayout extends LinearLayout {
    private static final String TAG = "MyLinearLayout";
    int test;
    public MyLinearLayout(Context context) {
        this(context,null);

    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getResources().obtainAttributes(attrs, R.styleable.DragView);
        test = a.getInteger(R.styleable.DragView_test,999);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG,"onLayout"+test);
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure"+test);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw"+test);
    }
}
