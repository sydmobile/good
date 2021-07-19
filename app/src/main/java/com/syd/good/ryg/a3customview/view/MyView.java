package com.syd.good.ryg.a3customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.syd.good.R;

/**
 * <p>
 * date: 2021/6/28 10:39
 *
 * @author syd
 * @version 1.0
 */
public class MyView extends View {

    private Paint p;
    int defaultSize;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        defaultSize = a.getDimensionPixelSize(R.styleable.MyView_default_size,100);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getSize(defaultSize, widthMeasureSpec);
        int height = getSize(defaultSize, heightMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.RED);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), p);

    }

    public int getSize(int defaultSize, int measureSpec) {
        int result;
        int measureSpecMode = MeasureSpec.getMode(measureSpec);
        int measureSpecSize = MeasureSpec.getSize(measureSpec);
        switch (measureSpecMode) {
            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = defaultSize;
                break;
            default:
                result = 0;
        }
        return result;
    }
}
