package com.syd.good.rwx.practicedraw1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.syd.good.R;

/**
 * <p>
 * date: 2021/7/1 11:10
 *
 * @author syd
 * @version 1.0
 */
public class PracticeImageView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    Rect mRect;
    Rect mDRect;

    public PracticeImageView(Context context) {
        super(context);
    }

    public PracticeImageView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img150x100);
        mRect = new Rect(0,0,100,100);
        mDRect = new Rect(400,150,600,500);
        Log.e("bitmap",mBitmap.getHeight()+"==="+mBitmap.getWidth()+"==");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(mBitmap,20,20,mPaint);
        canvas.drawBitmap(mBitmap,mRect,mDRect,mPaint);
    }
}
