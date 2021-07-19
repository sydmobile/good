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
public class PracticeLineView extends View {
    float[] ri = new float[]{50,50,250,50,250,50,250,350,50,50,50,350};
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PracticeLineView(Context context) {
        super(context);
    }

    public PracticeLineView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        mPaint.setStrokeWidth(6);
        canvas.drawLine(200, 200, 800, 800, mPaint);

        canvas.drawLines(ri,mPaint);
    }
}
