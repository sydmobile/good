package com.syd.good.surfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/12/8 17:03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SurfaceViewTest extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private boolean isDrawing;
    private Paint mPaint;
    private Paint mPaint2;
    int i;
    int w, h;

    public SurfaceViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.YELLOW);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                w = (int) event.getX();
                h = (int) event.getY();
                ThreadPoolManager.getInstance().getFixedThreadPool()
                        .execute(this);

                break;
        }
        return true;
    }

    private static final String TAG = "SurfaceViewTest";

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 创建 surface
        Log.e(TAG, "created");
        isDrawing = true;
        // 开启子线程

//        ThreadPoolManager.getInstance().getScheduledThreadPool()
//                .scheduleAtFixedRate(this, 100, 5000, TimeUnit.MILLISECONDS);
//        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged");
//        Canvas canvas = holder.lockCanvas();
//        canvas.drawColor(Color.RED);
//        holder.unlockCanvasAndPost(canvas);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
        Log.e(TAG, "destroy");
        ThreadPoolManager.getInstance().getScheduledThreadPool().shutdown();
    }

    @Override
    public void run() {
//        while (isDrawing){
        drawSomething();
//        }


    }

    //绘图逻辑
    private void drawSomething() {
        try {
            //获得canvas对象
           mCanvas = mSurfaceHolder.lockCanvas(null);
//            mCanvas = mSurfaceHolder.lockCanvas(new Rect(w, h, w+100, h+100));
            Log.e(TAG,"start");
//            mCanvas = mSurfaceHolder.lockCanvas();
//            mCanvas.drawColor(Color.RED);
//            if (i == 0){
//            }
//            i += 10;
////            i = 20;
//            if (i == 10){
////                mCanvas.drawColor(Color.RED);
//                Log.e(TAG,"i= 10");
//                mCanvas.drawRect(80,10,100,100,mPaint2);
//                mCanvas.drawText("xxxxx", 10, 10, mPaint2);
//                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
//                mCanvas  = mSurfaceHolder.lockCanvas();
//                mCanvas.drawRect(80,10,100,100,mPaint2);
//                mCanvas.drawText("xxxxx", 10, 10, mPaint2);
//            }
//            else if (i == 20){
//                Log.e(TAG,"i= 20");
////                mCanvas.drawColor(Color.YELLOW);
//                mCanvas.drawOval(80,10,100,100,mPaint2);
//                mCanvas.drawText("xxxxx", 10, 10, mPaint2);
//                mCanvas.drawText("xxxxx", 10, 20, mPaint2);
//            }else if (i == 30){
//                mCanvas.drawCircle(90,50,10,mPaint);
//            }else if (i==40){
//                mPaint.setColor(Color.RED);
//                mCanvas.drawCircle(90,50,13,mPaint);
//            }
//            mCanvas.drawText("xxxxx", 10, i, mPaint2);
//            mCanvas.drawCircle(w,h,20,mPaint2);
            mCanvas.drawRect(w, h, w + 20, h + 20, mPaint2);
//            mCanvas.drawLine(0,i,100,i,mPaint2);
            Log.e(TAG, "run==" + i);

            //绘图
        } catch (Exception e) {
            Log.e(TAG,"====");
            e.printStackTrace();
            Log.e(TAG,e.getMessage()+"====");
        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }


}
