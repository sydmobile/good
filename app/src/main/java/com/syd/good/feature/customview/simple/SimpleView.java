package com.syd.good.feature.customview.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <p>
 * date: 2021/5/24 17:05
 *
 * @author syd
 * @version 1.0
 */
public class SimpleView extends View {
    private Paint mPaint;

    public SimpleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Log.e("simple","simple");
    }

    @Override
    protected void onDraw(Canvas canvas) {
       mPaint.setColor(Color.YELLOW);
       canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
       mPaint.setColor(Color.RED);
       mPaint.setTextSize(20);
       String text = "Hello View";
       canvas.drawText(text,0,getHeight()/2,mPaint);
    }
}
