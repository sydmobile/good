package com.syd.good.feature.dragview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.DragviewActivityMainBinding;

import org.jetbrains.annotations.NotNull;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : ViewDragHelper 使用
 *     version: 1.0
 * </pre>
 */
public class ViewDragHelperTestActivity extends BaseActivity1<DragviewActivityMainBinding> {

    @Override
    protected void initBinding() {
        mBinding = DragviewActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        ((DragLayout) mBinding.getRoot().getChildAt(0)).setCh(mBinding.xx);
    }

    public static class DragLayout extends FrameLayout {
        private static final String TAG = "DragLayout";
        private ViewDragHelper mViewDragHelper;
        private int mCurrentLeft;
        private int mCurrentTop;
        private int rightBound;
        private int leftBound;
        private View mView;

        public DragLayout(@NonNull @NotNull Context context) {
            this(context, null);
        }

        public void setCh(View view){
            mView = view;
        }

        public DragLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public DragLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
            this(context, attrs, defStyleAttr, 0);
        }

        public DragLayout(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init();
            Log.e(TAG,"view:"+mView);
        }



        private void init() {
            ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    Log.e(TAG, "tryCaptureView, left=" + child.getLeft() + "; top=" + child.getTop());
                    Log.e(TAG, "pointerId:" + pointerId + "==");
                    return true;
                }

                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    Log.e(TAG,"child:"+left+"dx:"+dx);
                    leftBound = getPaddingLeft();
                    rightBound = getWidth() - child.getWidth() - getPaddingRight();
                    mCurrentLeft = Math.min(Math.max(left, leftBound), rightBound);

                    return mCurrentLeft;
                }

                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    final int topBound = getPaddingTop();
                    final int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();
                    mCurrentTop = Math.min(Math.max(top,topBound),bottomBound);
                    return mCurrentTop;
                }



                @Override
                public void onViewReleased(@NonNull @NotNull View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    Log.e(TAG,"view:"+mView+"releasedChild"+releasedChild);
                    if (mView != releasedChild){
                        return;
                    }
                    if (mCurrentLeft+releasedChild.getWidth()/2>getWidth()/2){
                        mViewDragHelper.settleCapturedViewAt(rightBound,mCurrentTop);
                    }else {
                        mViewDragHelper.settleCapturedViewAt(leftBound,mCurrentTop);
                    }

                    invalidate();


                }

            };
            mViewDragHelper = ViewDragHelper.create(this, callback);
        }

        @Override
        public void computeScroll() {
            super.computeScroll();
            if (mViewDragHelper != null && mViewDragHelper.continueSettling(true)) {
                invalidate();
            }
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            mViewDragHelper.processTouchEvent(event);
            return true;
        }
    }
}