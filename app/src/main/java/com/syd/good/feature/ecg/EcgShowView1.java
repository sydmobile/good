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
 *     time   : 2021/9/7 15:21
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@SuppressWarnings("ALL")
public class EcgShowView1 extends View {
    private int mShowModel = 0;
    public static final int SHOW_MODEL_ALL = 0;
    public static final int SHOW_MODEL_SCROLL = 1;
    public static final int SHOW_MODEL_REFRESH = 2;
    public static final String GRID_COLOR = "#D8D8D8";
    public static final String ECG_COLOR = "#31CE22";

    /**
     * View 宽度
     */
    private float mWidth;
    private float mHeight;

    /**
     * 画笔、路径
     */
    private Paint mPaint;
    private Path mPath;
    private List<String> mDataStrList;

    private int scrollIndex = 0;
    private Timer mTimer;
    private TimerTask mTimerTask;
    /**
     * x 轴数量
     */
    private static final int INTERVAL_SCROLL_REFRESH = 8;


    private List<Float> mRefreshList;
    private int mShowIndex;

    private static final float MAX_VALUE = 20f;
    /**
     * 心电图点数
     */
    private int mIntervalNumHeart = 1;
    /**
     * 心电图 x 轴间隔
     */
    private float mIntervalRowHeart = 0f;
    /**
     * 心电图 y 轴间隔
     */
    private float mIntervalColumnHeart = 0f;

    /**
     * 心电图线宽度
     */
    private static final float HEART_LINE_STROKE_WIDTH = 2.5f;
    private static final float GRID_LINE_STROKE_WIDTH = 1.5f;
    private static final float GRID_WIDTH_HEIGHT = 5f;
    /**
     * 方格行数
     */
    private int row;
    /**
     * 方格行之间的间距
     */
    private float intervalRow;
    /**
     * 方格列数
     */
    private int column;
    /**
     * 方格列之间的间距
     */
    private float intervalColumn;

    /**
     * 用来一次显示的数据
     */
    private float[] mFloatArray;


    public EcgShowView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        // 计算出有多少列
        column = (int) (mWidth / GRID_WIDTH_HEIGHT);
        intervalColumn = mWidth / column;

        // 计算出有多少行
        row = (int) (mHeight / GRID_WIDTH_HEIGHT);
        intervalRow = mHeight / row;
        initData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        switch (mShowModel) {
            case SHOW_MODEL_ALL:
                drawHeartAll(canvas);
                break;
            case SHOW_MODEL_SCROLL:
                drawHeartScroll(canvas);
                break;
            case SHOW_MODEL_REFRESH:
                drawHeartRefresh(canvas);
                break;
            default:

        }
    }

    public void setData(String string, int showModel) {
        if (string != null) {
            mDataStrList = Arrays.asList(string.split(",").clone());
        }
        this.mShowModel = showModel;
        initData();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
    }

    private void initData() {
        if (mDataStrList == null) {
            return;
        }

        int dataLenght;

        switch (mShowModel) {
            case SHOW_MODEL_ALL:
                dataLenght = mDataStrList.size();

                if (dataLenght > mWidth) {
                    dataLenght = (int) mWidth;
                }
                mFloatArray = new float[dataLenght];

                for (int i = 0; i < dataLenght; i++) {
                    mFloatArray[i] = Float.parseFloat(mDataStrList.get(i));
                }

                mIntervalNumHeart = mFloatArray.length;
                mIntervalRowHeart = mWidth / mIntervalNumHeart;
                mIntervalColumnHeart = mHeight / MAX_VALUE / 2;

                break;
            case SHOW_MODEL_SCROLL:
                dataLenght = mDataStrList.size();
                mFloatArray = new float[dataLenght];

                for (int i = 0; i < dataLenght; i++) {
                    mFloatArray[i] = Float.parseFloat(mDataStrList.get(i));
                }
                mIntervalRowHeart = mWidth / INTERVAL_SCROLL_REFRESH;
                mIntervalNumHeart = (int) (mWidth / mIntervalRowHeart);
                mIntervalColumnHeart = mHeight / MAX_VALUE / 2;
                startScrollTimer();
                break;
            case SHOW_MODEL_REFRESH:
                mIntervalRowHeart = mWidth / INTERVAL_SCROLL_REFRESH;
                mIntervalNumHeart = (int) (mWidth / mIntervalRowHeart);
                mIntervalColumnHeart = mHeight / MAX_VALUE / 2;
                break;
            default:
        }
    }

    @SuppressWarnings("AlibabaAvoidUseTimer")
    private void startScrollTimer() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (scrollIndex < mFloatArray.length) {
                    scrollIndex++;
                } else {
                    scrollIndex = 0;
                }
            }
        };
        mTimer.schedule(mTimerTask, 0, 50);
    }

    /**
     * 绘制表格
     *
     * @param canvas 画布
     */
    private void drawGrid(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(GRID_COLOR));
        mPaint.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        for (int i = 0; i <= column; i++) {
            float tempI = i * intervalColumn;
            mPath.moveTo(tempI, 0);
            mPath.lineTo(tempI, mHeight);
        }

        for (int i = 0; i <= row; i++) {
            mPath.moveTo(0, i * intervalRow);
            mPath.lineTo(mWidth, i * intervalRow);
        }
        canvas.drawPath(mPath, mPaint);

    }

    /**
     * 将数据全部绘制
     *
     * @param canvas 画布
     */
    private void drawHeartAll(Canvas canvas) {
        if (mFloatArray == null || mFloatArray.length == 0) {
            return;
        }
        mPaint.reset();
        mPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(ECG_COLOR));
        mPaint.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        mPath.moveTo(0f, mHeight / 2);

        // 坐标点
        float nowX;
        float nowY;

        for (int i = 0; i < mFloatArray.length; i++) {
            nowX = i * mIntervalRowHeart;
            float dataValue = mFloatArray[i];
            if (dataValue > 0) {
                if (dataValue > MAX_VALUE) {
                    dataValue = MAX_VALUE;
                }
            } else {
                if (dataValue < -MAX_VALUE) {
                    dataValue = -MAX_VALUE;
                }
            }
            nowY = mHeight / 2 - dataValue * mIntervalColumnHeart;
            mPath.lineTo(nowX, nowY);
        }
        canvas.drawPath(mPath, mPaint);

    }

    private void drawHeartScroll(Canvas canvas) {
        if (mFloatArray == null || mFloatArray.length == 0) {
            return;
        }
        mPaint.reset();
        mPath.reset();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(HEART_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor(ECG_COLOR));
        mPath.moveTo(0f, mHeight / 2);

        int scrollEndIndex = scrollIndex;
        int scrollStartIndex = scrollEndIndex - mIntervalNumHeart;
        if (scrollStartIndex < 0) {
            scrollStartIndex = 0;
        }

        float nowX;
        float nowY;

        for (int i = scrollStartIndex; i <= scrollEndIndex; i++) {
            nowX = (i - scrollStartIndex) * mIntervalRowHeart;

            float dataValue = mFloatArray[i];
            if (dataValue > 0) {
                if (dataValue > MAX_VALUE * 0.8f) {
                    dataValue = MAX_VALUE * 0.8f;
                }
            } else {
                if (dataValue < -MAX_VALUE * 0.8f) {
                    dataValue = -(MAX_VALUE * 0.8f);
                }
            }
            nowY = mHeight / 2 - dataValue * mIntervalColumnHeart;
            mPath.lineTo(nowX, nowY);
        }

        canvas.drawPath(mPath, mPaint);
        postInvalidate();
    }

    private void drawHeartRefresh(Canvas canvas) {
        mPaint.reset();
        mPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(ECG_COLOR));
        mPaint.setStrokeWidth(HEART_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);
        mPath.moveTo(0f, mHeight / 2);

        // 数据不存在
        if (mRefreshList == null || mRefreshList.size() == 0) {
            return;
        }
        int refreshListSize = mRefreshList.size();
        // i 表示本次绘制数据的下标
        for (int i = 0; i < mIntervalNumHeart; i++) {
            // 说明 总数据量很小，不够一次刷新长度
            if (i > refreshListSize - 1) {
                break;
            }
            if (mFloatArray == null || mFloatArray.length == 0) {
                break;
            }
            // 说明当前数据源的量很小
            if (refreshListSize <= mIntervalNumHeart) {
                this.mFloatArray[i] = mRefreshList.get(i);
            } else {
                int times = (refreshListSize - 1) / mIntervalNumHeart;
                int temp = times * mIntervalNumHeart + i;
                if (temp < refreshListSize) {
                    this.mFloatArray[i] = this.mRefreshList.get(temp);
                }
            }
        }
//        logdata();

        float nowX;
        float nowY;
        mShowIndex = refreshListSize < mIntervalNumHeart ? refreshListSize - 1 : (refreshListSize - 1) % mIntervalNumHeart;
        for (int i = 0; i < mFloatArray.length; i++) {
            nowX = i * mIntervalRowHeart;
            float dataValue = mFloatArray[i];
            if (dataValue > 0) {
                if (dataValue > MAX_VALUE * 0.8f) {
                    dataValue = MAX_VALUE * 0.8f;
                }
            } else {
                if (dataValue < -MAX_VALUE * 0.8f) {
                    dataValue = -(MAX_VALUE * 0.8f);
                }
            }
            nowY = mHeight / 2 - dataValue * mIntervalColumnHeart;

            if (i - 1 == mShowIndex) {
                mPath.moveTo(nowX,nowY);
            }else {
                mPath.lineTo(nowX,nowY);
            }
        }
        canvas.drawPath(mPath, mPaint);

    }

    public void showLine(float point) {
        if (mRefreshList == null) {
            mRefreshList = new ArrayList<>();
            mFloatArray = new float[mIntervalNumHeart];
        }
        mRefreshList.add(point);
        postInvalidate();
    }
}
