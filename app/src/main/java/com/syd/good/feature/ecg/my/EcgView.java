package com.syd.good.feature.ecg.my;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.Nullable;


import java.util.HashMap;
import java.util.List;
/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/10/11 16:59
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EcgView extends BaseEcgView implements SurfaceHolder.Callback {
    private static final String TAG = "EcgView";
    HashMap<String, EcgBean> mMapEcgBean;
    /** 每部分的高度 */
    float x1AxisPosition;
    /** 存储轨道的顺序信息，按照 ui 名字顺序来绘制 */
    String[] trackInfo;
    /** 纵轴被分成了几部分：例如 一个轨道，分为 2 部分（正负y） */
    int part;
    int gridNumPerPart;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    public static final String[] ECG_LEAD = {"LEAD_I", "LEAD_II", "LEAD_III", "LEAD_aVR", "LEAD_aVL", "LEAD_aVF", "LEAD_V1"};

    public EcgView(Context context) {
        this(context, null);
    }

    public EcgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public static class EcgBean {
        private Path mPath;
        private int id;

        public EcgBean(int id) {
            mPath = new Path();
            this.id = id;
        }

        public Path getPath() {
            return mPath;
        }

        public void setPath(Path path) {
            mPath = path;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Override
    protected void init() {
        super.init();
        mMapEcgBean = new HashMap<>();
        mMapEcgBean.put(ECG_LEAD[0], new EcgBean(0));
        mMapEcgBean.put(ECG_LEAD[1], new EcgBean(1));
        mMapEcgBean.put(ECG_LEAD[2], new EcgBean(2));
        mMapEcgBean.put(ECG_LEAD[3], new EcgBean(3));
        mMapEcgBean.put(ECG_LEAD[4], new EcgBean(4));
        mMapEcgBean.put(ECG_LEAD[5], new EcgBean(5));
        mMapEcgBean.put(ECG_LEAD[6], new EcgBean(6));
//        setTrackInfo(ECG_LEAD[0], ECG_LEAD[1], ECG_LEAD[2]);
        setTrackInfo(ECG_LEAD[0]);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

    }

    @Override
    protected void initData() {
        super.initData();
        x1AxisPosition = mHeight / part;
        gridNumPerPart = mSmallGridNumY/part;
    }

    @Override
    public void setTrackInfo(String... s) {
        this.trackInfo = s;
        mEcgConfigBean.setTrackInfo(trackInfo);
        part = trackInfo.length * 2;
        x1AxisPosition = mHeight / part;
        gridNumPerPart = mSmallGridNumY/part;
    }


    @Override
    public EcgConfigBean getEcgConfigBean() {
        String[] newStrackInfo
                = new String[trackInfo.length];
        System.arraycopy(trackInfo, 0, newStrackInfo, 0, trackInfo.length);
        mEcgConfigBean.setTrackInfo(newStrackInfo);
        mEcgConfigBean.setMsPerMM(currentMsPerMM);
        mEcgConfigBean.setVmPerMM(currentMvPerMM);
        mEcgConfigBean.setDuration(duration);
        return mEcgConfigBean;
    }

    /**
     * 绘制表格
     *
     * @param canvas canvas
     */
    @Override
    protected void drawGrid(Canvas canvas) {
        mPath.reset();
        // 绘制竖线
        for (int i = 0; i <= mSmallGridNumX; i++) {

            if (i % 5 != 0) {
                float xCoord = i * GRID_WIDTH_AND_HEIGHT;
                mPath.moveTo(xCoord, 0f);
                mPath.lineTo(xCoord, mHeight);
            }
        }

        // 绘制横线
        for (int i = 0; i <= mSmallGridNumY; i++) {
            if (i % 5 != 0) {
                float yCoord = i * GRID_WIDTH_AND_HEIGHT;
                mPath.moveTo(0, yCoord);
                mPath.lineTo(mWidth, yCoord);
            }
        }
        canvas.drawPath(mPath, mPaintSmallGrid);
    }

    @Override
    protected void drawBigGrid(Canvas canvas) {
        mPath.reset();
        for (int i = 0; i <= mSmallGridNumX; i++) {
            if (i % 5 == 0) {
                float xCoord = i * GRID_WIDTH_AND_HEIGHT;
                mPath.moveTo(xCoord, 0f);
                mPath.lineTo(xCoord, mHeight);
            }
        }

        for (int i = 0; i <= mSmallGridNumY; i++) {
            float yCoord = i * GRID_WIDTH_AND_HEIGHT;
            if (i % 5 == 0) {
                mPath.moveTo(0, yCoord);
                mPath.lineTo(mWidth, yCoord);
            }
        }
        canvas.drawPath(mPath, mPaintBigGrid);
    }


    @Override
    protected void drawEcgLine(Canvas canvas) {
        if (!checkData()) {
            return;
        }
        float nowX, nowY;
        // 开始绘制
        // 不同轨道初始化 Path，移动到合适的位置。
        for (int trackIndex = 0; trackIndex < trackInfo.length; trackIndex++) {
            EcgBean ecgBean = mMapEcgBean.get(trackInfo[trackIndex]);
            if (ecgBean==null){
                continue;
            }
            Path path = ecgBean.getPath();
            path.reset();
            float trackPosition = (trackIndex * 2 + 1) * x1AxisPosition;
            path.moveTo(0, trackPosition);

            for (int i = 0;i< mDataNum;i++){
                List<EcgPointBean> dataValueList = mDataArray[i];
                if (dataValueList == null || dataValueList.isEmpty()){
                    continue;
                }
                int packageSize = dataValueList.size();
                nowX = i*mDrawPx;
                if (packageSize < mDrawPx){
                    float interval = mDrawPx/packageSize;
                    for (int j = 0;j<packageSize;j++){
                        nowY = getYValue(dataValueList.get(j),ecgBean.getId());
                        nowX+=interval;
                        if (nowY > 0 ){
                            if (nowY / currentMvPerMM > gridNumPerPart ){
                                path.moveTo(nowX,trackPosition - x1AxisPosition);
                            }else {
                                path.lineTo(nowX,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
                            }
                        }else {
                            if (nowY/currentMvPerMM < -gridNumPerPart){
                                path.moveTo(nowX,trackPosition+x1AxisPosition);
                            }else {
                                path.lineTo(nowX,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
                            }
                        }

                    }
                }else {
                    float interval = packageSize/mDrawPx;
//                    for (int j = 0;j< mDrawPx;j++){
//                        int index = Math.round(j * interval);
//                        if (index>=packageSize){
//                            index = packageSize-1;
//                        }
//                        nowY = getYValue(dataValueList.get(index),ecgBean.getId());
//                        if (nowY > 0 ){
//                            if (nowY / currentMvPerMM > gridNumPerPart ){
//                                path.moveTo(nowX+j,trackPosition - x1AxisPosition);
//                            }else {
//                                path.lineTo(nowX+j,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
//                            }
//                        }else {
//                            if (nowY/currentMvPerMM < -gridNumPerPart){
//                                path.moveTo(nowX+j,trackPosition+x1AxisPosition);
//                            }else {
//                                path.lineTo(nowX+j,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
//                            }
//                        }
//                    }

                    // 绘制一个点
                    if (i==currentArrayIndex|| i-1== currentArrayIndex || i-2 == currentArrayIndex || i-3 == currentArrayIndex){
                        continue;
                    }
                    if (i-4 == currentArrayIndex){
                        nowY = getYValue(dataValueList.get(0),ecgBean.getId());
                        if (nowY > 0 ){
                            if (nowY / currentMvPerMM > gridNumPerPart ){
                                path.moveTo(nowX,trackPosition - x1AxisPosition);
                            }else {
                                path.moveTo(nowX,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
                            }
                        }else {
                            if (nowY/currentMvPerMM < -gridNumPerPart){
                                path.moveTo(nowX,trackPosition+x1AxisPosition);
                            }else {
                                path.moveTo(nowX,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
                            }
                        }
                        continue;
                    }

                    nowY = getYValue(dataValueList.get(0),ecgBean.getId());
                    if (nowY > 0 ){
                        if (nowY / currentMvPerMM > gridNumPerPart ){
                            path.moveTo(nowX,trackPosition - x1AxisPosition);
                        }else {
                            path.lineTo(nowX,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
                        }
                    }else {
                        if (nowY/currentMvPerMM < -gridNumPerPart){
                            path.moveTo(nowX,trackPosition+x1AxisPosition);
                        }else {
                            path.lineTo(nowX,trackPosition-(nowY/currentMvPerMM)*GRID_WIDTH_AND_HEIGHT);
                        }
                    }
                }
            }

        }



        // 开始绘制
//        for (int i = 0; i < mDataNum; i++) {
//            // 当前数据
//            List<EcgPointBean> dataValeList = mDataArray[i];
//            // 没有数据
//            if (dataValeList == null || dataValeList.isEmpty()) {
//                continue;
//            }
//
//            for (int trackIndex = 0; trackIndex < trackInfo.length; trackIndex++) {
//                EcgBean ecgBean = mMapEcgBean.get(trackInfo[trackIndex]);
//                Path path = ecgBean.getPath();
//                float trackPosition = (trackIndex * 2 + 1) * x1AxisPosition;
//
//                nowY = getYValue(dataValeList.get(0), ecgBean.id);
//
//                nowX = i * mDrawPx;
//
//                if ( nowY > 0){
//                    if (nowY/currentMvPerMM > mSmallGridNumY/part){
////                        path.moveTo(nowX + (j), trackPosition - dataY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
//                    }else {
//                        if (i == currentArrayIndex) {
//                            path.moveTo(nowX, trackPosition - nowY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
//                        }else {
//                            path.lineTo(nowX, trackPosition - nowY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
//                        }
//                    }
//                }else {
//                    if (nowY/currentMvPerMM < -mSmallGridNumY/part){
//
////                        path.moveTo(nowX + (j), trackPosition - dataY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
//                    }else {
//                        if (i == currentArrayIndex) {
//                            path.moveTo(nowX, trackPosition - nowY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
//                        }else {
//                            path.lineTo(nowX, trackPosition - nowY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
//                        }
//                    }
//                }
//
////                float interval = dataValeList.size() / (mDrawPx);
////                for (int j = 0; j < mDrawPx; j++) {
////                    int index = Math.round(j * interval);
////                    if (index >= dataValeList.size()) {
////                        index = dataValeList.size() - 1;
////                    }
////                    float dataY = getYValue(dataValeList.get(index), ecgBean.id);
////                    if (dataY > 0) {
////                        if (dataY / currentMvPerMM > mSmallGridNumY / part) {
////                            path.moveTo(nowX + (j), trackPosition - mSmallGridNumY / part * GRID_WIDTH_AND_HEIGHT);
////                        } else {
////                            path.lineTo(nowX + (j), trackPosition - dataY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
////                        }
////                    } else {
////                        if (dataY / currentMvPerMM < -mSmallGridNumY / part) {
////                            path.moveTo(nowX + (j), trackPosition + mSmallGridNumY / part * GRID_WIDTH_AND_HEIGHT);
////                        } else {
////                            path.lineTo(nowX + (j), trackPosition - dataY / currentMvPerMM * GRID_WIDTH_AND_HEIGHT);
////                        }
////                    }
////
////                }
//
//            }
//
//        }
        for (String s : trackInfo) {
            canvas.drawPath(mMapEcgBean.get(s).getPath(), mPaintEcgLine);
        }

    }


    private float getYValue(EcgPointBean pointBean, int id) {
        switch (id) {
            case 0:
//                return pointBean.LEAD_I/3;
                return pointBean.LEAD_I / 3;
            case 1:
                return pointBean.LEAD_I / 3;
//                return pointBean.LEAD_II;
            case 2:
                return pointBean.LEAD_III / 3;
//                return pointBean.LEAD_III;
            case 3:
                return pointBean.LEAD_aVR / 3;
            case 4:
                return pointBean.LEAD_aVL / 3;
            case 5:
                return pointBean.LEAD_aVF / 3;
            case 6:
                return pointBean.LEAD_V1 / 3;
            default:
        }
        return 0;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG,"surfaceChanged");

        new Thread(()->{
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            if (mCanvas==null){
                Log.e(TAG,"null");
                return;
            }
            mCanvas.drawColor(BACKGROUND_COLOR);
            drawGrid(mCanvas);
            drawBigGrid(mCanvas);
            drawEcgLine(mCanvas);
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }).start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

//    @Override
//    public void run() {
//        drawSomething();
//    }

    //绘图逻辑
    @Override
    protected void drawSomething() {
        // 数据整理
        while (!gQueue.isEmpty()) {
            this.mDataArray[currentArrayIndex++] = gQueue.poll();
            if (currentArrayIndex == mDataNum) {
                currentArrayIndex = 0;
            }
        }
        if (getFreezeState()== FreezeState.frozen){
            return;
        }
        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(BACKGROUND_COLOR);
            drawGrid(mCanvas);
            drawBigGrid(mCanvas);
            drawEcgLine(mCanvas);
            //绘图
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
