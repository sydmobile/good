package com.syd.good.feature.customview.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import butterknife.OnClick;

/**
 * <p>
 * date: 2021/5/25 10:23
 *
 * @author syd
 * @version 1.0
 */
public class CounterView extends View implements View.OnClickListener {

    private Paint mPaint;
    private Rect mBounds;
    private int mCount;

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        // 获得文字的宽度和高度
        mPaint.getTextBounds(text,0,text.length(),mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        // 绘制文字的时候 y 指的是文字的下坐标
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
