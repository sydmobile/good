package com.syd.good.rwx.practicedraw1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
public class PracticePathView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();

    public PracticePathView(Context context) {
        super(context);
    }

    public PracticePathView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
//        mPath.addArc(200,200,400,400,0,-180);
//        mPath.arcTo(400,200,600,400,0,-180,true);
//        mPath.moveTo(600,300);
//        mPath.lineTo(400,500);
//        mPath.lineTo(200,300);
        mPath.lineTo(100,100);
        mPath.lineTo(110,150);
        canvas.drawPath(mPath,mPaint);
    }
}
