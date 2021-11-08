package com.syd.good.feature.ecg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/9/2 16:10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EcgShowView extends View {
    /**
     * View 宽度
     */
    private float mWidth;
    /**
     * View 高度
     */
    private float mHeight;
    private Paint mPaint;
    private Path mPath;
    private List<String> dataStrList;
    private int intervalNumHeart;
    private float intervalRowHeart;
    private float intervalColumnHeart;
    private float[] floatArrays;
    private float mHeartLineStrokeWidth;
    private int row;
    private float intervalRow;
    private int column;
    private float intervalColumn;
    /**
     * 网络格线条
     */
    private float mGridLinesStrokeWidth;
    /**
     * 网络格宽高
     */
    private float mGridsStrokeWidthAndHeight;

    // 心电
    private final float MAX_VALUE = 20f;
    private final float HEART_LINE_STROKE_WIDTH = 5f;

    // 网格
    private final float GRID_LINE_STROKE_WIDTH = 2f;
    private final float GRID_WIDTH_AND_HEIGHT = 10f;
    private Timer timer;
    private int scrollIndex;
    private boolean isInva = false;

    private List<Float> refreshList;
    private int showIndex;
    private List<Float> data = new ArrayList<>();

    public EcgShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
        // 网络格 线条
        mGridLinesStrokeWidth = GRID_LINE_STROKE_WIDTH;
        mGridsStrokeWidthAndHeight = GRID_WIDTH_AND_HEIGHT;
        column = (int) (mWidth / mGridsStrokeWidthAndHeight);

        intervalColumn = mWidth / column;
        row = (int) (mHeight / mGridsStrokeWidthAndHeight);
        intervalRow = mHeight / row;

        mHeartLineStrokeWidth = HEART_LINE_STROKE_WIDTH;
        initData();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            // 绘制网格
            mPath.reset();
            mPaint.reset();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.parseColor("#D8D8D8"));
            mPaint.setStrokeWidth(mGridLinesStrokeWidth);
            mPaint.setAntiAlias(true);

            // 绘制竖线
            for (int i = 0; i < column; i++) {
                float iTempC = i * intervalColumn;
                mPath.moveTo(iTempC, 0f);
                mPath.lineTo(iTempC, mHeight);
            }

            // 绘制横线
//            for (int i = 0; i < row; i++) {
//                mPath.moveTo(0f, i * intervalRow);
//                mPath.lineTo(mWidth, i * intervalRow);
//            }

            canvas.drawPath(mPath, mPaint);

            // 绘制心电图
            if (floatArrays == null || floatArrays.length == 0) {
                return;
            }
            drawHeartScroll(canvas);

    }

    public  void startScrollTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (scrollIndex < dataStrList.size()) {
                    scrollIndex++;
                } else {
                    scrollIndex = 0;
                }
                postInvalidate();
            }
        };
        timer.schedule(task, 0, 100);
    }

    private void drawHeartScroll(Canvas canvas) {
        if (dataStrList == null || dataStrList.size() == 0) {
            return;
        }
        mPaint.reset();
        mPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#31CE32"));
        mPaint.setStrokeWidth(mGridLinesStrokeWidth);
        mPaint.setAntiAlias(true);
        mPath.moveTo(0f, mHeight / 2);

        int scrollStartIndex;
        int scrollEndIndex ;

        scrollEndIndex = scrollIndex;

        scrollStartIndex = scrollEndIndex - intervalNumHeart;
        if (scrollStartIndex < 0) {
            scrollStartIndex = 0;
        }

        float nowX;
        float nowY;

        for (int i = scrollStartIndex; i < scrollEndIndex; i++) {
            nowX = (i - scrollStartIndex) * intervalRowHeart;

            float dataValue = floatArrays[i];
            if (dataValue > 0) {
                if (dataValue > MAX_VALUE * 0.8f) {
                    dataValue = MAX_VALUE * 0.8f;
                }
            } else {
                if (dataValue < -MAX_VALUE * 0.8f) {
                    dataValue = -(MAX_VALUE * 0.8f);
                }
            }
            nowY = mHeight/2 - dataValue* intervalColumnHeart;
            mPath.lineTo(nowX,nowY);

        }
        canvas.drawPath(mPath,mPaint);
//        postInvalidate();
    }

        public void setData (String dataStr){
            dataStrList = Arrays.asList(dataStr.split(",").clone());
            initData();
        }

        private void init () {
            mPaint = new Paint();
            mPath = new Path();

        }


        private void drawHeartRefresh(Canvas canvas){
            mPaint.reset();
            mPath.reset();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.parseColor("#31CE32"));
            mPaint.setStrokeWidth(mGridLinesStrokeWidth);
            mPaint.setAntiAlias(true);
            mPath.moveTo(0f,mHeight/2);
            int nowIndex = refreshList == null?0:refreshList.size();
             if (nowIndex == 0){
                 return;
             }
             if (nowIndex < intervalNumHeart){
                 showIndex = nowIndex - 1;
             }else {
                 showIndex = (nowIndex - 1) % intervalNumHeart;
             }

             for (int i=0;i<intervalNumHeart;i++){
                 if (i > refreshList.size() - 1){
                     break;
                 }
                 if (nowIndex <= intervalNumHeart){
                     data.set(i,refreshList.get(i));
                 }else {
                     int times = (nowIndex - 1)/intervalNumHeart;
                     int temp = times * intervalNumHeart + i;
                     data.set(i,refreshList.get(temp));
                 }
             }



        }

        private void initData () {
            int dataLength = dataStrList.size();
            if (dataLength > mWidth) {
                dataLength = (int) mWidth;
            }
            floatArrays = new float[dataLength];
            for (int i = 0; i < dataLength; i++) {
                floatArrays[i] = Float.parseFloat(dataStrList.get(i));
            }
            intervalNumHeart = floatArrays.length;
            intervalRowHeart = mWidth / intervalNumHeart;
            intervalColumnHeart = mHeight / (MAX_VALUE * 2);

        }
    }
