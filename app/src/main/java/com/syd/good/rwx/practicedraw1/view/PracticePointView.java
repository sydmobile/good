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
public class PracticePointView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float[] points = new float[]{20,200,60,200,100,200};

    public PracticePointView(Context context) {
        super(context);
    }

    public PracticePointView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(40,40,mPaint);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(100,100,mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawPoints(points,mPaint);
    }
}
