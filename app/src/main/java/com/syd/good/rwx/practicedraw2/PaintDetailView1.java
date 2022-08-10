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
public class PaintDetailView1 extends View {
    private Paint mPaint;
    private BitmapShader mBitmapShaderClamp;
    private int mBitMapWidth;
    private int mBitMapHeight;
    private Bitmap mBitmap;
    private Paint mPaintCommon;

    public PaintDetailView1(Context context) {
        super(context);
    }

    public PaintDetailView1(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaintCommon = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_header);
//        mBitMapWidth = mBitmap.getWidth();
//        mBitMapHeight = mBitmap.getHeight();
//        mBitmapShaderClamp = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        mPaint.setShader(mBitmapShaderClamp);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(344,344,344,mPaint);
        canvas.drawColor(Color.RED);
        canvas.drawRect(0,0,100,100,mPaint);
        canvas.save();
        canvas.translate(100,100);
        canvas.rotate(45);
        canvas.drawRect(0,0,50,50,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(45);
//        canvas.translate(100,100);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,50,50,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(45);
        canvas.translate(100,100);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,50,50,mPaint);
        canvas.restore();

//        canvas.drawRect(0,0,mBitMapWidth,mBitMapHeight,mPaint);
    }
}
