package com.syd.good.carsonblog.a2eventdispatcher;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * <p>
 * date: 2021/1/29 11:06
 *
 * @author syd
 * @version 1.0
 */

/*

class MyActivity {
    Window mWindow;

    public boolean dispatchTouchEvent(MotionEvent event) {
        // 事件开始的时候就是 DOWN 事件 == 按下
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onUserInteraction();
        }

        if (getWindow().superDispatchTouchEvent(event)) {
            return true;
        }

        return onTouchEvent(event);
    }

    // 空方法，用户自定义
    public void onUserInteraction() {

    }

    public boolean onTouchEvent(MotionEvent event) {
        // 点击到 Window 边界外。
        if (mWindow.shouldCloseOnTouch(this, event)) {
            finish();
            return true;
        }
        // 一般情况返回 false
        return false;
    }

    Window getWindow() {
        return new PhoneWindow(context);
    }
}


class PhoneWindow extends Window {
    ViewGroup mDecor;

    public boolean shouldCloseOnTouch(Context context,MotionEvent event){
        if (mCloseOnTouchOutside && event.getAction() == MotionEvent.ACTION_DOWN &&
        isOutOfBounds(context,event) && peekDecorView()!=null){
            return true;
        }
        return false;
    }

    public void superDispatchTouchEvent(event) {
        mDecor.dispatchTouchEvent(event);
    }
}

class ViewGroup1{
    public boolean dispatchTouchEvent(MotionEvent event){
        final boolean intercepted;
        if (!disallowIntercept){
            intercepted = onInterceptTouchEvent(event);
        }else {
            intercepted = false;
        }

        if (!canceld && !intercepted){
            for (int i = childCount -1;i>=0;i--){
                if ("点击的点在子View内"){
                    if(child.dispathcTouchEvent(event)){
                        mMotionTarget = child;
                        return true;
                    }
                }
            }
        }

        return super.dispatchTouchEvent(event);
    }

    public boolean onInterceptTouchEvent(MotionEvent event){
        return  false;
    }
}

 */
