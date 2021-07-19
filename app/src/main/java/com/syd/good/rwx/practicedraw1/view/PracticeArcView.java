package com.syd.good.rwx.practicedraw1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
public class PracticeArcView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mRectF = new RectF(200,200,400,300);
    public PracticeArcView(Context context) {
        super(context);
    }

    public PracticeArcView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(mRectF,0,90,true,mPaint);
        canvas.drawArc(mRectF,-10,-60,true,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectF,95,90,false,mPaint);
    }
}
