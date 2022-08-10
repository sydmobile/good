package com.syd.good.feature.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/10/14 09:58
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestView extends View {
    Path mPath1 = new Path();
    Path mPath2 = new Path();
    Path mPath3 = new Path();
    Paint mPaint = new Paint();
    private float mHeight;
    private float mWidth;
    private float[] arr = new float[20];
    private List<Float> mFloatList = new ArrayList<>();
    float x1AxisPosition = mHeight / 6;
    float x2AxisPosition = mHeight / 6 * 3;
    float x3AxisPosition = mHeight / 6 * 5;
    int currentIndex = 0;
    private static final String TAG = "TestView";

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG,"onSizeChanged");
        mHeight = h;
        mWidth = w;
        x1AxisPosition = mHeight / 6;
        x2AxisPosition = mHeight / 6 * 3;
        x3AxisPosition = mHeight / 6 * 5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw()");
        mPath1.reset();
        mPath2.reset();
        mPath3.reset();
        mPath1.moveTo(0, x1AxisPosition);
        mPath2.moveTo(0, x2AxisPosition);
        mPath3.moveTo(0, x3AxisPosition);
        for (int i = 0; i < arr.length; i++) {
            if (currentIndex >= mFloatList.size()) {
                currentIndex = 0;
            }
            arr[i] = mFloatList.get(currentIndex);
            currentIndex++;
        }

        for (int i = 0; i < arr.length; i++) {
            float num = arr[i];
            if (num > 0) {
                mPath1.lineTo(50 * i, x1AxisPosition - num);
                mPath2.lineTo(50 * i, x2AxisPosition - num);
                mPath3.lineTo(50 * i, x3AxisPosition - num);
            } else {
                mPath1.lineTo(50 * i, x1AxisPosition + num);
                mPath2.lineTo(50 * i, x2AxisPosition + num);
                mPath3.lineTo(50 * i, x3AxisPosition + num);
            }
        }
        canvas.drawPath(mPath1, mPaint);
        canvas.drawPath(mPath2, mPaint);
        canvas.drawPath(mPath3, mPaint);
//        canvas.drawRect(100,200,200,300,mPaint);
    }

    public void init() {
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 50; i++) {
            mFloatList.add((float) (Math.random() * 100));
        }
    }
}
