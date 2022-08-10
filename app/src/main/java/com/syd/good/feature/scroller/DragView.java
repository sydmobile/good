package com.syd.good.feature.scroller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.syd.good.R;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/10/22 09:00
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DragView extends View {
    private Paint mPaint;
    private static final String TAG = "DragView";
    float lastX, lastY;
    int test;
    ViewGroup mViewGroup;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = getResources().obtainAttributes(attrs, R.styleable.DragView);
        test = array.getInteger(R.styleable.DragView_test, 888);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.RED);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewGroup = (ViewGroup) getParent();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                Log.e(TAG, "lastX:" + lastX);
                Log.e(TAG, "lastY:" + lastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) (event.getX() - lastX);
                int offsetY = (int) (event.getY() - lastY);
                if (getRight()+offsetX>=mViewGroup.getWidth()){
                    return true;
                }
                Log.e(TAG, "offsetX:" + offsetX + "===offsetY:" + offsetY + "===x:" + event.getX() + "====y:" + event.getY());
                layout(getLeft() + offsetX, getTop(), getRight() + offsetX, getBottom());
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout"+test);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");
        canvas.drawRect(0, 0, 300, 300, mPaint);
        canvas.drawRect(0, 400, 300.23333f, 700.888f, mPaint);

    }
}
