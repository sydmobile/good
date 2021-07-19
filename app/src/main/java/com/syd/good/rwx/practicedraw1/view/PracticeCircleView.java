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
public class PracticeCircleView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PracticeCircleView(Context context) {
        super(context);
    }

    public PracticeCircleView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);

        canvas.drawCircle(200,200,50,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200,350,50,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        canvas.drawCircle(350,200,50,mPaint);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(350,350,50,mPaint);
    }
}
