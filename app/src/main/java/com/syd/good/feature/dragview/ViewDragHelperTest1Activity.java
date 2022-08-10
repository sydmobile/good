package com.syd.good.feature.dragview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.DragviewActivityMain1Binding;
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
public class ViewDragHelperTest1Activity extends BaseActivity1<DragviewActivityMain1Binding> {

    @Override
    protected void initBinding() {
        mBinding = DragviewActivityMain1Binding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        ((DragLayout) mBinding.getRoot().getChildAt(0)).setCh(mBinding.xx);
    }

    public static class DragLayout extends LinearLayout {
        private static final String TAG = "LinearLayout";
        private ViewDragHelper mViewDragHelper;
        private int mCurrentLeft;
        private int mCurrentTop;
        private int rightBound;
        private int leftBound;
        private View mView;
        private View mMenuView;
        private View mContentView;

        public DragLayout(@NonNull @NotNull Context context) {
            this(context, null);
        }

        public void setCh(View view) {
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
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            mMenuView = getChildAt(1);
            mContentView = getChildAt(0);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            mMenuView.layout(l, getMeasuredHeight(), mMenuView.getMeasuredWidth(),
                    getMeasuredHeight() + mMenuView.getMeasuredHeight());
            mContentView.layout(l, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
        }

        private void init() {
            setOrientation(VERTICAL);
            ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    return mMenuView == child;
                }


                @Override
                public void onEdgeTouched(int edgeFlags, int pointerId) {
                    Log.e(TAG, "onEdgeTouched()======edgeFlags:" + edgeFlags + "pointerId:" + pointerId);
                    super.onEdgeTouched(edgeFlags, pointerId);

                }

                @Override
                public boolean onEdgeLock(int edgeFlags) {
                    Log.e(TAG,"onEdgeLock()"+edgeFlags);
                    return super.onEdgeLock(edgeFlags);
                }

                @Override
                public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                    super.onEdgeDragStarted(edgeFlags, pointerId);
                    Log.e(TAG,"onEdgeDragStarted()");
                    mViewDragHelper.captureChildView(mMenuView, pointerId);
                }


                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    return Math.max(getHeight() - child.getHeight(), top);
                }


                @Override
                public void onViewReleased(@NonNull @NotNull View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    if (yvel <= 0) {
                        mViewDragHelper.settleCapturedViewAt(0,
                                getHeight() - releasedChild.getHeight());
                    } else {
                        mViewDragHelper.settleCapturedViewAt(0, getHeight());
                    }
                    invalidate();


                }

            };
            mViewDragHelper = ViewDragHelper.create(this, callback);
            mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
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