package com.syd.good.rwx.practicedraw1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <p>
 * date: 2021/7/1 11:10
 *
 * @author syd
 * @version 1.0
 */
public class PracticeHistogramView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PracticeHistogramView(Context context) {
        super(context);
    }

    public PracticeHistogramView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawLine(20,20,20,320,mPaint);
        canvas.drawLine(20,320,320,320,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(30,320,80,300,mPaint);
        canvas.drawRect(90,320,140,250,mPaint);
        canvas.drawRect(150,320,200,200,mPaint);
        canvas.drawRect(210,320,260,260,mPaint);
        canvas.drawRect(270,320,320,260,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawText("iOS",40,340,mPaint);
        canvas.drawText("Html",100,340,mPaint);
    }
}
