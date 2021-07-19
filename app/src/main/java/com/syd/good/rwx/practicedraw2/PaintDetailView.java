package com.syd.good.rwx.practicedraw2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.syd.good.R;

/**
 * <p>
 * date: 2021/7/5 10:06
 *
 * @author syd
 * @version 1.0
 */
public class PaintDetailView extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradientClamp;
    private LinearGradient mLinearGradientMirror;
    private LinearGradient mLinearGradientRepeat;
    private BitmapShader mBitmapShaderClamp;
    private int mBitMapWidth;
    private int mBitMapHeight;
    private Bitmap mBitmap;
    private Paint mPaintCommon;

    public PaintDetailView(Context context) {
        super(context);
    }

    public PaintDetailView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCommon = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinearGradientClamp = new LinearGradient(200,0,400,0, Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
        mLinearGradientMirror = new LinearGradient(200,0,400,0,Color.RED,Color.BLUE, Shader.TileMode.MIRROR);
        mLinearGradientRepeat = new LinearGradient(200,0,400,0,Color.RED,Color.BLUE, Shader.TileMode.REPEAT);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_header);
        mBitMapWidth = mBitmap.getWidth();
        mBitMapHeight = mBitmap.getHeight();
        Log.e("xx",mBitMapHeight+"=="+mBitMapWidth);
        mBitmapShaderClamp = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(1200,3000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mLinearGradientClamp);
        canvas.drawRect(0,0,1000,400,mPaint);
        mPaint.setShader(mLinearGradientMirror);
        canvas.drawRect(0,500,1000,900,mPaint);
        mPaint.setShader(mLinearGradientRepeat);
        canvas.drawRect(0,1000,1000,1400,mPaint);
        mPaint.setShader(mBitmapShaderClamp);
        canvas.drawCircle(400,2000,344,mPaint);
        canvas.drawRect(100,900,1000,1900,mPaint);
        canvas.drawBitmap(mBitmap,100,100,mPaintCommon);
    }
}
