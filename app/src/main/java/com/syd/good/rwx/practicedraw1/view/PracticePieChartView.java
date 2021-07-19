package com.syd.good.rwx.practicedraw1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * <p>
 * date: 2021/7/1 11:10
 *
 * @author syd
 * @version 1.0
 */
public class PracticePieChartView extends View {

    ArrayList<MyArc> mData = new ArrayList<>();
    Paint textPaint = new Paint();

    public PracticePieChartView(Context context) {
        super(context);
    }

    public PracticePieChartView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //初始化画布信息
        MyPoint myPoint = new MyPoint(getMeasuredWidth() * 0.5f,
                getMeasuredHeight() * 0.5f,
                "圆心", (int) (getMeasuredHeight() * 0.2));
        canvas.translate(myPoint.x, myPoint.y);
        myPoint.x = 0;
        myPoint.y = 0;


        //画名字
        textPaint.setColor(Color.parseColor("#ffffff"));
        textPaint.setTextSize(40);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStrokeWidth(1);
        canvas.drawText("饼图", myPoint.x, getMeasuredHeight() * 0.4f, textPaint);


        //画饼

        mData.add(new MyArc("Lollipop", "#F44336", 12));
        mData.add(new MyArc("Marshmallow", "#FFC107", 6));
        mData.add(new MyArc("Froyo", "#9C27B0", 1));
        mData.add(new MyArc("Gingerbread", "#9E9E9E", 2));
        mData.add(new MyArc("Ice Cream Sandwich", "#009688", 2));
        mData.add(new MyArc("Jelly Bean", "#2196F3", 6));
        mData.add(new MyArc("KitKat", "#FF4081", 12));


        RectF oval = new RectF(-myPoint.r, -myPoint.r, myPoint.r, myPoint.r);//图所在位置

        float toatleSize = 0;//总份数
        float maxArc = 0;
        int max = 0;

        for (int i = 0; i < mData.size(); i++) {
            MyArc myArc = mData.get(i);
            if (myArc.weight > maxArc) {
                max = i;
            }
            toatleSize += myArc.weight;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float startAngle = 160;


        MyPoint midStart = new MyPoint(0, 0, "中点开始", 0);
        MyPoint midEnd = new MyPoint(0, 0, "中点结束", 0);
        Paint aaa = new Paint();
        aaa.setStrokeWidth(3);
        aaa.setStrokeCap(Paint.Cap.ROUND);
        aaa.setColor(Color.parseColor("#C2C2C2"));
        MyPoint pianyiPoint = new MyPoint(0, 0, "偏移", 20);

        for (int i = 0; i < mData.size(); i++) {
            MyArc myArc = mData.get(i);
            myArc.weight = myArc.weight * 100 / toatleSize * 360 * 0.01f;
            paint.setColor(Color.parseColor(myArc.color));

            float thTa = startAngle + (myArc.weight - 4) * 0.5F;
            midStart.x = myPoint.r * (float) Math.cos(thTa / 180 * Math.PI);
            midStart.y = myPoint.r * (float) Math.sin(thTa / 180 * Math.PI);
            midEnd.x = (myPoint.r + 40) * (float) Math.cos(thTa / 180 * Math.PI);
            midEnd.y = (myPoint.r + 40) * (float) Math.sin(thTa / 180 * Math.PI);


            pianyiPoint.x = pianyiPoint.r * (float) Math.cos(thTa / 180 * Math.PI);
            pianyiPoint.y = pianyiPoint.r * (float) Math.sin(thTa / 180 * Math.PI);
//            if (i == max) {
//                canvas.save();
//
//                canvas.translate(pianyiPoint.x, pianyiPoint.y);
//
//            }
            canvas.drawArc(oval, startAngle, myArc.weight - 4, true, paint);
            canvas.drawLine(midStart.x, midStart.y, midEnd.x, midEnd.y, aaa);

            float end = 0;
            if (midStart.x > 0) {
                end = myPoint.x + myPoint.r + 40;
                textPaint.setTextAlign(Paint.Align.LEFT);
            } else {
                end = midEnd.x - (myPoint.r + 20);
                textPaint.setTextAlign(Paint.Align.RIGHT);
            }
            canvas.drawLine(midEnd.x, midEnd.y, end, midEnd.y, aaa);
            textPaint.setTextSize(30);
            canvas.drawText(myArc.name, end, midEnd.y, textPaint);

//            if (i == max) {
//                canvas.restore();
//            }

            startAngle += myArc.weight;


        }


    }


    private static class MyPoint {
        float x;
        float y;
        String name;
        int r; // 半径

        public MyPoint(float x, float y, String name, int r) {
            this.x = x;
            this.y = y;
            this.name = name;
            this.r = r;
        }
    }

    private static class MyArc {
        String name;
        String color;
        float weight;

        public MyArc(String name, String color, float weight) {
            this.name = name;
            this.color = color;
            this.weight = weight;
        }
    }


}
