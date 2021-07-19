package com.syd.good.ryg.a3customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <p>
 * date: 2021/6/29 11:21
 *
 * @author syd
 * @version 1.0
 */
public class BaseDrawView extends View {
    public BaseDrawView(Context context) {
        super(context);
    }

    public BaseDrawView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
