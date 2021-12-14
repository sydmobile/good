package com.syd.good.feature.ecg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
/**
 * @author taotao
 */
public class TestView extends View {

    /** 小网格绘制线宽度 */
    private static final int GRID_LINE_STROKE_WIDTH = 1;

    /** View 宽度 */
    protected int mWidth;
    /** View 高度 */
    protected int mHeight;

    /** 1 毫米对应像素 **/
    private int mOneMmCorrespondsPx;

    /**
     *  x轴 小网格总数
     */
    private int mXAxisSmallGridCount;

    /**
     *  y轴 小网格总数
     */
    private int mYAxisSmallGridCount;


    /** 画笔 */
    protected Paint mPaint;
    /** 绘制路径 */
    protected Path mPath;
    protected Canvas mBufferCanvas;
    private Rect mEcgRect;

    private Bitmap mBufferBitmap;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context) {
        super(context);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mPaint = new Paint();
        mPath = new Path();
        mEcgRect = new Rect(0, 0, mWidth, mHeight);
        mBufferBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        if (mBufferCanvas == null) {
            mBufferCanvas = new Canvas(mBufferBitmap);
        }
        initPaint();
        initData();

    }

    /**
     * 毫米转像素
     * @param context context
     * @param mmValue 毫米
     * @return 像素值
     */
    public static float mm2pxByTypedValue(Context context,float mmValue){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,mmValue,context.getResources().getDisplayMetrics());
    }

    void initData(){
        mOneMmCorrespondsPx = Math.round(mm2pxByTypedValue(getContext(), 1));

        mXAxisSmallGridCount = (int) (mWidth / mOneMmCorrespondsPx);
        mYAxisSmallGridCount = (int) (mHeight / mOneMmCorrespondsPx);
    }

    void drawSmallGrid() {
        mBufferCanvas.save();
        mBufferCanvas.clipRect(mEcgRect);
        mBufferCanvas.drawColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaint.setColor(Color.GRAY);

        for (int i = 0; i <= mXAxisSmallGridCount; i++) {

            if (i % 5 != 0) {
                float xCoord = i * mOneMmCorrespondsPx;
//                mPath.moveTo(xCoord, 0f);
//                mPath.lineTo(xCoord, mHeight);
                mBufferCanvas.drawLine(xCoord, 0, xCoord, mHeight,  mPaint);
            }
        }

        // 绘制横线
        for (int i = 0; i <= mYAxisSmallGridCount; i++) {
            if (i % 5 != 0) {
                float yCoord = i * mOneMmCorrespondsPx;
//                mPath.moveTo(0, yCoord);
//                mPath.lineTo(mWidth, yCoord);
                mBufferCanvas.drawLine(0, yCoord, mWidth,yCoord,  mPaint);
            }
        }

        mBufferCanvas.clipRect(0, 0, mWidth, mHeight);
        mBufferCanvas.restore();
    }

    public void drawEcg(){
        drawSmallGrid();
        drawBigGrid();
        mBufferCanvas.drawPath(mPath, mPaint);
        mBufferCanvas.drawText("1111111111", 25,30, mPaint);
        invalidate();
    }

    protected RectF m_drawRect = new RectF();
    public void clearPartView(int startPos, int count){
        mBufferCanvas.save();

        mPaint.setStyle(Paint.Style.FILL);

        int startx = startPos * mOneMmCorrespondsPx;
        int endx = startx + count * mOneMmCorrespondsPx;
        m_drawRect.left = startx;
        m_drawRect.top = 20;
        m_drawRect.bottom = mHeight;
        m_drawRect.right = endx;
        mPaint.setColor(Color.GREEN);
        mBufferCanvas.drawRect(m_drawRect, mPaint);

        mPaint.setColor(Color.BLACK);

        mBufferCanvas.drawLine(startx, 40, endx, 40, mPaint);
        mBufferCanvas.restore();

        mBufferCanvas.drawPath(mPath, mPaint);
        mBufferCanvas.clipRect(0, 0, mWidth, mHeight);

        mPaint.setStyle(Paint.Style.STROKE);
        invalidate();
    }

    protected void drawBigGrid() {
        mBufferCanvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaint.setAntiAlias(true);

        for (int i = 0; i <= mXAxisSmallGridCount; i++) {
            if (i % 5 == 0) {
                float xCoordinate = i * mOneMmCorrespondsPx;
                mBufferCanvas.drawLine(xCoordinate, 0, xCoordinate, mHeight,  mPaint);
            }
        }

        for (int i = 0; i <= mYAxisSmallGridCount; i++) {
            float yCoordinate = i * mOneMmCorrespondsPx;
            if (i % 5 == 0) {
                mBufferCanvas.drawLine(0, yCoordinate, mWidth,yCoordinate,  mPaint);
            }
        }
        mBufferCanvas.clipRect(0, 0, mWidth, mHeight);
        mBufferCanvas.restore();
    }

    private void initPaint(){
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(GRID_LINE_STROKE_WIDTH);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBufferBitmap, 0, 0, null);
    }
}
