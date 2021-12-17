package com.syd.good.feature.ecg.my;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/10/11 16:59
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class BaseEcgView extends SurfaceView {
    private static final String TAG = "BaseEcgView";
    /** 心电图绘制线宽度 */
    protected final int HEART_LINE_STROKE_WIDTH = 2;
    /** 小网格绘制线宽度 */
    protected final int GRID_LINE_STROKE_WIDTH = 2;
    protected final int SMALL_GRID_LINE_STROKE_WIDTH = 1;
    /** 心电图绘制颜色 */
    protected final String HEART_LINE_COLOR = "#28308C";
//    protected final String HEART_LINE_COLOR = "#FFFF00";
    /** 心电图大方格颜色 */
    protected final String GRID_BIG_LINE_COLOR = "#FF0000";
//    protected final String GRID_BIG_LINE_COLOR = "#1b4200";
    /** 心电图网络格颜色 */
    protected final String GRID_LINE_COLOR = "#909CA1";
    protected final int BACKGROUND_COLOR = Color.WHITE;
    /** 采样频率（一个包的时间） */
    protected final static int rate = 40;

    /** 网络格大小 默认 1mm 大小的像素值 */
    protected int GRID_WIDTH_AND_HEIGHT;
    /** View 宽度 */
    protected float mWidth;
    /** View 高度 */
    protected float mHeight;


    /** X 轴上小方格数量 */
    protected int mSmallGridNumX = 0;
    /** Y 轴上小方格数量 */
    protected int mSmallGridNumY = 0;
    /** 一行可以容纳的最大个数 */
    protected int mDataNum = 0;
    /** 数组中的位置 */
    protected int currentArrayIndex;

    /** 一屏显示的数据 */
    protected List<EcgPointBean>[] mDataArray;

    /** 数据源 */
    public static ArrayBlockingQueue<List<EcgPointBean>> gQueue = new ArrayBlockingQueue<>(10);
    /** 保存截屏数据 */
    public static LimitedQueue<List<EcgPointBean>> gCacheData = new LimitedQueue<>(600);
    /** 当前每个小格代表的时间，代表走纸速度 25mm/s */
    public float currentMsPerMM = 40;
    /** 当前一个小格代表 0.1 mV */
    public float currentMvPerMM = 0.1f;
    /** 截屏后持续时间 */
    public int duration;
    /** 当前Ecg 状态 */
    protected FreezeState mFreezeState = FreezeState.playing;
    /** 描述 Ecg 的配置信息 */
    protected EcgConfigBean mEcgConfigBean = new EcgConfigBean();

    /** 画笔 */
    protected Paint mPaintSmallGrid;
    protected Paint mPaintBigGrid;
    protected Paint mPaintEcgLine;
    /** 绘制路径 */
    protected Path mPath;
    /** 一次绘制的像素 */
    protected float mDrawPx;

    public BaseEcgView(Context context) {
        this(context, null);
    }

    public BaseEcgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 定义速度
     */
    public enum Speed {
        /** 速度 25mm/s */
        SPEED25(40),
        /** 速度 50 mm/s */
        SPEED50(20),
        /** 速度 12.5 mm/s */
        SPEED12_5(80);
        /** 每毫米代表的时间 单位 ms */
        public float timePerMM;

        Speed(float i) {
            timePerMM = i;
        }
    }

    public enum Gain {
        /** Y 轴 */
        GRID0_1(0.1f),
        /** Y */
        GRID0_5(0.5f),
        /** Y */
        GRID1(1);
        public float mv;

        Gain(float i) {
            mv = i;
        }
    }

    public enum FreezeState {
        /** 冻结状态 */
        frozen,
        /** 播放状态 */
        playing
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        initData();
    }

    /**
     * 设置走纸速度
     *
     * @param speed 走纸速度
     */
    public void setCurrentSpeed(Speed speed) {
        this.currentMsPerMM = speed.timePerMM;
        mEcgConfigBean.setMsPerMM(currentMsPerMM);
        initData();
        mDataArray = new List[mDataNum];
        currentArrayIndex = 0;
//        postInvalidate();

    }

    public void setCurrentMv(Gain gain) {
        this.currentMvPerMM = gain.mv;
        mEcgConfigBean.setVmPerMM(currentMvPerMM);
        initData();
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    protected void init() {
        GRID_WIDTH_AND_HEIGHT = 5;
        initPaint();
        mPath = new Path();
        currentMsPerMM = Speed.SPEED25.timePerMM;

        currentMvPerMM = Gain.GRID0_1.mv;
        int duration = 10;
        this.duration = duration;
        mEcgConfigBean.setMsPerMM(currentMsPerMM);
        mEcgConfigBean.setRate(rate);
        mEcgConfigBean.setVmPerMM(currentMvPerMM);
        mEcgConfigBean.setDuration(duration);
    }

    private void initPaint() {

        mPaintSmallGrid = new Paint();
        mPaintSmallGrid.reset();
        mPaintSmallGrid.setStyle(Paint.Style.STROKE);
        mPaintSmallGrid.setStrokeWidth(SMALL_GRID_LINE_STROKE_WIDTH);
        mPaintSmallGrid.setColor(Color.parseColor(GRID_LINE_COLOR));
        mPaintSmallGrid.setAntiAlias(true);

        mPaintBigGrid = new Paint();
        mPaintBigGrid.setStyle(Paint.Style.STROKE);
        mPaintBigGrid.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaintBigGrid.setColor(Color.parseColor(GRID_BIG_LINE_COLOR));
        mPaintBigGrid.setAntiAlias(true);

        mPaintEcgLine = new Paint();
        mPaintEcgLine.reset();
        mPaintEcgLine.setStyle(Paint.Style.STROKE);
        mPaintEcgLine.setColor(Color.parseColor(HEART_LINE_COLOR));
        mPaintEcgLine.setStrokeWidth(HEART_LINE_STROKE_WIDTH);
        mPaintEcgLine.setAntiAlias(true);


    }

    protected void initData() {
        mSmallGridNumX = (int) (mWidth / GRID_WIDTH_AND_HEIGHT);
        mSmallGridNumY = (int) (mHeight / GRID_WIDTH_AND_HEIGHT);
        // 数据 按照包来
        mDataNum = (int) (mSmallGridNumX * currentMsPerMM) / rate;
        // 保证只初始化一次
        if (mDataNum != 0 && mDataArray == null) {
            mDataArray = new List[mDataNum];
        }
        // 一次绘制的像素
        mDrawPx = (rate / currentMsPerMM) * GRID_WIDTH_AND_HEIGHT;
        Log.e(TAG, "mDrawPx:" + mDrawPx);
        Log.e(TAG, "mWidth:" + mWidth);
        Log.e(TAG, "mHeight:" + mHeight);
        Log.e(TAG, "mSmallGridNumX" + mSmallGridNumX);
        Log.e(TAG, "mSmallGridNumY" + mSmallGridNumY);
        Log.e(TAG, "mDataNum" + mDataNum);
    }

    public void refreshEcgView() {
        drawSomething();
    }

    public void changeFreezeState(FreezeState state) {
        this.mFreezeState = state;
    }


    public abstract void setTrackInfo(String... info);

    public abstract EcgConfigBean getEcgConfigBean();


    public static void addData(List<EcgPointBean> d) {
        if (gQueue == null || gCacheData == null) {
            return;
        }
        gCacheData.offer(d);
        gQueue.offer(d);
    }


    public FreezeState getFreezeState() {
        return mFreezeState;
    }


    /**
     * 绘制表格
     *
     * @param canvas canvas
     */
    protected abstract void drawGrid(Canvas canvas);


    protected abstract void drawBigGrid(Canvas canvas);


    protected abstract void drawEcgLine(Canvas canvas);

    protected abstract void drawSomething();


    public boolean checkData() {

        return mDataArray != null && mDataArray.length != 0;
    }


    /**
     * 获取截取的数据
     *
     * @param time 截取时间范围内 单位 ms
     * @return 数据
     */
    public static List<List<EcgPointBean>> getSnapshotData(int time) {
        Object[] catchData = gCacheData.toArray();
        List<List<EcgPointBean>> c = new ArrayList<>();
        for (Object o : catchData) {
            c.add((List<EcgPointBean>) o);
        }
        int end = catchData.length;
        int count = time / rate;
        int start = end - count;
        if (start < 0) {
            start = 0;
        }
        return new ArrayList<>(c.subList(start, end));
    }

    public static boolean isToTime(int time) {
        int count = gCacheData.size();
        int needCount = time / rate;
        return count >= needCount;
    }


    static class LimitedQueue<T> extends ArrayBlockingQueue<T> {
        private final int limit;

        public LimitedQueue(int capacity) {
            super(capacity);
            limit = capacity;
        }

        @Override
        public boolean offer(T t) {
            if (size() == limit) {
                poll();
            }
            return super.offer(t);
        }
    }
}
