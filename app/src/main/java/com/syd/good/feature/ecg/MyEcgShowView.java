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
import java.util.List;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/9/2 16:10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "FieldCanBeLocal"})
public class MyEcgShowView extends View {
    /** 心电图绘制线宽度 */
    private final float HEART_LINE_STROKE_WIDTH = 5f;
    /** 小网格绘制线宽度 */
    private final float GRID_LINE_STROKE_WIDTH = 2f;
    /** 网络格大小 */
    private final int  GRID_WIDTH_AND_HEIGHT = 20;
    /** 心电图绘制颜色 */
    private final String HEART_LINE_COLOR = "#28308C";
    /** 心电图大方格颜色 */
    private final String GRID_BIG_LINE_COLOR = "#FF0000";
    /** 心电图网格颜色 */
    private final String GRID_LINE_COLOR = "#909CA1";
    /** View 宽度 */
    private float mWidth;
    /** View 高度 */
    private float mHeight;
    private Paint mPaint;
    private Path mPath;
    /** 数据源 */
    private List<List<Float>> dataSourceList;
    /** 一屏显示的数据 */
    private List<Float>[] dataArray;
    private int mSmallGridXNum = 0;
    private int mBigGridXNum = 0;
    private int mSmallGridYNum = 0;
    private int mBigGridYNum = 0;

    public MyEcgShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        initData();

    }

    /**
     * 绘制表格
     *
     * @param canvas 画布
     */
    private void drawGrid(Canvas canvas) {
        mPaint.reset();
        mPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        // 绘制竖线
        for (int i = 0; i <= mSmallGridXNum; i++) {
            mPath.reset();
            if (i % 5 == 0) {
                mPaint.setColor(Color.parseColor(GRID_BIG_LINE_COLOR));
            } else {
                mPaint.setColor(Color.parseColor(GRID_LINE_COLOR));
            }
            float temp = i * GRID_WIDTH_AND_HEIGHT;
            mPath.moveTo(temp, 0f);
            mPath.lineTo(temp, mHeight);
            canvas.drawPath(mPath, mPaint);
        }
        // 绘制横线
        for (int i = 0; i <= mSmallGridYNum; i++) {
            mPath.reset();
            if (i % 5 == 0) {
            mPaint.setColor(Color.parseColor(GRID_BIG_LINE_COLOR));
            } else {
                mPaint.setColor(Color.parseColor(GRID_LINE_COLOR));
            }
            float temp = i * GRID_WIDTH_AND_HEIGHT;
            mPath.moveTo(0, temp);
            mPath.lineTo(mWidth, temp);
            canvas.drawPath(mPath, mPaint);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        drawEcgLine(canvas);
    }

    public void drawEcg(List<Float> data){
        if (dataSourceList == null){
            dataSourceList = new ArrayList<>();
            dataArray = new List[mBigGridXNum/2];
        }
        dataSourceList.add(data);
        postInvalidate();
    }

    /**
     * 绘制 ECG
     *
     * @param canvas canvas
     */
    public void drawEcgLine(Canvas canvas) {
        mPaint.reset();
        mPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(HEART_LINE_COLOR));
        mPaint.setStrokeWidth(HEART_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        mPath.moveTo(0f, mHeight / 2);

        if (dataSourceList == null || dataSourceList.size() == 0) {
            return;
        }
        if (dataArray == null || dataArray.length == 0){
            return;
        }
        int dataSourceListSize = dataSourceList.size();
        // 当前要绘制的改变的新数据
        int showIndex = dataSourceListSize < mBigGridXNum ? dataSourceListSize - 1 : (dataSourceListSize - 1) % mBigGridXNum;

        for (int i = 0; i < mBigGridXNum/2; i++) {
            if (i > dataSourceListSize - 1){
                break;
            }

            if (dataSourceListSize <= mBigGridXNum/2){
                this.dataArray[i] = dataSourceList.get(i);
            }else {
                int times = (dataSourceListSize -  1)/(mBigGridXNum/2);
                int index = times * (mBigGridXNum/2) + i ;
                if (index < dataSourceListSize){
                    this.dataArray[i] = dataSourceList.get(index);
                }
            }
        }
        float nowX,nowY;
        for (int i = 0 ;i<dataArray.length;i++){
            // 当前数据
            List<Float> dataValue = dataArray[i];
            if (dataValue == null || dataValue.size() == 0){
                break;
            }

            int nowX1 = (i)*GRID_WIDTH_AND_HEIGHT*10;
            mPath.moveTo(nowX1,mHeight / 2);
            if (dataValue.size()< GRID_WIDTH_AND_HEIGHT*5){
                int smallX =   GRID_WIDTH_AND_HEIGHT*5 / dataValue.size();
                for (int i1 = 0;i1< dataValue.size();i1++){
                    float dataY = dataValue.get(i1);
                    if (dataY > 0){
                        if (dataY> mSmallGridYNum/2){
                            dataY  = mHeight/2 * 0.9f;
                        }
                    }else {
                        if (dataY < -mSmallGridYNum/2){
                            dataY = - mHeight/2 * 0.9f;
                        }
                    }

                    mPath.lineTo(nowX1+(i1+1)*smallX,mHeight/2 - dataY * GRID_WIDTH_AND_HEIGHT);
//                    if (i!=0){
//                    }else {
//
//                    }
                }
            }


//            nowX = (i+1) * GRID_WIDTH_AND_HEIGHT*5;
//
//            // 先只取第一个数据
//            float dataY = dataValue.get(dataValue.size()-1);
//            if (dataY > 0){
//                if (dataY> mSmallGridYNum/2){
//                    dataY  = mHeight/2 * 0.9f;
//                }
//            }else {
//                if (dataY < -mSmallGridYNum/2){
//                    dataY = - mHeight/2 * 0.9f;
//                }
//            }
//            nowY = mHeight/2 - dataY * GRID_WIDTH_AND_HEIGHT;
//
//            if (i - 1 == showIndex){
//                mPath.moveTo(nowX,nowY);
//            }else {
//                mPath.lineTo(nowX,nowY);
//            }
        }
        canvas.drawPath(mPath,mPaint);





    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
    }

    private void initData() {
        mSmallGridXNum = (int) (mWidth / GRID_WIDTH_AND_HEIGHT);
        mBigGridXNum = mSmallGridXNum / 5;
        mSmallGridYNum = (int) (mHeight / GRID_WIDTH_AND_HEIGHT);
        mBigGridYNum = mSmallGridYNum / 5;

    }
}
